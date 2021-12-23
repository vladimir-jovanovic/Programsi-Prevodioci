
import java.util.Stack;
import java.io.Reader;

public class LL1Parser{
    
    public static int ACC = 0;
    public static int ERR = -10;
    public static int POP = -1;

    private Stack<Integer> stack;
    private SyntaxTable syntaxTable;
    private MPLexer lexAnalyzer;

    public LL1Parser(Reader in){
        stack = new Stack<Integer>();
        this.syntaxTable = new SyntaxTable();

        lexAnalyzer = new MPLexer(in);
    }

    public int SA_LL1(){
        
        boolean err = false;
        boolean recognized = false;

        int next = nextToken();

        stack.push(sym.EOF);
        stack.push(Grammar.rules.get(0).leftSide);
            
        do{
            int value = syntaxTable.table[stack.peek()][next];

            if(value == ERR){
                err = true;
                break;
            }

            if(value == ACC){
                recognized = true;
                break;
            }

            if(value == POP){
                stack.pop();
                next = nextToken();
                continue;
            }

            GrammarRule rule = Grammar.rules.get(value - 1);
            stack.pop();
            for(int i = rule.rightSide.length - 1; i >=0; i--) {
                stack.push(rule.rightSide[i]);
            }

        }while(!err && !recognized);

        return recognized ? 0 : -1;
    }

    private int nextToken(){
        try{
            return lexAnalyzer.next_token().m_index;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return -1;
    }
}

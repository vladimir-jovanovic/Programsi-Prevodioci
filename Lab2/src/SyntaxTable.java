
import java.util.Stack;

public class SyntaxTable{
    
    public static int n = 19;
    public static int m = 12;

    public int table[][];

    public SyntaxTable(){
        
        table = new int[n][m];

        for(int i = 0; i < m; i++)
            for(int j = 0; j < m; j++)
                table[i][j] = i == j ? LL1Parser.ACC : LL1Parser.ERR;
        
        table[sym.EOF][sym.EOF] = LL1Parser.ACC;

        for(int i = m; i < n; i++)
            for(int j = 0; j < m; j++)
                table[i][j] = LL1Parser.ERR;

        table[Grammar.STATEMENTS][sym.IF] = Grammar.rules.get(0).id;
        table[Grammar.STATEMENTS][sym.ID] = Grammar.rules.get(0).id;
        table[Grammar.STATEMENTS_PRIME][sym.SEMICOLON] = Grammar.rules.get(1).id;
        table[Grammar.STATEMENTS_PRIME][sym.CLOSE_CURLY_BRACKETS] = Grammar.rules.get(2).id;
        table[Grammar.STATEMENTS_PRIME][sym.EOF] = Grammar.rules.get(2).id;
        table[Grammar.STATEMENT][sym.IF] = Grammar.rules.get(4).id;
        table[Grammar.STATEMENT][sym.ID] = Grammar.rules.get(3).id;
        table[Grammar.IF_STATEMENT][sym.IF] = Grammar.rules.get(5).id;
        table[Grammar.REL_EXPRESSION][sym.ID] = Grammar.rules.get(6).id;
        table[Grammar.REL_EXPRESSION][sym.CONST] = Grammar.rules.get(6).id;
        table[Grammar.ASSIGNMENT][sym.ID] = Grammar.rules.get(7).id;
        table[Grammar.EXPRESSION][sym.ID] = Grammar.rules.get(8).id;
        table[Grammar.EXPRESSION][sym.CONST] = Grammar.rules.get(9).id;
    }
}

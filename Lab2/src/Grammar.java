
import java.util.ArrayList;

public class Grammar{

    public final static int STATEMENTS = 12;
    public final static int STATEMENTS_PRIME = 13;
    public final static int STATEMENT = 14;
    public final static int IF_STATEMENT = 15;
    public final static int REL_EXPRESSION = 16;
    public final static int ASSIGNMENT = 17;
    public final static int EXPRESSION = 18;

    public final static ArrayList<GrammarRule> rules = new ArrayList<GrammarRule>();

    public Grammar(){
        
        rules.clear();

        rules.add(new GrammarRule(1, STATEMENTS, new int[]{STATEMENT, STATEMENTS_PRIME}));
        rules.add(new GrammarRule(2, STATEMENTS_PRIME, new int[]{sym.SEMICOLON, STATEMENT, STATEMENTS_PRIME}));
        rules.add(new GrammarRule(3, STATEMENTS_PRIME, new int[]{}));
        rules.add(new GrammarRule(4, STATEMENT, new int[]{ASSIGNMENT}));
        rules.add(new GrammarRule(5, STATEMENT, new int[]{IF_STATEMENT}));
        rules.add(new GrammarRule(6, IF_STATEMENT, new int[]{sym.IF, sym.OPEN_ROUND_BRACKETS, REL_EXPRESSION, 
                                                    sym.CLOSE_ROUND_BRACKETS, sym.COLON, sym.OPEN_CURLY_BRACKETS,
                                                    STATEMENTS, sym.CLOSE_CURLY_BRACKETS}));
        rules.add(new GrammarRule(7, REL_EXPRESSION, new int[]{EXPRESSION, sym.EQ, EXPRESSION}));
        rules.add(new GrammarRule(8, ASSIGNMENT, new int[]{sym.ID, sym.ASSIGN, EXPRESSION}));
        rules.add(new GrammarRule(9, EXPRESSION, new int[]{sym.ID}));
        rules.add(new GrammarRule(10, EXPRESSION, new int[]{sym.CONST}));

    }
}

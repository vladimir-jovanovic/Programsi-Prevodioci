
public class GrammarRule{
    
    public int id;
    public int leftSide;
    public int[] rightSide;

    public GrammarRule(int id, int leftSide, int[] rightSide){
        this.id = id;
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public String toString(){
        String retValue = id + ": " + leftSide + " --> ";
        for(int symbol : rightSide) retValue += symbol + " ";
        return retValue;
    }
}

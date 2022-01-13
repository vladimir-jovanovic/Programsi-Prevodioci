package AST;

public class Eq extends BinaryExpression {
	
	public Eq( Expression left, Expression right )
	{
		super( left, right );
	}
	
	protected String opCode()
	{
		return "Compare_Equal";
	}
}

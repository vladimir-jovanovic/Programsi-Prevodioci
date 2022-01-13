package AST;

public class Gt extends BinaryExpression {
	
	public Gt( Expression left, Expression right )
	{
		super( left, right );
	}
	
	protected String opCode()
	{
		return "Compare_Greater";
	}
}

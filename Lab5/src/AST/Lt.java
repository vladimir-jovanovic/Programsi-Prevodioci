package AST;

public class Lt extends BinaryExpression {
	
	public Lt( Expression left, Expression right )
	{
		super( left, right );
	}
	
	protected String opCode()
	{
		return "Compare_Less";
	}
}

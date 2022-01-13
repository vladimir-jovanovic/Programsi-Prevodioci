package AST;

import java.io.*;

import SymbolTable.Constant;
import SymbolTable.Type;

public class ConstantExpression 
extends Expression{
	private Constant targetConst;
	
	public ConstantExpression( Constant c )
	{
		targetConst = c;
	}
	
	public void translate( BufferedWriter out )
	throws IOException
	{
		this.result = targetConst.type.tkind == Type.STRING ? String.format("\"%s\"", targetConst.value.toString()) : targetConst.value.toString();
	}
	
	protected void genLoad( String reg, BufferedWriter out )
	throws IOException
	{
		out.write( "\tLoad_Const\t" + 
				reg + ", " + result );
		out.newLine();
	}
}

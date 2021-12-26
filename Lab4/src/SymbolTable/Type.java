package SymbolTable;

public class Type extends SymbolNode {
	public static int INTEGER = 0;
	public static int DOUBLE = 1;
	public static int STRING = 2;
	public static int UNKNOWN = 3;
	public int tkind;
	
	public Type ( String name, 
			int typeKind, 
			SymbolNode next)
	{
		super( name, SymbolNode.TYPE, null, next );
		this.tkind = typeKind;
		this.type = this;
	}
}

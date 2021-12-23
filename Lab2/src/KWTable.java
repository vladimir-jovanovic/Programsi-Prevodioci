import java.util.Hashtable;
public class KWTable {

	private Hashtable<String, Integer> mTable;

	public KWTable()
	{
		mTable = new Hashtable<String, Integer>();
		mTable.put("int", new Integer(sym.INT));
		mTable.put("double", new Integer(sym.DOUBLE));
		mTable.put("string", new Integer(sym.STRING));
		mTable.put("if", new Integer(sym.IF));
		mTable.put("eq", new Integer(sym.EQ));
	}
	
	public int find(String keyword)
	{
		Object symbol = mTable.get(keyword);

		if (symbol != null) return (int)symbol;
        else return -1;
	}
}

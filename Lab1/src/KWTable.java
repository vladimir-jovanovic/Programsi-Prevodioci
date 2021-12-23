import java.util.Hashtable;
public class KWTable {

	private Hashtable<String, Integer> mTable;

	public KWTable()
	{
		mTable = new Hashtable<String, Integer>();
		mTable.put("experiment", new Integer(sym.EXPERIMENT_START));
		mTable.put("~experiment", new Integer(sym.EXPERIMENT_END));
		mTable.put("int", new Integer(sym.INT));
		mTable.put("double", new Integer(sym.DOUBLE));
		mTable.put("string", new Integer(sym.STRING));
		mTable.put("if", new Integer(sym.IF));
		mTable.put("lt", new Integer(sym.LT));
		mTable.put("eq", new Integer(sym.EQ));
		mTable.put("gt", new Integer(sym.GT));
		mTable.put("requirements", new Integer(sym.REQUIREMENTS_START));
		mTable.put("~requirements", new Integer(sym.REQUIREMENTS_END));
		mTable.put("nodes", new Integer(sym.NODES));
		mTable.put("execution", new Integer(sym.EXECUTION_START));
		mTable.put("~execution", new Integer(sym.EXECUTION_END));
		mTable.put("node", new Integer(sym.NODE_DEF_START));
		mTable.put("~node", new Integer(sym.NODE_DEF_END));
		mTable.put("name", new Integer(sym.NAME));
		mTable.put("WP", new Integer(sym.WP));
	}
	
	public int find(String keyword)
	{
		Object symbol = mTable.get(keyword);

		if (symbol != null) return (int)symbol;
        else return -1;
	}
}

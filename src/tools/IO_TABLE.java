package tools;

public class IO_TABLE
{

	public String export(String path,String tableName)
	{
		
		String xlsxName=tableName+(new Boc_Time().getDate().replace("-", ""));
		
		return xlsxName;
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}

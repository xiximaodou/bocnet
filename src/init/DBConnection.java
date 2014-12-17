package init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import tools.XMLParser;

public class DBConnection
{
	private String SIDNAME;
	private String IPADDR;
	private String DBPORT;
	private String DRIVERNAME;
	private String USERNAME;
	private String PASSWORD;
	private String CONNSTR;

	private XMLParser xmlParser;
	private static String dbconfig = null;

	private Connection conn = null;

	public Connection getConn()
	{
		if (conn == null)
		{
			xmlParser = new XMLParser(dbconfig);
			SIDNAME = xmlParser.getData("SIDName");
			IPADDR = xmlParser.getData("IPAddr");
			DBPORT = xmlParser.getData("DBPort");
			DRIVERNAME = xmlParser.getData("DriverName");
			USERNAME = xmlParser.getData("UserName");
			PASSWORD = xmlParser.getData("Password");
			CONNSTR = xmlParser.getData("ConnStr");
			try
			{
				Class.forName(DRIVERNAME);
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}

			try
			{
				conn = DriverManager.getConnection(CONNSTR + IPADDR + ":" + DBPORT + ":" + SIDNAME, USERNAME, PASSWORD);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return conn;
	}

	public static String getDbconfig()
	{
		return dbconfig;
	}

	public static void setDbconfig(String dbconfig)
	{
		DBConnection.dbconfig = dbconfig;
	}
}

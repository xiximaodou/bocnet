package tools.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import tools.XMLParser;

public class DBConn
{
	private Connection conn;
	
	public Connection getConn()
	{
		if (conn == null)
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}

			try
			{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "scott", "tiger");
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return conn;
	}
	public static void main(String[] args)
	{
		Connection conn=new DBConn().getConn();
		System.out.println(conn);
	}
}

package tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import tools.test.DBConn;

public class Boc_Time
{

	private String timeFormat="HH:mm:ss";
	private String dateFormat="yyyy-MM-dd";
	private SimpleDateFormat format=null;
	
	public String getTime()
	{
		Date d=new Date();
		format=new SimpleDateFormat(timeFormat);
		return format.format(d);
		
	}
	public String getDate()
	{
		Date d=new Date();
		format=new SimpleDateFormat(dateFormat);
		return format.format(d);
	}
	
	public static void main(String[] args) throws SQLException
	{
		Boc_Time bt=new Boc_Time();
		System.out.println(bt.getDate().replace('-', '_'));
		System.out.println(bt.getTime());
		Connection con=new DBConn().getConn();
		PreparedStatement pre;
		ResultSet rs;
		
		String sql = "select systimestamp st from dual";
		pre = con.prepareStatement(sql);
		
		rs = pre.executeQuery();
		while (rs.next())
		{
			Timestamp ts=rs.getTimestamp("st");
			bt.format=new SimpleDateFormat(bt.dateFormat);
			System.out.println(bt.format.format(ts));			
		}
		
		
	}
}

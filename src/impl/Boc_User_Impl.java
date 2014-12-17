package impl;

import init.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pojo.Boc_User;
import tools.MD5Util;
import org.apache.log4j.Logger;

public class Boc_User_Impl
{
	private Logger logger = Logger.getLogger(this.getClass());

	private Connection con = null;
	private PreparedStatement pre = null;
	private ResultSet rs = null;

	public Boc_User_Impl()
	{
		con = new DBConnection().getConn();
	}

	public List<Boc_User> queryUser(String name, String password)
	{
		ArrayList<Boc_User> users = new ArrayList<Boc_User>();

		try
		{
			pre = con.prepareStatement("SELECT * FROM boc_user WHERE name = ? and password = ? ");
			pre.setString(1, name);
			pre.setString(2, MD5Util.MD5(password));
			rs = pre.executeQuery();
			while (rs.next())
			{
				Boc_User user = new Boc_User();
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				users.add(user);
			}
			logger.info("------Get " + users.size() + " user: " + name);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				con.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return users;
	}

	public boolean insertUser(String name, String password)
	{

		try
		{
			pre = con.prepareStatement("insert into boc_user values(?,?)");
			pre.setString(1, name);
			pre.setString(2, MD5Util.MD5(password));
			pre.executeUpdate();
			logger.info("------Insert a user: " + name);
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}

	}

}

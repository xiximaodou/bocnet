package impl;

import init.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import pojo.Boc_Server;

public class Boc_Server_Impl
{

	private Logger logger = Logger.getLogger(this.getClass());
	private Connection con = null;
	private PreparedStatement pre = null;
	private ResultSet rs = null;
	
	int size = 3;

	public Boc_Server_Impl()
	{
		DBConnection conns = new DBConnection();
		con = conns.getConn();
	}
	
	public List<Boc_Server> queryModule(String module, int currentPage)
	{
		ArrayList<Boc_Server> servers = new ArrayList<Boc_Server>();
		
		try
		{
			int from = (currentPage - 1) * size;
			int to = currentPage * size;

			String sql = "select * from (SELECT rownum no, module,type, ip,remark FROM boc_server bs WHERE MODULE LIKE ?)  where no>? and no<=?";
			pre = con.prepareStatement(sql);
			pre.setString(1, "%" + module + "%");
			pre.setInt(2, from);
			pre.setInt(3, to);
			rs = pre.executeQuery();
			while (rs.next())
			{
				Boc_Server server = new Boc_Server();
				server.setNo(rs.getInt("no"));
				server.setModule(rs.getString("module"));
				server.setType(rs.getString("type"));
				server.setIp(rs.getString("ip"));
				server.setRemark(rs.getString("remark"));
				servers.add(server);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return servers;
	}

	// 根据关键字查询总页数
	public int cluPage(String module)
	{
		int countPage = 0; // 存储总页数
		try
		{
			pre = con.prepareStatement("SELECT COUNT(*) total FROM boc_server T WHERE MODULE LIKE ?");
			pre.setString(1, "%" + module + "%");
			rs = pre.executeQuery();
			if (rs.next())
			{
				int total = rs.getInt("total");
				countPage = total % size == 0 ? total / size : total / size + 1;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		logger.info("------Total Pages: "+countPage);
		return countPage;
	}

	public boolean insert(String module,String type,String ip,String remark)
	{
		try
		{
			pre = con.prepareStatement("insert into boc_server(no,module,type,ip,remark) values(boc_no_seq.nextval,?,?,?,?)");
			pre.setString(1, module);
			pre.setString(2, type);
			pre.setString(3, ip);
			pre.setString(4, remark);
			pre.executeUpdate();
			logger.info("------Insert a server: " + module+"\t"+type+"\t"+ip+"\t"+ip+"\t"+remark);
			return true;
		}
		catch (SQLException e)
		{
			logger.error("------Insert a server:"+e.toString());
			return false;
		}
		
	}
	public boolean update(int no,String module,String type,String ip,String remark)
	{
		try
		{
			pre = con.prepareStatement("update boc_server set module=?,type=?,ip=?,remark=? where no=?");
			pre.setString(1, module);
			pre.setString(2, type);
			pre.setString(3, ip);
			pre.setString(4, remark);
			pre.setInt(5, no);
			pre.executeUpdate();
			logger.info("------update a server: " + module+"\t"+type+"\t"+ip+"\t"+ip+"\t"+remark);
			return true;
		}
		catch (SQLException e)
		{
			logger.error("------update a server:"+e.toString());
			return false;
		}
		
	}

}

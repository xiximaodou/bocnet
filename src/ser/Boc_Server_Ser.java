package ser;

import impl.Boc_Server_Impl;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import pojo.Boc_Server;

/**
 * Servlet implementation class LoginSer
 */

public class Boc_Server_Ser extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Boc_Server_Ser.class);
	Boc_Server_Impl tServerImpl = new Boc_Server_Impl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Boc_Server_Ser()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String status = request.getParameter("status");

		if (status.equals("query"))
		{
			String keyword = request.getParameter("keyword");
			HttpSession session = request.getSession();
			session.setAttribute("keyword", keyword);
			session.setAttribute("currentPage", 1);

			session.setAttribute("countPage", tServerImpl.cluPage(keyword));
			List<Boc_Server> servers = tServerImpl.queryModule(keyword, 1);

			// ��������ݴ�Ser���ݵ���ͼҳ����
			request.setAttribute("servers", servers);
			// ҳ��֮����ת ser-->jsp :����ת���ڲ���ת������ת���������ҳ��
			RequestDispatcher dispatcher = request.getRequestDispatcher("/server.jsp");
			dispatcher.forward(request, response);
		} else if (status.equals("splitPage"))
		{
			// ��session���õ��ؼ���
			String keyword = request.getSession().getAttribute("keyword").toString();
			int currentPage = Integer.parseInt(request.getParameter("currentPage"));
			// ����session�еĵ�ǰҳ
			request.getSession().setAttribute("currentPage", currentPage);
			List<Boc_Server> servers = tServerImpl.queryModule(keyword, currentPage);

			// ��������ݴ�Ser���ݵ���ͼҳ����
			request.setAttribute("servers", servers);
			// ҳ��֮����ת ser-->jsp :����ת���ڲ���ת������ת���������ҳ��
			RequestDispatcher dispatcher = request.getRequestDispatcher("/server.jsp");
			dispatcher.forward(request, response);
		}else if(status.equals("insert"))
		{
			String module=request.getParameter("module");
			String ip=request.getParameter("ip");
			String type=request.getParameter("type");
			String remark=request.getParameter("remark");
			Boc_Server_Impl boc_Server_Impl=new Boc_Server_Impl();
			
			boolean result=boc_Server_Impl.insert(module, type, ip, remark);
			if(result)
			{
				request.setAttribute("info", "server�Ѳ���");
			}
			else
			{
				request.setAttribute("error", "server����ʧ��");
			}
			request.getRequestDispatcher("/private/insertServer.jsp").forward(request,
					response);
		}
		else if(status.equals("update"))
		{
			int no=Integer.parseInt(request.getParameter("no"));
			String module=request.getParameter("module");
			String ip=request.getParameter("ip");
			String type=request.getParameter("type");
			String remark=request.getParameter("remark");
			Boc_Server_Impl boc_Server_Impl=new Boc_Server_Impl();
			
			boolean result=boc_Server_Impl.update(no,module, type, ip, remark);
			if(result)
			{
				request.setAttribute("info", "server�Ѹ���");
			}
			else
			{
				request.setAttribute("error", "server����ʧ��");
			}
			request.getRequestDispatcher("/private/insertServer.jsp").forward(request,
					response);
		}
	}

}

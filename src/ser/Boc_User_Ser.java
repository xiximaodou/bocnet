package ser;

import impl.Boc_User_Impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddTUserSer
 */
public class Boc_User_Ser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Boc_User_Ser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status=request.getParameter("status");
		if(status.equals("insertUser"))
		{
			String uname = request.getParameter("name");
			String upass = request.getParameter("password");
			Boc_User_Impl tUserImpl=new Boc_User_Impl();
			boolean result=tUserImpl.insertUser(uname, upass);
			if(result)
			{
				request.setAttribute("info", "用户"+uname+"已插入");
			}
			else
			{
				request.setAttribute("error", "用户"+uname+"插入失败");
			}
			request.getRequestDispatcher("/private/insertUser.jsp").forward(request,
					response);
		}
	}

}

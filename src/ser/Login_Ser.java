package ser;

import impl.Boc_User_Impl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class LoginSer
 */

public class Login_Ser extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Login_Ser.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login_Ser()
	{
		super();
		// TODO Auto-generated constructor stub
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
		if (status.equals("login"))
		{
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			Boc_User_Impl tUserImpl = new Boc_User_Impl();

			if (tUserImpl.queryUser(name, password).size() > 0)
			{
				logger.info("------"+name + " has log in!");
				HttpSession session = request.getSession();
				session.setAttribute("name", name);
				// 判断用户是否需要保存自己的登录信息
				Cookie nameCookie = new Cookie("name", name);
				Cookie passwordCookie = new Cookie("password", password);
				nameCookie.setMaxAge(60 * 60 * 24 * Integer.parseInt(request.getParameter("auto")));
				passwordCookie.setMaxAge(nameCookie.getMaxAge());
				// response代表响应
				response.addCookie(nameCookie);
				response.addCookie(passwordCookie);
				response.sendRedirect("/xixi/private/home.jsp");
			} else
			{
				request.setAttribute("error", "登录失败");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}

		}

		else if (status.equals("auto"))
		{
			if (request.getCookies() != null)
			{
				Cookie[] cookies = request.getCookies();

				for (Cookie cookie : cookies)
				{
					if (cookie.getName().equals("name"))
					{
						request.setAttribute("name", cookie.getValue());
					}
					if (cookie.getName().equals("password"))
					{
						request.setAttribute("password", cookie.getValue());
					}
				}
			}

			request.getRequestDispatcher("/login.jsp").forward(request, response);

		}
	}

}

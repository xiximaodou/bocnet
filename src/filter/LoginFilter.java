package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class LoginFilter implements Filter
{

	private Logger logger = Logger.getLogger(this.getClass());

	public void destroy()
	{

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		if (session.getAttribute("name") != null)
		{
			filter.doFilter(request, response);
		} else
		{
			request.setAttribute("error", "∑«∑®∑√Œ ");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			logger.info("------Invalid access! Request:"+request.toString());
		}

	}

	public void init(FilterConfig arg0) throws ServletException
	{
		logger.info("------LoginFilter.init");
	}

}

package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.log4j.Logger;

public class EnCodeFilter implements Filter
{
	private Logger logger = Logger.getLogger(this.getClass());

	public void destroy()
	{
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException
	{
		logger.info("------EnCodeFilter.doFilter");
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		filter.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException
	{
		logger.info("------EnCodeFilter.init");
	}

}

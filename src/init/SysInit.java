package init;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class SysInit extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger=Logger.getLogger(this.getClass());

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		String prefix = getServletContext().getRealPath("/");
		String file = getInitParameter("sysconf");
		if (file != null)
		{
			SysConf.setSysConfigFile(prefix + file);
		}
		logger.info("------SysConf");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
	}
}
package init;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class DBInit extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger=Logger.getLogger(this.getClass());
 
	public void init(ServletConfig config) throws ServletException 
	 {
	  super.init(config);
	  String prefix = getServletContext().getRealPath("/");
	  String file = getInitParameter("db"); 
	  logger.info("------DB Initialing");
	  if (file != null) {
	   DBConnection.setDbconfig(prefix + file);
	  }
	 }

	 protected void doGet(HttpServletRequest request,
	   HttpServletResponse response) throws ServletException, IOException {
	 }
	}
package ser;

import init.SysConf;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jspsmart.upload.SmartUpload;
public class ServletDownload extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	 final public void init(ServletConfig config) throws ServletException {
	  this.config = config;
	 }
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  SmartUpload mySmartUpload = new SmartUpload();
	  try {
		  //初始化
	   mySmartUpload.initialize(config, request, response);
	   //设置不自动打开
	   mySmartUpload.setContentDisposition(null);
	   mySmartUpload.downloadFile(SysConf.getTmpDir()+"workbook.xlsx");
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	 }
	 protected void doPost(HttpServletRequest request,
	   HttpServletResponse response) throws ServletException, IOException {
	  // TODO Auto-generated method stub
	  doGet(request, response);
	 }
	}

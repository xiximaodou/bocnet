package ser;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jspsmart.upload.SmartUpload;
/**
 * �������ϴ�servlet
 * @author 2009-3-4  ת�� http://www.blogjava.net/hijackwust/archive/2007/08/22/138598.html
 *
 */
public class ServletUpload extends HttpServlet {
 private ServletConfig config;
 final public void init(ServletConfig config) throws ServletException {
  this.config = config;
 }
 
 protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  PrintWriter out = response.getWriter();
  out.println("<HTML>");
  out.println("<BODY BGCOLOR='white'>");
  out.println("<H1>jspSmartUpload : Servlet Sample</H1>");
  out.println("<HR>");
  // ��������
  int count = 0;
  //����һ��SmartUpload��
  SmartUpload mySmartUpload = new SmartUpload();
  try {
		//��ʼ��   
	    mySmartUpload.initialize(config, request, response);
	   //�ϴ�
	   mySmartUpload.upload();
	   for (int i = 0; i < mySmartUpload.getFiles().getCount(); i++) {
	    com.jspsmart.upload.File myfile = mySmartUpload.getFiles().getFile(i);
	    String fileName = myfile.getFileName();
	    //����
	    count = mySmartUpload.save("/upload");
	    //count = mySmartUpload.save(null);
	   }
	   out.println(count + " file uploaded.");
  } catch (Exception e) {
	   out.println("Unable to upload the file.<br>");
	   out.println("Error : " + e.toString());
  }
  //ͨ�� ����    mySmartUpload.getRequest().getParameter(arg0);�����Ի�ȡ�������ķ�file���͵�����ֵ��
  out.println("</BODY>");
  out.println("</HTML>");
 }
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doGet(request, response);
 }
}

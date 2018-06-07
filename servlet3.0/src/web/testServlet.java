package web;
/**
 * ע��ʽServlet
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(description="",
displayName="",
initParams={@WebInitParam(name="username",value="Tom"),@WebInitParam(name="age",value="18")},
loadOnStartup=1,
name="testServlet",
value={"/testServlet","/helloServlet"} //urlPatterns={"/testServlet","/helloServlet"}
)
/*
 * name ==<serlvet-name>
 * value/urlPatterns����ֻ��дһ��==<url-pattern>   //urlPatterns={"/testServlet","/helloServlet"},
 * loadOnStartup==<load-on-startup>
 * initParams==  <init-param>
 * description==<description>
 * displayName==<display-name>
 */
@MultipartConfig(
		
		)
/*
 * ��ע����Ҫ��Ϊ�˸��� Servlet 3.0 �� HttpServletRequest �ṩ�Ķ��ϴ��ļ���֧�֡���ע���ע�� Servlet ���棬�Ա�ʾ�� Servlet ϣ������������ MIME ������ multipart/form-data�����⣬�����ṩ�������������ڼ򻯶��ϴ��ļ��Ĵ����������£�

�� 5. @MultipartConfig �ĳ�������
������ ���� �Ƿ��ѡ ���� 
fileSizeThreshold  int  �� �����������ڸ�ֵʱ�����ݽ���д���ļ��� 
location  String  �� ������ɵ��ļ���ַ�� 
maxFileSize  long  �� �����ϴ����ļ����ֵ��Ĭ��ֵΪ -1����ʾû�����ơ� 
maxRequestSize  long  �� ��Ը� multipart/form-data ��������������Ĭ��ֵΪ -1����ʾû�����ơ� 

 */
public class testServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("");
	}
}

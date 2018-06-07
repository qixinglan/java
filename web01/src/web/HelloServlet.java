package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




public class HelloServlet extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		//����һ����Ϣͷcontent-type���������
		//���ص���������
		response.setContentType("text/html");
		//ͨ����Ӧ����response)��������
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		session.setAttribute("name", "a");
		session.setAttribute("age", 18);
		out.println("<div style='color:red;font-size:60px;font-style:italic'>"
		+session.getAttribute("name")+session.getAttribute("age")+"</div>");
		out.close();
	}
}

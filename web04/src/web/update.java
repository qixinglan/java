package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class update extends HttpServlet{
	static String oname;
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String name=new String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
		oname=name;
		String salary=request.getParameter("salary");
		String age=request.getParameter("age");
		PrintWriter out=response.getWriter();
		out.println("<form action='fix' method='post'>");
		out.println("������<input value='"+name+"' name='name'><br>");
		out.println("нˮ:<input value='"+salary+"' name='salary'><br>");
		out.println("����:<input value='"+age+"' name='age'><br>");
		out.println("<input type='submit' value='�ύ�޸�'>");
		out.println("</form>");
	}
}

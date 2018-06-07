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
		//设置一个消息头content-type告诉浏览器
		//返回的数据类型
		response.setContentType("text/html");
		//通过响应对象（response)获得输出流
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		session.setAttribute("name", "a");
		session.setAttribute("age", 18);
		out.println("<div style='color:red;font-size:60px;font-style:italic'>"
		+session.getAttribute("name")+session.getAttribute("age")+"</div>");
		out.close();
	}
}

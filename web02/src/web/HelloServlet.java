package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class HelloServlet extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");//放在第一行
		String username=request.getParameter("username");
		System.out.println(username);
		String citys[]=request.getParameterValues("city");
		//设置一个消息头content-type告诉浏览器
		//返回的数据类型
		response.setContentType("text/html;charset=utf-8");
		//通过响应对象（response)获得输出流
		PrintWriter out=response.getWriter();
		out.println("<div style='color:red;font-size:60px;font-style:italic'>Hello"+username+"</div>");
		if(citys==null){
			out.println("<h1>no city</h1>");
		}
		else
		for(int i=0;i<citys.length;i++){
			out.println("<h1>"+citys[i]+"</h1>");
		}
		
		out.close();
	}
}

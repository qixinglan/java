package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetData2 extends HttpServlet{
	public void service(HttpServletRequest request,
			HttpServletResponse response)throws ServletException,IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("name"); 
		byte[] b=name.getBytes("iso-8859-1");
		name=new String(b, "utf-8");
		out.print(name);
		out.close();
	}
}

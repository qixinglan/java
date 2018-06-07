package web;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReqInfoServlet extends HttpServlet{
	public void service(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException,IOException{
		//Enumeration���������飬���ϵ�ö������
		Enumeration e=request.getHeaderNames();
		while(e.hasMoreElements()){
			String name=e.nextElement().toString();
			System.out.println(name+":"+request.getHeader(name));
		}
	}
}

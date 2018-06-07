package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class findCookie extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Cookie[] cookie=request.getCookies();
		if(cookie!=null){
			for(int i=0;i<cookie.length;i++){
				out.println(cookie[i].getName());
				out.println(cookie[i].getValue());
			}
		}
		else{
			out.print("Ã»ÓÐcookie");
		}
		out.close();
	}

}

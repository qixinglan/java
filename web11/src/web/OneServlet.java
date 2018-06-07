package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OneServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		
		Integer a=((Integer)session.getAttribute("count"));
		if(a==null){
			a=1;
		}
		else{
			a++;
		}
		session.setAttribute("count", a);
		out.print("<h1>ÄãÊÇµÚ"+a+"<h1>");
		out.close();
	}

}

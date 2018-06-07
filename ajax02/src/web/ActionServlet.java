package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset:utf-8");
		PrintWriter out=response.getWriter();
		String uri=request.getRequestURI();
		String action=uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		if(action.equals("/check")){
//			//模拟出错
//			if(1==1){
//				
//				throw new ServletException();
//			}
			String uname=request.getParameter("username");
			System.out.println("uname:"+uname);
			if("李白".equals(uname)){
				System.out.println("1");
				out.println("username already usered");
			}
			else{
				System.out.println("2");
				out.println("may use");
			}
		}
	}

}

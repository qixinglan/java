package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class actionServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String uri=request.getRequestURI();
		String action=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		if(action.equals("/check")){
			String username=request.getParameter("username");
			System.out.println(username);
			try {
				Thread.sleep(2000);//模拟查询时间
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			if(1==1){
//				throw new ServletException();//模拟系统异常
//			}
		System.out.println(new String(username.getBytes("ISO-8859-1"),"GBK"));
			if("张三".equals(new String(username.getBytes("ISO-8859-1"),"GBK"))){
				out.println("该用户名已被注册");
			}
			else{
				out.println("可以使用");
			}
		}
		out.close();
	}

}

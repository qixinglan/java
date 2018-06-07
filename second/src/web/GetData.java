package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetData extends HttpServlet{
	public void service(HttpServletRequest request,
			HttpServletResponse response)throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("name"); 
		String pwd=request.getParameter("pwd");
		//获取复选框提交的多个同name的值
		String[] hobbys=request.getParameterValues("t1");
		if(hobbys!=null){
			for(String hobby:hobbys){
				out.print(hobby+"&nbsp;");
			}
		}
		//获取单选按钮的值
		String sex=request.getParameter("s1"); 
		out.print("name:"+name+"<br>"+"pwd:"+pwd+"<br>"+"sex:"+sex);
		out.close();
	}
}

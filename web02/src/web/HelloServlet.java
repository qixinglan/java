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
		request.setCharacterEncoding("utf-8");//���ڵ�һ��
		String username=request.getParameter("username");
		System.out.println(username);
		String citys[]=request.getParameterValues("city");
		//����һ����Ϣͷcontent-type���������
		//���ص���������
		response.setContentType("text/html;charset=utf-8");
		//ͨ����Ӧ����response)��������
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

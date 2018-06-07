package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DelUser extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String name=new String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
		System.out.println(name);
		Connection con=null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jsd1403","jsd1403");
			PreparedStatement pre=con.prepareStatement("delete from emp1 where name='"+name+"'");
			//pre.setString(1, name);
			pre.executeUpdate();
			response.sendRedirect("se");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h1>服务器繁忙，请稍后重试</h1>");
			// TODO: handle exception
		}finally{
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

package web;
import javax.servlet.*;
import javax.servlet.http.*;

import oracle.jdbc.proxy.annotation.Pre;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UpdateEmpServlet extends HttpServlet{
	public void service(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		double salary=Double.parseDouble(request.getParameter("salary"));
		int age=Integer.parseInt(request.getParameter("age"));
		Connection con=null;
		PreparedStatement ps=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jsd1403","jsd1403");
			ps=con.prepareStatement("update t_emp set name="+"'"+name+"'"+",salary="+salary+",age="+age+"where id="+id);
			ps.executeUpdate();
			response.sendRedirect("list");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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

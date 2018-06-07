package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class listemp extends HttpServlet {
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jsd1403","jsd1403");
			ps=con.prepareStatement("select * from t_emp");
			ResultSet rs=ps.executeQuery();
			out.println("<table border='1' ><caption>员工列表</caption><tr><th>ID</th><th>姓名</th><th>薪水</th><th>年龄</th></tr>");
			while(rs.next()){
				out.print("<tr><td>"+rs.getInt(1)+"</td>");
				out.print("<td>"+rs.getString(2)+"</td>");
				out.print("<td>"+rs.getInt(3)+"</td>");
				out.print("<td>"+rs.getInt(4)+"</td>");
				out.print("<td><a href='fixemp?id="+rs.getInt(1)+"'"+">修改</a>");
				out.print("<td><a href='delemp?id="+rs.getInt(1)+"'"+">删除</a></tr>");
			}
			out.print("</table>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}

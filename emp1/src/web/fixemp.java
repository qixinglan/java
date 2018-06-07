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

public class fixemp extends HttpServlet{
	public void server(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int id=Integer.parseInt(request.getParameter("id"));
		PrintWriter out=response.getWriter();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("oracle;jdbc;OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:@127.0.0.1:1521:xe","jsd1403","jsd1403");
			ps=con.prepareStatement("select * from t_emp where id="+id);
			rs=ps.executeQuery();
			out.print("编号："+id+"<br>");
			out.print("<form action='loademp' method='post'><fieldset>");
			out.print("姓名：<input name='name' value="+"'"+rs.getString(2)+"'"+"/>");
			out.print("薪水：<input name='name' value="+"'"+rs.getInt(3)+"'"+"/>");
			out.print("年龄：<input name='name' value="+"'"+rs.getInt(4)+"'"+"/>");
			out.print("<input type=’submit‘ value='确认修改'/>");
			out.print("</fieldset></form>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

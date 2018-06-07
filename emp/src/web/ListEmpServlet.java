package web;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ListEmpServlet extends HttpServlet{
	public void service(HttpServletRequest request,
			HttpServletResponse response)
	throws ServletException,IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jsd1403","jsd1403");
			ps=con.prepareStatement("select * from t_emp");
			rs=ps.executeQuery();
			out.print("<table border='2'bordercolor='red'><caption>员工列表</caption><th>编号</th><th>姓名</th><th>薪水</th><th>年龄</th>");
			while(rs.next()){
				out.print("<tr>");
				out.print("<td>"+rs.getInt("id")+"</td>");
				out.print("<td>"+rs.getString("name")+"</td>");
				out.print("<td>"+rs.getDouble("salary")+"</td>");
				out.print("<td>"+rs.getInt("age")+"</td>");
				out.print("<td>"+"<a  href='del?id="+rs.getInt("id")+"'"+">删除</a></td>");
				out.print("<td>"+"<a  href='load?id="+rs.getInt("id")+"'"+">修改 </a></td>");
				out.print("</tr>");
			}
			out.print("</table>");
		}catch (Exception  e) {
			// TODO Auto-generated catch block
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

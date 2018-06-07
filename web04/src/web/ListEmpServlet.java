package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connect.connections;

public class ListEmpServlet extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<h1>员工表</h1><table border='1' width='60%'><tr><th>姓名</th><th>薪水</th><th>年龄</th></tr>");
		Connection con=null;
		try {
			
			con=connections.openConnection1();
			PreparedStatement prep=con.prepareStatement("SElect * from EMP1")	;
			ResultSet rs=prep.executeQuery();
			while(rs.next()){
				String name=rs.getString("name");
				String salary=rs.getString("salary");
				String age=rs.getString("age");
				out.println("<tr>"+"<td>"+rs.getString("name")
						+"</td>"+"<td>"+rs.getString("salary")+
						"</td>"+"<td>"+rs.getString("age")+"</td>"+
						"<td><a href='del?name="+name+"'"+">删除</a>" +
								"<a href='update?name="+name+"&salary="+salary+"&age="+age+"'>修改</a></td>"+"</tr>");
			}
			out.println("</table>");
			out.println("<a href='addEmp.html'>添加员工</a>");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// TODO: handle exception
		}catch(Exception e){
			e.printStackTrace();
			out.println("<h1>系统繁忙，请稍后重试</h1>");
		}
		finally{
			if(con!=null){
				try{
					//一般conn。close（）ResultSet，Statement也会自动关闭
					//但是如果用了连接池，conn.close,链接并没有真正关闭，需要用ResultSet，Statement的close方法来关闭。
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		out.close();
		
	}
}

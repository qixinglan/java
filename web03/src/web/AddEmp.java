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

public class AddEmp extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username=request.getParameter("username");
		String salary=request.getParameter("salary");
		String age=request.getParameter("age");
		PrintWriter out=response.getWriter();
		out.println("<h1>姓名："+username+"薪水:"+salary+"年龄:"+age+"</h1>");
		
		//使用JDBC链接数据库'
		Connection conn=null;
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jsd1403","jsd1403");
			PreparedStatement prep=conn.prepareStatement("insert into Emp1 values(?,?,?)");
			prep.setString(1, username);
			prep.setDouble(2, Double.parseDouble(salary));
			prep.setInt(3, Integer.parseInt(age));
			prep.executeUpdate();
//			out.println("<h1>提交成功</h1>");
//			out.println("<a href='se'>员工表</a>");
			response.sendRedirect("se");//重定向
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			//异常发生后，第一步生成日志
			//记日志，一般记录在文件上，比如使用一些日志工具（log4j）
			
			e.printStackTrace();//这也是一种记日志的方式
			//第二步，看异常能否恢复，如（系统异常：不是程序的问题，比如数据库服务停止，网络中断
			//）不能恢复，要提示用户稍后重试
			//如果能够恢复（即应用异常）要采取相应的措施恢复。
			out.println("<h1>系统繁忙，请稍后重试</h1>");
		}
		finally{
			if(conn!=null){
				try{
					//一般conn。close（）ResultSet，Statement也会自动关闭
					//但是如果用了连接池，conn.close,链接并没有真正关闭，需要用ResultSet，Statement的close方法来关闭。
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		out.close();
		
		
	}
	

}

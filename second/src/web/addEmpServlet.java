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

public class addEmpServlet extends HttpServlet{
	public void service(HttpServletRequest request,
			HttpServletResponse response)
	throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String name=request.getParameter("name");
		double salary=Double.parseDouble(request.getParameter("salary"));
		int age=Integer.parseInt(request.getParameter("age"));
		PrintWriter out=response.getWriter();
		out.print("姓名:"+name+"<br>"+"薪水："+salary+"<br>"+"年龄："+age+"<br>");
		// 放入数据库
		Connection con=null;
		PreparedStatement ps=null;
		try {
			//1加载驱动
			Class.forName("oracle.jdbc.OracleDriver");
			//2获取链接
			con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jsd1403","jsd1403");
			ps=con.prepareStatement("insert into t_emp values(emp_id_seq.nextval,?,?,?)");
			ps.setString(1,name);
			ps.setDouble(2,salary);
			ps.setInt(3,age);
			ps.executeUpdate();
			out.print("保存成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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

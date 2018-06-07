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
/**
 * 1获取参数中的id
 * 2.根据ID查询员工信息
 * 3.将要修改的数据以表单的形式显示给用户
 * @author asus
 *
 */
public class LoadEmpServlet extends HttpServlet{
	public void service(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();//注意先后顺序
		int id=Integer.parseInt(request.getParameter("id"));
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jsd1403","jsd1403");
			ps=con.prepareStatement("select * from t_emp where id="+id);
			rs=ps.executeQuery();
			out.print("<html><head></head><body><form action='update' method='post'>");
			while(rs.next()){
				String name=rs.getString("name");
				double salary=rs.getDouble("salary");
				int age=rs.getInt("age");
				//以表单形式显示
				out.println("编号："+id+"<br>");
				//表单隐藏域
				out.print("<input type='hidden' name='id' value='"+id+"'/>");
				out.print("<input name='name' value='"+name+"'/><br>");
				out.print("<input name='salary' value='"+salary+"'/><br>");
				out.print("<input name='age' value='"+age+"'/><br>");
			}
			out.print("<input type='submit' value='保存修改'/>");
			out.print("</form></body></html>");
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

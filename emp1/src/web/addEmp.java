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

public class addEmp extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException
	{request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	String name=request.getParameter("name");
	int salary=Integer.parseInt(request.getParameter("salary"));
	int age=Integer.parseInt(request.getParameter("age"));
	PrintWriter out=response.getWriter();
//	out.println("ÐÕÃû£º"+name+"ÄêÁä£º"+age+"Ð½Ë®£º"+salary);
	Connection con=null;
	try {
		Class.forName("oracle.jdbc.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jsd1403","jsd1403");
		PreparedStatement ps=con.prepareStatement("insert into t_emp values(emp_id_seq.nextval,"+"'"+name+"'"+","+salary+","+age+")");
		ps.executeUpdate();
		response.sendRedirect("listemp");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	}
}

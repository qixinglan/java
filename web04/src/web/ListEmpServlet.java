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
		out.println("<h1>Ա����</h1><table border='1' width='60%'><tr><th>����</th><th>нˮ</th><th>����</th></tr>");
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
						"<td><a href='del?name="+name+"'"+">ɾ��</a>" +
								"<a href='update?name="+name+"&salary="+salary+"&age="+age+"'>�޸�</a></td>"+"</tr>");
			}
			out.println("</table>");
			out.println("<a href='addEmp.html'>���Ա��</a>");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// TODO: handle exception
		}catch(Exception e){
			e.printStackTrace();
			out.println("<h1>ϵͳ��æ�����Ժ�����</h1>");
		}
		finally{
			if(con!=null){
				try{
					//һ��conn��close����ResultSet��StatementҲ���Զ��ر�
					//��������������ӳأ�conn.close,���Ӳ�û�������رգ���Ҫ��ResultSet��Statement��close�������رա�
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		out.close();
		
	}
}

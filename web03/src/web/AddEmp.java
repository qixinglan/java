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
		out.println("<h1>������"+username+"нˮ:"+salary+"����:"+age+"</h1>");
		
		//ʹ��JDBC�������ݿ�'
		Connection conn=null;
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jsd1403","jsd1403");
			PreparedStatement prep=conn.prepareStatement("insert into Emp1 values(?,?,?)");
			prep.setString(1, username);
			prep.setDouble(2, Double.parseDouble(salary));
			prep.setInt(3, Integer.parseInt(age));
			prep.executeUpdate();
//			out.println("<h1>�ύ�ɹ�</h1>");
//			out.println("<a href='se'>Ա����</a>");
			response.sendRedirect("se");//�ض���
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			//�쳣�����󣬵�һ��������־
			//����־��һ���¼���ļ��ϣ�����ʹ��һЩ��־���ߣ�log4j��
			
			e.printStackTrace();//��Ҳ��һ�ּ���־�ķ�ʽ
			//�ڶ��������쳣�ܷ�ָ����磨ϵͳ�쳣�����ǳ�������⣬�������ݿ����ֹͣ�������ж�
			//�����ָܻ���Ҫ��ʾ�û��Ժ�����
			//����ܹ��ָ�����Ӧ���쳣��Ҫ��ȡ��Ӧ�Ĵ�ʩ�ָ���
			out.println("<h1>ϵͳ��æ�����Ժ�����</h1>");
		}
		finally{
			if(conn!=null){
				try{
					//һ��conn��close����ResultSet��StatementҲ���Զ��ر�
					//��������������ӳأ�conn.close,���Ӳ�û�������رգ���Ҫ��ResultSet��Statement��close�������رա�
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		out.close();
		
		
	}
	

}

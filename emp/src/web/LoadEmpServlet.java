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
 * 1��ȡ�����е�id
 * 2.����ID��ѯԱ����Ϣ
 * 3.��Ҫ�޸ĵ������Ա�����ʽ��ʾ���û�
 * @author asus
 *
 */
public class LoadEmpServlet extends HttpServlet{
	public void service(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();//ע���Ⱥ�˳��
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
				//�Ա���ʽ��ʾ
				out.println("��ţ�"+id+"<br>");
				//��������
				out.print("<input type='hidden' name='id' value='"+id+"'/>");
				out.print("<input name='name' value='"+name+"'/><br>");
				out.print("<input name='salary' value='"+salary+"'/><br>");
				out.print("<input name='age' value='"+age+"'/><br>");
			}
			out.print("<input type='submit' value='�����޸�'/>");
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

package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FirstJDBC {
//	private static final String DRIVER="oracle.jdbc.OracleDriver";
//	private static final String URL="jdbc:oracle:thin:@localhost:1521:xe";
//	private static final String USER="jsd1403";
//	private static final String PWD="jsd1403";
	public static void main(String[] args) throws Exception{
		//��������
		
//		Class.forName(DRIVER);//Driver driver=new OracleDriver();DriverManager.registerDriver(driver);
		//��������
//		Connection con=DriverManager.getConnection(URL,USER,PWD);
		Connection con=DBUtils.getConnection();
		System.out.println(con);
		//����sql��䵽���ݿ�ִ�У���ý��
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from emp");
		//��ʾ�����
		System.out.println("id\t"+"name");
		for(;rs.next();){
			System.out.println(rs.getString(1)+"\t"+rs.getString(2));
		}
		//�ر�����
//		con.close();
		DBUtils.closeConnection(con);
		
		
//		try{
//			Class.forName("oracle.jdbc.OracleDriver");
//			String url="jdbc:oracle:thin:@localhost:1521:xe";
//			Connection con=DriverManager.getConnection(url,"jsd1403","jsd1403");
//			Statement stmt=con.createStatement();
//			ResultSet res=stmt.executeQuery("Select id,name,deptno from emp");
//			while(res.next()){
//				System.out.println(res.getString("id"));
//			}
//		con.close();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
	}
}

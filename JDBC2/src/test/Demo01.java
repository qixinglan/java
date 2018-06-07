package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Demo01 {
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","jsd1403","jsd1403");
		int a=25;
		PreparedStatement ps=con.prepareStatement("select name from emp1 where age="+a);
		//ps.setInt(1, 1300);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			System.out.println(rs.getString("name"));
		}
		con.close();
	}
}

package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class demo03 {
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection con=DriverManager.getConnection(url,"jsd1403","jsd1403");
		Statement stmet=con.createStatement();
		int n=stmet.executeUpdate("delete from emp where val=3000");
		System.out.println(n);
		con.close();
	}
}

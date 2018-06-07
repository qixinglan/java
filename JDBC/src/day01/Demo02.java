package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo02 {
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		String url="jdbc:oracle:thin:@172.16.3.133:1521:xe";
		Connection con=DriverManager.getConnection(url, "jsd1403", "jsd1403");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("Select ename from S_emp");
		while(rs.next()){
			System.out.println(rs.getString("ename"));
		}
		con.close();
	}
}

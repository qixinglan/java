package day01;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import oracle.jdbc.driver.OracleDriver;

public class demo01 {
	public static void main(String[] args) throws Exception {
		Driver driver=new OracleDriver();
		DriverManager.registerDriver(driver);
		String url="jdbc:oracle:thin:@172.16.1.228:1521:xe";
		Connection con=DriverManager.getConnection(url, "jsd1403", "jsd1403");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select ename from emp");
		while(rs.next()){
			System.out.println(rs.getString("ename"));
		}
		con.close();
	}
	
}

package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Demo04 {
	public static void main(String[] args) throws Throwable {
		Scanner in=new Scanner(System.in);
		System.out.println(" ‰»Î√˚◊÷£∫");
		String name =in.next();
		//System.out.println(name);
		Connection con=Demo05.openConnection();
		PreparedStatement ps=con.prepareStatement("select ename,sal from emp where ename=?");
		ps.setString(1, name);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			System.out.println(rs.getString("ename")+"\t"+rs.getString("sal"));
		}
		con.close();
	}
}

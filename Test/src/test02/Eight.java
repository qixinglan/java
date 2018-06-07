package test02;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Eight {
	public static void main(String[] args) throws Exception{
		PrintStream ps1=new PrintStream("ps1.txt");
		System.setOut(ps1);
		System.out.println("aaaaa");
		
		
		ps1.close();
		
	}
}

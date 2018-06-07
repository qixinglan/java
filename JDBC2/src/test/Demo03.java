package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Demo03 {
	 static String url;
	 static String password;
	 static String user;
	 static String driver;
	static {
		Properties pro=new Properties();
		try {
			pro.load(new FileInputStream("db.properties"));
			url=pro.getProperty("url");
			password=pro.getProperty("password");
			user=pro.getProperty("user");
			driver=pro.getProperty("driver");
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
	}
	public static Connection openConnection() throws Exception{
		return DriverManager.getConnection(url,user,password);
	}
}

package day01;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Demo05 {
	private static String driver;
	private static String user;
	private static String url;
	private static String password;
	static{
		try {
			Properties props=new Properties();
			//从类路径加载文件
			props.load(Demo05.class.getClassLoader().getResourceAsStream("day01/db.properties"));
			driver=props.getProperty("driver");
			url=props.getProperty("url");
			user=props.getProperty("user");
			password=props.getProperty("password");
			Class.forName(driver);
			// TODO Auto-generated catch block
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection openConnection() throws Exception{
		return DriverManager.getConnection(url,user,password);
	}
	public static void main(String[] args) {
		Connection con=null;
		try {
			con = openConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(con);
	}
}

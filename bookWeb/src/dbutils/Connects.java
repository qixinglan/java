package dbutils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;


public class Connects {
	private static String driver;
	private static String url;
	private static String password;
	private static String user;
	private static BasicDataSource ds;
	
	static{	
			Properties pro=new Properties();
			ds=new BasicDataSource();
			try {
				pro.load(Connects.class.getResourceAsStream("db.properties"));
				driver=pro.getProperty("driver");
				System.out.println(driver);
				url=pro.getProperty("url");
				user=pro.getProperty("user");
				password=pro.getProperty("password");
				
				ds.setDriverClassName(driver);
				ds.setUrl(url);
				ds.setUsername(user);
				ds.setPassword(password);
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
	}
	
	public  static Connection getConnection() throws Exception{
		return ds.getConnection();
	}
	
	
}

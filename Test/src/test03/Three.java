package test03;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class Three {
	static BasicDataSource bds;
	static{
		bds=new BasicDataSource();
		Properties p=new Properties();
		FileInputStream fis;
		try {
//			fis = new FileInputStream("src/test03/oraclejdbc.properties");
//			p.load(fis);
			p.load(Three.class.getClassLoader().getResourceAsStream("test03/oraclejdbc.properties"));
			bds.setUrl(p.getProperty("uri"));
			bds.setUsername(p.getProperty("user"));
			bds.setPassword(p.getProperty("password"));
			bds.setDriverClassName(p.getProperty("driver"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static Connection getConnection() throws SQLException{
		return bds.getConnection();
	}
	public static void main(String[] args) {
		try {
			System.out.println(Three.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

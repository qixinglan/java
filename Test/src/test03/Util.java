package test03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class Util {
	static BasicDataSource bds;
	static{
		bds=new BasicDataSource();
		Properties p=new Properties();
		try {
			p.load(new FileInputStream("src/test03/oraclejdbc.properties"));
			bds.setUrl(p.getProperty("uri"));
			bds.setPassword(p.getProperty("password"));
			bds.setDriverClassName(p.getProperty("driver"));
			bds.setUsername(p.getProperty("user"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static Connection getConnection() throws SQLException{
		Connection con=bds.getConnection();
		con.setAutoCommit(false);
		return con;
	}
	
}

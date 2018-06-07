package test04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class Seven {
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
	public static void main(String[] args) throws Exception {
//		Class.forName("oracle.jdbc.OracleDriver");
//		Connection con=DriverManager.getConnection
//		("jdbc:oracle:thin:@127.0.0.1:1521:xe","jsd1403","jsd1403");
		Connection con=bds.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from cost where cost_id=?");
		ps.setInt(1, 1);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			System.out.println(rs.getString(2));
		}
		con.close();
	}
}

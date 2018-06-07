package test03;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class Two {
	static BasicDataSource bds;
	static{
		bds=new BasicDataSource();
		bds.setDriverClassName("oracle.jdbc.OracleDriver");
		bds.setUsername("jsd1403");
		bds.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
		bds.setPassword("jsd1403");
	}
	static Connection getConnection() throws SQLException{
		return bds.getConnection();
	}
}

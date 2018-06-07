package day02;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class TestDBCP {
	public static void main(String[] args) throws Exception {
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
		ds.setUsername("jsd1403");
		ds.setPassword("jsd1403");
		ds.setInitialSize(6);
		ds.setMaxActive(6);
		ds.setMaxIdle(5);
		Connection con0=ds.getConnection();
		Connection con1=ds.getConnection();
		Connection con2=ds.getConnection();
		Connection con3=ds.getConnection();
		Connection con4=ds.getConnection();
		Connection con5=ds.getConnection();
		con0.close();
		System.out.println(ds.getNumIdle());
		System.out.println(ds.getNumActive());
		Connection con6=ds.getConnection();
		System.out.println(ds.getNumIdle());
		System.out.println(con0);
	}
}

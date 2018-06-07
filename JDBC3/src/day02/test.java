package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class test {
	public static void main(String[] args) throws Exception {
		Properties p=new Properties();
		p.load(test.class.getClassLoader().getResourceAsStream("db.properties"));
		BasicDataSource bds=new BasicDataSource();
		bds.setDriverClassName(p.getProperty("driver"));
		bds.setUrl(p.getProperty("url"));
		bds.setUsername(p.getProperty("user"));
		bds.setPassword(p.getProperty("pwd"));
		bds.setInitialSize(Integer.parseInt(p.getProperty("initialSize")));
		Connection con=bds.getConnection();
		con.createStatement().executeQuery(
				"select salary from emp where name='CLARK'");
		con.createStatement().executeQuery(
				"select salary from emp where name='a' OR 'b'='b'");
		PreparedStatement ps=con.prepareStatement(
				"select salary from emp where name=?");
		ps.setString(1,"CLARK");
		ps.executeQuery();
		ps.setString(1, "a OR 'b'='b'");
		ps.executeQuery();
		con.close();
	}
}

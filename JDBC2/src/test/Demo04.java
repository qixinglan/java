package test;

import java.sql.Connection;

import org.apache.commons.dbcp.BasicDataSource;

public class Demo04 {
	public static void main(String[] args) throws Exception {
		BasicDataSource bds=new BasicDataSource();
		Demo03 d=new Demo03();
		bds.setDriverClassName(d.driver);
		bds.setUrl(d.url);
		bds.setPassword(d.password);
		bds.setUsername(d.user);
		Connection con=bds.getConnection();
		System.out.println(con);
	}
}

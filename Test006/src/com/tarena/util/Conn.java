package com.tarena.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
@Repository
public class Conn {
	@Value("#{db.url}")
	 String url;
	@Value("#{db.user})")
	 String user;
	@Value("#{db.driver}")
	String driver;
	@Value("#{db.pwd}")
	 String pwd;
	
	static BasicDataSource  ds;
	
	public  Connection getConnection() throws SQLException{
		ds=new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(pwd);
		return ds.getConnection();
	}
	public static void closeConnection(Connection con) throws SQLException{
		con.close();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}

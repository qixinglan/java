package com.tarena.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {
	String driver;
	String url;
	String user;
	String pwd;
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) throws ClassNotFoundException {
		this.driver = driver;
		Class.forName(driver);
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Connection getConnection() throws SQLException{
		Connection con=DriverManager.getConnection(url,user,pwd);
		return con;		
	}
}

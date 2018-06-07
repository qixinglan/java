package com.tarena.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcDateSource implements Serializable{
	private String driver;
	private String url;
	private String user;
	private String pwd;
	public Connection getComncetion() throws SQLException{
		Connection con=DriverManager.getConnection(url,user,pwd);
		return con;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	@Override
	public String toString() {
		return "JdbcDateSource [driver=" + driver + ", url=" + url + ", user="
				+ user + ", pwd=" + pwd + "]";
	}
	
}

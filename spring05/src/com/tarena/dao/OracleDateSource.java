package com.tarena.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.sun.org.apache.xpath.internal.operations.Or;

@Repository
public class OracleDateSource implements Serializable{
	String driver;
	@Value("#{db.url}")
	String url;
	@Value("#{db.user}")
	String user;
	@Value("#{db.pwd}")
	String pwd;
	public Connection getConnection() throws SQLException{
		Connection con=DriverManager.getConnection(url,user,pwd);
		return con;
	}
	public void close(Connection con){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getDriver() {
		return driver;
	}
	@Value("#{db.driver}")
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

}

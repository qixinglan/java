package com.tarena.jdbc;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class DateSource implements Serializable{
	private String driver;
	@Value("#{db.url}")
	private String url;
	@Value("#{db.user}")
	private String user;
	@Value("#{db.pwd}")
	private String pwd;
	public Connection getConnection() throws SQLException{
		Connection con=DriverManager.getConnection(url,user,pwd);
		return con;
	}
	public void close(Connection con) {
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public String getDriver() {
		return driver;
	}
	@Value("#{db.driver}")
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
	
}

package com.nci.dcs.common.sql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 编程调用c3p0
 * 
 * @author xuhua
 * 
 */
public class C3P0DBConnectionManager {

	/**
	 * 取得链接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		InitialContext ic;
		Connection connection = null;
		try {
			ic = new InitialContext();
			DataSource dataSource = (DataSource) ic
					.lookup("java:comp/env/jdbc/sfjjk");
			connection = dataSource.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
}

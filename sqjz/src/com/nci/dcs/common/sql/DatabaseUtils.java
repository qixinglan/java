package com.nci.dcs.common.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DatabaseUtils {
	private final static Logger log = LogManager.getLogger(DatabaseUtils.class); // 为子类定义的logger
	public static void close(ResultSet rs) {
		if(rs != null){
			try {
				rs.close();
				log.debug("关闭ResultSet成功");
			} catch (SQLException ex) {
				ex.printStackTrace();
				log.error(ex.getMessage());
			}
		}
	}

	public static void close(PreparedStatement ps) {
		if(ps != null){
			try {
				ps.close();
				log.debug("关闭PreparedStatement成功");
			} catch (SQLException ex) {
				ex.printStackTrace();
				log.error(ex.getMessage());
			}
		}
	}

	public static void close(Connection conn) {
		if(conn != null){
			try {
				conn.close();
				log.debug("关闭Connection成功");
			} catch (SQLException ex) {
				ex.printStackTrace();
				log.error(ex.getMessage());
			}
		}
	}
}

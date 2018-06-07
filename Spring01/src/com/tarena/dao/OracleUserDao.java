package com.tarena.dao;

import java.io.Serializable;

/**
 * 早Orale数据库实现用户实体的管理
 * 需要连接到数据库
 */
public class OracleUserDao implements Serializable{
	private JdbcDateSource dateSource;
    //有了bean属性：deteSource
	public JdbcDateSource getDateSource() {
		return dateSource;
	}

	public void setDateSource(JdbcDateSource dateSource) {
		this.dateSource = dateSource;
	}

	@Override
	public String toString() {
		return "OracleUserDao [dateSource=" + dateSource + "]";
	}
	
}

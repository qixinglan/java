package com.nci.dcs.common.sql;

/**
 * 数据访问工厂类
 * 
 * @author xuhua
 * 
 */
public class DatabaseAccessFactory {
	private static DatabaseAccessInterface databaseai = null;

	/**
	 * 取得数据库访问实例
	 * 
	 * @return
	 */
	public static DatabaseAccessInterface getDataAccessInstance() {
		if (databaseai == null) {
			databaseai = new DatabaseAccessDB2Impl();
		}
		return databaseai;
	}

}

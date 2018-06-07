package com.nci.dcs.common.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

/**
 * 数据库访问接口
 * 
 * @author Administrator
 * 
 */
public interface DatabaseAccessInterface {
	/**
	 * 根据sql声明语句直接执行
	 * 
	 * @param sqlStatement
	 * @throws SQLException
	 */
	public abstract void executeSQL(String sqlStatement) throws SQLException;

	/**
	 * 带参数sql的执行
	 * 
	 * @param sqlStatement
	 * @param parameters
	 * @throws SQLException
	 */
	public abstract void executeSQL(String sqlStatement, Object... parameters)
			throws SQLException;

	/**
	 * 带参数sql执行
	 * 
	 * @param sqlStatement
	 * @param parameters
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public abstract void executeSQL(String sqlStatement, List parameters)
			throws SQLException;

	/**
	 * 执行sql
	 * 
	 * @param sqlStatement
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public abstract List<HashMap> executeQuerySQL(String sqlStatement)
			throws SQLException;

	/**
	 * 带参数的sqk执行
	 * 
	 * @param sqlStatement
	 * @param parameters
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public abstract List<HashMap> executeQuerySQL(String sqlStatement,
			Object... parameters) throws SQLException;

	/**
	 * 带参数的sql执行
	 * 
	 * @param sqlStatement
	 * @param parameters
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public abstract List<HashMap> executeQuerySQL(String sqlStatement,
			List parameters) throws SQLException;

	/**
	 * 返回执行sql查询后的结果集
	 * 
	 * @param sqlStatement
	 * @return
	 * @throws SQLException
	 */
	public abstract ResultSet execute(Statement statement) throws SQLException;
	

	/**
	 * 返回执行sql查询后的Statement
	 * 
	 *  @param connection
	 * @param sqlStatement
	 * @param parameters
	 * @return
	 * @throws SQLException
	 */
	public abstract Statement returnExecuteQuerySQL(Connection connection,String sqlStatement,
			Object... parameters) throws SQLException;

	/**
	 * 返回执行sql查询后的Statement
	 * 
	 * @param connection
	 * @param sqlStatement
	 * @param parameters
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public abstract Statement returnExecuteQuerySQL(Connection connection,String sqlStatement,
			List parameters) throws SQLException;

	/**
	 * 关闭结果集
	 * 
	 * @param rs
	 * @throws SQLException
	 */
	public abstract void close(ResultSet rs) throws SQLException;
	/**
	 * 关闭Statement
	 * 
	 * @param statement
	 * @throws SQLException
	 */
	public abstract void close(Statement statement) throws SQLException;
	/**
	 * 关闭Connection
	 * 
	 * @param connection
	 * @throws SQLException
	 */
	public abstract void close(Connection connection) throws SQLException;

}

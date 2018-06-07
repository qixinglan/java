package com.nci.dcs.common.sql;

import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 直接sql访问db2实现类
 * 
 * @author xuhua
 * 
 */
public class DatabaseAccessDB2Impl implements DatabaseAccessInterface {
	private static Log log = LogFactory.getLog(DatabaseAccessDB2Impl.class);
	/**
	 * 执行sql
	 */
	public void executeSQL(String sqlStatement) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = C3P0DBConnectionManager.getConnection();
			connection.setAutoCommit(true);
			statement = connection.prepareStatement(sqlStatement);
			statement.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		} finally {
			try {
				try {
					statement.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
					log.error("执行slq方法时关闭 statement异常 ");
				}
				connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				log.error(ex.getMessage());
				throw ex;
			}
		}
		log.debug("退出执行sql方法");
		return;
	}

	/**
	 * 带参数的sql执行
	 */
	public void executeSQL(String sqlStatement, Object... parameters)
			throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = C3P0DBConnectionManager.getConnection();
			connection.setAutoCommit(true);
			statement = connection.prepareStatement(sqlStatement);
			// 获取执行数据
			if (parameters != null && parameters.length > 0) {
				for (int i = 0; i < parameters.length; i++) {
					statement.setObject(i + 1, parameters[i]);
				}
			}
			statement.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		} finally {
			try {
				try {
					statement.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
					log.error("执行slq方法时关闭 statement异常");
				}
				connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				log.error(ex.getMessage());
				throw ex;
			}
		}
		log.debug("退出执行sql方法");
		return;
	}

	/**
	 * 带参数sql执行
	 */
	@SuppressWarnings("unchecked")
	public void executeSQL(String sqlStatement, List parameters)
			throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = C3P0DBConnectionManager.getConnection();
			connection.setAutoCommit(true);
			statement = connection.prepareStatement(sqlStatement);
			// 获取执行数据
			int listsize = parameters.size();
			for (int i = 0; i < listsize; i++) {
				statement.setObject(i + 1, parameters.get(i));
			}
			statement.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		} finally {
			try {
				try {
					statement.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
					log.error("执行slq方法时关闭 statement异常");
				}
				connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				log.error(ex.getMessage());
				throw ex;
			}
		}
		log.debug("退出执行sql方法");
		return;
	}

	/**
	 * 返回sql查询结果
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap> executeQuerySQL(String sqlStatement)
			throws SQLException {
		List<HashMap> resultList = new ArrayList<HashMap>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = C3P0DBConnectionManager.getConnection(); // 获取数据库连接对象
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sqlStatement); // 获取statement对象
			statement.execute();
			rs = statement.getResultSet(); // 获取查询记录集
			HashMap rowItem;
			for (; rs.next(); resultList.add(rowItem)) {
				rowItem = new HashMap();
				int columnCount = rs.getMetaData().getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					Object cell = rs.getObject(i);
					if (cell == null) {
						cell = "";
					} else if (cell instanceof Blob) {
						try {
							InputStreamReader in = new InputStreamReader(
									((Blob) cell).getBinaryStream());
							char bufferString[] = new char[5000];
							int readCharCount = in.read(bufferString);
							StringBuffer bolbStringBuffer = new StringBuffer("");
							for (; readCharCount == 5000; readCharCount = in
									.read(bufferString)) {
								bolbStringBuffer.append(bufferString, 0,
										readCharCount);
							}
							if (readCharCount != -1) {
								bolbStringBuffer.append(bufferString, 0,
										readCharCount);
							}
							cell = bolbStringBuffer.toString();
							in.close();
						} catch (Exception ex) {
							ex.printStackTrace();
							log.error(ex.getMessage());
						}
					}
					rowItem.put(rs.getMetaData().getColumnName(i), cell);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		} finally {
			try {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
					log.error("执行sql查询时，关闭结果集异常");
				}
				try {
					statement.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
					log.error("执行sql查询时，关闭ｓｑｌstatement异常");
				}
				connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				log.error(ex.getMessage());
				throw ex;
			}
		}
		log.debug("返回记录数大小：" + resultList.size());
		log.debug("退出执行sql方法");
		return resultList;
	}

	/**
	 * 返回sql查询结果
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap> executeQuerySQL(String sqlStatement,Object parameters[]) throws SQLException {
		List<HashMap> resultList = new ArrayList<HashMap>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = C3P0DBConnectionManager.getConnection(); // 获取数据库连接对象
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sqlStatement); // 获取statement对象
			// 获取查询参数
			if (parameters != null && parameters.length > 0) {
				for (int i = 0; i < parameters.length; i++) {
					statement.setObject(i + 1, parameters[i]);
				}
			}
			statement.execute();
			rs = statement.getResultSet(); // 获取查询记录集
			HashMap rowItem;
			for (; rs.next(); resultList.add(rowItem)) {
				rowItem = new HashMap();
				int columnCount = rs.getMetaData().getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					Object cell = rs.getObject(i);
					if (cell == null) {
						cell = "";
					} else if (cell instanceof Blob) {
						try {
							InputStreamReader in = new InputStreamReader(
									((Blob) cell).getBinaryStream());
							char bufferString[] = new char[5000];
							int readCharCount = in.read(bufferString);
							StringBuffer bolbStringBuffer = new StringBuffer("");
							for (; readCharCount == 5000; readCharCount = in
									.read(bufferString)) {
								bolbStringBuffer.append(bufferString, 0,
										readCharCount);
							}
							if (readCharCount != -1) {
								bolbStringBuffer.append(bufferString, 0,
										readCharCount);
							}
							cell = bolbStringBuffer.toString();
							in.close();
						} catch (Exception ex) {
							ex.printStackTrace();
							log.error(ex.getMessage());
						}
					}
					rowItem.put(rs.getMetaData().getColumnName(i), cell);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		} finally {
			try {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
					log.error("执行sql查询时，关闭结果集异常");
				}
				try {
					statement.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
					log.error("执行sql查询时，关闭ｓｑｌstatement异常");
				}
				connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				log.error(ex.getMessage());
				throw ex;
			}
		}
		log.debug("返回记录数大小：" + resultList.size());
		log.debug("退出执行sql方法");
		return resultList;
	}

	/**
	 * 返回sql查询结果
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap> executeQuerySQL(String sqlStatement, List parameters)
			throws SQLException {
		List<HashMap> resultList = new ArrayList<HashMap>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		log.debug("进入sql查询方法");
		log.debug("sql查询语句 statement:" + sqlStatement);

		try {
			connection = C3P0DBConnectionManager.getConnection(); // 获取数据库连接对象
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sqlStatement); // 获取statement对象
			// 获取查询数据
			int listsize = parameters.size();
			for (int i = 0; i < listsize; i++) {
				statement.setObject(i + 1, parameters.get(i));

			}
			statement.execute();
			rs = statement.getResultSet(); // 获取查询记录集
			HashMap rowItem;
			for (; rs.next(); resultList.add(rowItem)) {
				rowItem = new HashMap();
				int columnCount = rs.getMetaData().getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					Object cell = rs.getObject(i);
					if (cell == null) {
						cell = "";
					} else if (cell instanceof Blob) {
						try {
							InputStreamReader in = new InputStreamReader(
									((Blob) cell).getBinaryStream());
							char bufferString[] = new char[5000];
							int readCharCount = in.read(bufferString);
							StringBuffer bolbStringBuffer = new StringBuffer("");
							for (; readCharCount == 5000; readCharCount = in
									.read(bufferString)) {
								bolbStringBuffer.append(bufferString, 0,
										readCharCount);
							}
							if (readCharCount != -1) {
								bolbStringBuffer.append(bufferString, 0,
										readCharCount);
							}
							cell = bolbStringBuffer.toString();
							in.close();
						} catch (Exception ex) {
							ex.printStackTrace();
							log.error(ex.getMessage());
						}
					}
					rowItem.put(rs.getMetaData().getColumnName(i), cell);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		} finally {
			try {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
					log.error("返回记录数大小：");
				}
				try {
					statement.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
					log.error("退出执行sql方法");
				}
				connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				log.error(ex.getMessage());
				throw ex;
			}
		}
		return resultList;
	}

	/**
	 * 返回执行sql查询后的结果集
	 * 
	 * @param sqlStatement
	 * @return
	 * @throws SQLException
	 */
	public ResultSet execute(Statement statement) throws SQLException {

		ResultSet rs = null;

		log.debug("进入sql查询方法");

		try {

			// 获取rs对象

			rs = statement.getResultSet(); // 获取查询记录集

		} catch (SQLException ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw ex;
		}
		return rs;
	}
	
	


	/**
	 * 返回执行sql查询后的Statement
	 * 
	 * @param connection
	 * @param sqlStatement
	 * @param parameters
	 * @return
	 * @throws SQLException
	 */
	public Statement returnExecuteQuerySQL(Connection connection,
			String sqlStatement, Object... parameters) throws SQLException {

		PreparedStatement statement = null;

		log.debug("进入sql查询方法");
		log.debug("sql查询语句 statement:" + sqlStatement);

		try {

			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sqlStatement); // 获取statement对象

			// 获取查询参数
			if (parameters != null && parameters.length > 0) {
				for (int i = 0; i < parameters.length; i++) {
					statement.setObject(i + 1, parameters[i]);
				}
			}
			statement.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw ex;
		}
		return statement;
	}

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
	public Statement returnExecuteQuerySQL(Connection connection,
			String sqlStatement, List parameters) throws SQLException {
		PreparedStatement statement = null;

		log.debug("进入sql查询方法");
		log.debug("sql查询语句 statement:" + sqlStatement);

		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sqlStatement); // 获取statement对象
			// 获取查询数据
			int listsize = parameters.size();
			for (int i = 0; i < listsize; i++) {
				statement.setObject(i + 1, parameters.get(i));

			}
			statement.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw ex;
		}
		return statement;
	}

	/**
	 * 关闭结果集
	 * 
	 * @param rs
	 * @throws SQLException
	 */
	public void close(ResultSet rs) throws SQLException {
		if(rs!=null)
		{
			log.debug("进入ResultSet关闭方法");
			try {
				rs.close();
				log.debug("关闭ResultSet成功");
			} catch (SQLException ex) {
				ex.printStackTrace();
				log.debug("关闭ResultSet失败");
				log.error(ex.getMessage());
				throw ex;
			}
		}
	}

	/**
	 * 关闭Statement
	 * 
	 * @param statement
	 * @throws SQLException
	 */
	public void close(Statement statement) throws SQLException {
		if(statement!=null)
		{
			log.debug("进入statement关闭方法");
			try {
				statement.close();
				log.debug("关闭statement成功");
			} catch (SQLException ex) {
				ex.printStackTrace();
				log.debug("关闭statement失败");
				log.error(ex.getMessage());
				throw ex;
			}
		}	
	}

	/**
	 * 关闭Connection
	 * 
	 * @param connection
	 * @throws SQLException
	 */
	public void close(Connection connection) throws SQLException {
		if(connection!=null)
		{
			log.debug("进入connection关闭方法");
			try {
				connection.close();
				log.debug("关闭connection成功");
			} catch (SQLException ex) {
				ex.printStackTrace();
				log.debug("关闭connection失败");
				log.error(ex.getMessage());
				throw ex;
			}
		}
	}

}

package com.nci.dcs.jzgl.fingerprint.dao;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.jzgl.fingerprint.model.FingerPrintCommpost;
@Repository
@Transactional
public class FingerPrintDao {
	public static Logger logger = LoggerFactory.getLogger(FingerPrintDao.class);
	private static Connection conn ;
	Statement stmt ;
	PreparedStatement pst;
	ResultSet rs ;
	private static String dbdriver=null;
	private static String dburl;
	private static String dbuser;
	private static String dbpswd;
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public  FingerPrintDao(){
		//初始化配置
		if(dbdriver ==null){
			Properties properties = new Properties();
			InputStream in = null;
			try {
				in = new BufferedInputStream(new FileInputStream(getClass()
						.getResource("/").getPath() + "fingerPrintConfig.properties"));
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage());
			}
			try {
				properties.load(in);
				dbdriver = properties.getProperty("dbdriver");
				dburl= properties.getProperty("dburl");
				dbuser = properties.getProperty("dbuser");
				dbpswd = properties.getProperty("dbpswd");
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		
	}
	
	/**
	 * 获取链接
	 * @return
	 * @throws SQLException
	 */
	public  static Connection getConnection() throws SQLException{
		if(conn != null && !conn.isClosed()){
			return conn;
		}else{
			try{
				Class.forName(dbdriver);
				conn = DriverManager.getConnection(dburl,dbuser,dbpswd);
				return conn;
			}catch(Exception e){
				System.out.println(e);
				logger.error(e.getMessage());
				return null; 
			} 
		} 
	}
	/**
	 * 关闭链接
	 * @throws SQLException
	 */
	public void close() throws SQLException{
		if(conn !=null &!conn.isClosed()){
			try{
				conn.close();
			}catch(Exception e){
				conn = null;
			} 
		}
	}
	/**
	 * 

	 * @param tasktype任务类型 1-增加设备 2-删除设备 3-下发人员 4-删除人员 5-上传人员
	 * @param params1 	当tasktype=1 params1为设备的机器号
						当tasktype=2 params1为设备的机器号
						当tasktype=3 params1为人员编号
						当tasktype=4 params1为人员编号
						当tasktype=5 params1为人员编号
	 * @param params2	当tasktype=1 params2为设备的ip地址
						当tasktype=2 params2为空
						当tasktype=3 params2为人员姓名
						当tasktype=4 params2为设备的机器号
						当tasktype=5 params2为设备的机器号
	 * @param params3 	当tasktype=1 params3为空
						当tasktype=2 params3为空
						当tasktype=3 params3为设备的机器号
						当tasktype=4 params3为空
						当tasktype=5 params3为空
	 * 
	 * @return
	 */
	public boolean addTask(int tasktype,String params1,String params2,String params3){
		try {
			getConnection();
			stmt = conn.createStatement();
			stmt.execute("insert into commtask (tasktype,params1,Params2,Params3,addtime,status)"
					+ "values("+tasktype+",'"+params1+"','"+params2+"','"+params3+"','"+sf.format(new Date())+"',0)");
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				stmt.close();
				return true;
			} catch (SQLException ex) {
				ex.printStackTrace();
				return false;
			}
		}
	}
	
	/**
	 * 获取服刑人员指纹记录
	 * @param personId
	 */
	public boolean getFingerPrint(String personId){
		return true;
	}
	/**
	 * 获取报道记录
	 * @param personId
	 * @return
	 */
	public boolean getReportLog(String personId){
		return true;
	}
	/**
	 * 
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String args[]) throws SQLException{
		FingerPrintDao dao = new FingerPrintDao();
		FingerPrintDao.getConnection();
		dao.addTask(1, "2", "121", "131");
	}

	public List<FingerPrintCommpost> getTask() throws SQLException {
		getConnection();
		pst = conn.prepareStatement("select postid,posttype,params1,Params2,Params3,Params4,addtime,status,finishtime from commpost where status=0");
		rs = pst.executeQuery();
		List<FingerPrintCommpost> res = new ArrayList();
		while(rs.next()){
			FingerPrintCommpost post = new FingerPrintCommpost();
			post.setPostid(rs.getInt(1));
			post.setPosttype(rs.getInt(2));
			post.setParams1(rs.getString(3));
			post.setParams2(rs.getString(4));
			post.setParams3(rs.getString(5));
			post.setParams4(rs.getString(6));
			post.setAddtime(rs.getDate(7));
			post.setStatus(rs.getInt(8));
			post.setFinishtime(rs.getDate(9));
			res.add(post);
		}
		rs.close();
		pst.close();
		return res;
	}

	public void updatePost(int postId,int status) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			pst = conn.prepareStatement("update commpost set status=?,finishtime=? where postid=?");
			pst.setInt(1, status);
			pst.setString(2, sdf.format(new Date()));
			pst.setInt(3, postId);
			pst.execute();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
} 
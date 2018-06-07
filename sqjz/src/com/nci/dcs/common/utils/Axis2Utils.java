package com.nci.dcs.common.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nci.dcs.common.sql.C3P0DBConnectionManager;
import com.nci.dcs.common.sql.DatabaseAccessFactory;

public class Axis2Utils {
	public static String WEBSERVICE_ENDPOINT;	//总线服务地址
	public static String WEBSERVICE_NAMESPACE;	//服务总线的命名空间
	public static final String DEPT_PREFIX = "BF";	//系统标识前缀
	public static String MQ_HOST;				//MQ服务器IP
	public static String MQ_PORT;				//MQ端口号
	public static String GIS_URL;				//GIS地图地址 
	
	public static final String KEY_SYS_TAG = "ZHSJFW";// 如OA
	public static String DEPT_ID;
	private static Axis2Utils AXIS2 = new Axis2Utils();
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public Axis2Utils(){		
		 //加载数据源
		 try {
			conn = C3P0DBConnectionManager.getConnection();	//获取数据库连接
			ps = conn.prepareStatement("select strkey,strvalue from PUBLICSYS.GLOBALPROPERTYINFO");	//读取公共信息
			rs = ps.executeQuery();
			while(rs.next()){
				String key =  rs.getString("strkey");
				String value = rs.getString("strvalue");
				if(key != null && key.equals("webservice.namespace")){
					WEBSERVICE_NAMESPACE = value;	//服务总线的命名空间
				}else if(key != null && key.equals("webservice.endpoint")){
					WEBSERVICE_ENDPOINT = value;	//总线服务地址
				}else if(key != null && key.equals("dept.tag")){
					DEPT_ID = value;	//总线服务地址
				}else if(key != null && key.equals("mq.qHostIP")){
					MQ_HOST = value;	//总线服务地址
				}else if(key != null && key.equals("mq.qPort")){
					MQ_PORT = value;	//总线服务地址
				}else if(key != null && key.equals("gismapip.url")){
					GIS_URL = value;	//总线服务地址
				}
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				//关闭连接
				DatabaseAccessFactory.getDataAccessInstance().close(rs);
				DatabaseAccessFactory.getDataAccessInstance().close(ps);
				DatabaseAccessFactory.getDataAccessInstance().close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Axis2Utils getInstance(){
		return AXIS2==null ? new Axis2Utils() : AXIS2;	
	}
}

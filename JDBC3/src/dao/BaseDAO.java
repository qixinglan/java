package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbutils.DBUtils;
//1.采用泛型解决试题类型不确定，把问题交给了继承者
//2.采用传参解决sql语句不同问题
//3.采用Object数组参数解决sql参数类型数量不确定
//4.采用stmt.setObject给sql参数赋值
public abstract class BaseDAO <T>{
	public List<T> find(String sql,Object[]params) throws Exception {
		// TODO Auto-generated method stub
		List<T> L=new ArrayList<T>();
		Connection con=dbutils.DBUtils.getConnection();
		PreparedStatement pstmt=con.prepareStatement(sql);
		if(params!=null){
			for(int i=1;i<=params.length;i++){
				pstmt.setObject(i, params[i-1]);//重要
			}
		}
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			//父类中一个代码段不确定如何实现，采用抽象方法强迫子类实现，再由父类自己调用.
			L.add(toEntity(rs));
		}
		DBUtils.closeConnection(con);
		return L;
		
	}
	public int update(String sql,Object[]params) throws Exception{
		Connection con=DBUtils.getConnection();
		PreparedStatement ps=con.prepareStatement(sql);
		for(int i=1;i<=params.length;i++){
			ps.setObject(i, params[i-1]);
		}
		int n=ps.executeUpdate();
		con.close();
		return n;
	}
	public abstract T toEntity(ResultSet rs) throws Exception;
		
	
}

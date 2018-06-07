package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbutils.DBUtils;
//1.���÷��ͽ���������Ͳ�ȷ���������⽻���˼̳���
//2.���ô��ν��sql��䲻ͬ����
//3.����Object����������sql��������������ȷ��
//4.����stmt.setObject��sql������ֵ
public abstract class BaseDAO <T>{
	public List<T> find(String sql,Object[]params) throws Exception {
		// TODO Auto-generated method stub
		List<T> L=new ArrayList<T>();
		Connection con=dbutils.DBUtils.getConnection();
		PreparedStatement pstmt=con.prepareStatement(sql);
		if(params!=null){
			for(int i=1;i<=params.length;i++){
				pstmt.setObject(i, params[i-1]);//��Ҫ
			}
		}
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			//������һ������β�ȷ�����ʵ�֣����ó��󷽷�ǿ������ʵ�֣����ɸ����Լ�����.
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

package com.tarena.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.tarena.util.Conn;

public abstract class BaseDao <T>{
	@Resource
	private Conn conn;
	public List<T> select(String sql,Object o[]) throws SQLException{
		Connection con=null;
		try {
			con=conn.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			if(o!=null){
				for(int i=0;i<o.length;i++){
					ps.setObject(i+1, o[i]);
				}
			}
			ResultSet rs=ps.executeQuery();
			List<T> list=toEntity(rs);
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.closeConnection(con);
		}
		return null;
	}
	public void update(String sql,Object o[]) throws SQLException{
		Connection con=null;
		try {
			con=conn.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps=con.prepareStatement(sql);
			if(o!=null){
				for(int i=0;i<o.length;i++){
					ps.setObject(i+1, o[i]);
				}
			}
			
			ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			con.rollback();
		}finally{
			conn.closeConnection(con);
		}
	}
	public Conn getConn() {
		return conn;
	}
	public void setConn(Conn conn) {
		this.conn = conn;
	}
	public abstract List<T> toEntity(ResultSet rs);
		
}

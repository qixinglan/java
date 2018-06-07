package com.tarena.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.tarena.entity.Worker;
import com.tarena.jdbc.DateSource;
@Repository
public class OracleWorkerDao implements Serializable,WorkerDao{
	private String fingByName="select * from worker where name=?";
	@Resource(name="dateSource")
	private DateSource datesource;
	public DateSource getDatesource() {
		return datesource;
	}
	public void setDatesource(DateSource datesource) {
		this.datesource = datesource;
	}
	public Worker findByName(String name){
		Worker worker=null;
		Connection con=null;
		try {
			con=datesource.getConnection();
			PreparedStatement ps=con.prepareStatement(fingByName);
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				worker=toEntity(rs);
			}
			return worker;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			datesource.close(con);
		}
	}
	public Integer add(Worker worker) {
		// TODO Auto-generated method stub
		return null;
	}
	public void delete(Worker worker) {
		// TODO Auto-generated method stub
		
	}
	public void update(Worker worker) {
		// TODO Auto-generated method stub
		
	}
	public List<Worker> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	public Worker findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	public Worker toEntity(ResultSet rs) throws SQLException{
		Worker worker=new Worker();
		worker.setId(rs.getString("id"));
		worker.setPhone(rs.getString("phone"));
		worker.setPwd(rs.getString("pwd"));
		worker.setName(rs.getString("name"));
		return worker;
	}
}

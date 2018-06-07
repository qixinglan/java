package com.tarena.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.xml.ws.RespectBinding;

import org.springframework.stereotype.Repository;

import com.tarena.entity.Worker;
@Repository
public class OracleWorkerDao implements WorkerDao,Serializable{
	String fingByName="select * from worker where name= ?";
	@Resource
	OracleDateSource oracleDateSource;
	public Worker findByName(String name) {
		// TODO Auto-generated method stub	
		Connection con=null;
		try {
			Worker worker=null;
			con = oracleDateSource.getConnection();
			PreparedStatement ps=con.prepareStatement(fingByName);
			ps.setString(1, name);
			ResultSet set=ps.executeQuery();
			while(set.next()){
				worker=toEntity(set);
			}
			return worker;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(con!=null){
				oracleDateSource.close(con);
			}
		}
		return null;
	}
	public OracleDateSource getOracleDateSource() {
		return oracleDateSource;
	}
	public void setOracleDateSource(OracleDateSource oracleDateSource) {
		this.oracleDateSource = oracleDateSource;
	}
	public Worker toEntity(ResultSet set) throws SQLException{
		Worker worker=new Worker();
		worker.setId(set.getString("id"));
		worker.setName(set.getString("name"));
		worker.setPwd(set.getString("pwd"));
		worker.setPhone(set.getString("phone"));
		return worker;
	}

}

package com.tarena.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tarena.entity.Worker;
@Repository
public class OracleWorkerDao extends BaseDao implements WorkerDao{
	public Worker findByName(String name) {
		// TODO Auto-generated method stub
		String sql="select * from worker where name=?";
		try {
			List<Worker> list=select(sql,new Object[]{name});
			if(list.size()>=1)
			return list.get(0);
			else
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<Worker> toEntity(ResultSet rs){
		// TODO Auto-generated method stub
		List<Worker> list=new ArrayList<Worker>();
		try {
			while(rs.next()){
				Worker worker=new Worker();
				worker.setId(rs.getInt("id"));
				worker.setName(rs.getString("name"));
				worker.setPwd(rs.getString("pwd"));
				worker.setPhone(rs.getString("phone"));
				list.add(worker);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}

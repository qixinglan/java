package com.tarena.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tarena.entity.Worker;
@Repository
public class OracleWorkerDao implements WorkerDao{
	@Resource
	private JdbcTemplate jdbcTemplate;
	public Worker findByName(String name) {
		// TODO Auto-generated method stub
		String sql="select * from worker by name= ?";
		Worker worker=jdbcTemplate.queryForObject
				(sql,new Object[]{name},Worker.class);
		
		return worker;
	}

}

package com.tarena.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.tarena.entity.Worker;
@Component
public class OracleWorkerDao implements WorkerDao{
@Resource
JdbcTemplate jdbcTemplate;
	public void add(Worker worker) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("insert into worker values(?,?,?)"
				,new Object[]{worker.getName(),worker.getPwd(),worker.getPhone()});
	}

	public Worker add(String name, String pwd, String phone) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("insert into worker values(?,?,?)"
				,new Object[]{name,pwd,phone});
		Worker worker=new Worker(name, pwd, phone);
		return worker;
	}

	public Worker findByName(String name) {
		// TODO Auto-generated method stub
		Worker worker=jdbcTemplate.queryForObject("select * from worker where name=?",
				new Object[]{name},new WorkerMapper());
		return worker;
	}
	class WorkerMapper implements RowMapper<Worker>{

		public Worker mapRow(ResultSet arg0, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			Worker worker=new Worker(
					arg0.getString("name"), arg0.getString("pwd"), arg0.getString("phone"));
			return worker;
		}
		
	}

	public void update(Worker worker) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("update worker set pwd=?,phone=? where name=?",
				new Object[]{worker.getPwd(),worker.getPhone(),worker.getName()});
	}

	public Worker delete(String name) {
		// TODO Auto-generated method stub
		Worker worker=findByName(name);
		jdbcTemplate.update("delete from worker where name="+name);
		return worker;
	}

	public List<Worker> findByPwd(String pwd) {
		// TODO Auto-generated method stub
		List<Worker> list=jdbcTemplate.query("select * from worker where pwd="+pwd,new WorkerMapper());
		return list;
	}

	public List<Worker> findAll() {
		// TODO Auto-generated method stub
		List<Worker> list=jdbcTemplate.query("select * from worker ",new WorkerMapper());
		return list;
	}

	public List<MainInfo> findNameAndPhone() {
		// TODO Auto-generated method stub
		List<MainInfo> list=jdbcTemplate.query("select name,pwd from worker",new MainInfoMappper());
		return list;
	}
	class MainInfoMappper implements RowMapper<MainInfo>{

		public MainInfo mapRow(ResultSet arg0, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			MainInfo mainInfo=new MainInfo();
			mainInfo.setName(arg0.getString("name"));
			mainInfo.setPwd(arg0.getString("pwd"));
			return mainInfo;
		}
		
	}

	public List<Map<String, Object>> findPhoneAndName() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list=jdbcTemplate.queryForList("select name,pwd from worker");
		return list;
	}

	public List<Worker> findByNameAndPhone(String name, String phone) {
		// TODO Auto-generated method stub
		List<Worker> list=jdbcTemplate.query("select * from worker where name='"+name+"' and phone="+phone, new WorkerMapper());
		return list;
	}

	public void add(String name, String pwd) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("insert into worker values('"+name+"','"+pwd+"','')");
	}

}

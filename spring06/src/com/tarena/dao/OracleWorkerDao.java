package com.tarena.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tarena.entity.Worker;
@Repository
public class OracleWorkerDao implements WorkerDao   {
	@Resource
	private JdbcTemplate jdbcTWorkerlate;

	public void  add(Worker worker) {
		// TODO Auto-generated method stub
		add(worker.getName(), worker.getPwd(), worker.getPhone());
	}

	public Worker add(String name, String pwd, String phone) {
		// TODO Auto-generated method stub
		String sql1="insert into worker(name,pwd,phone) values (?,?,?)";
		
		jdbcTWorkerlate.update(sql1,new Object[]{name,pwd,phone});
		String sql2="select name,pwd,phone from worker  where name=?";
		Worker worker=jdbcTWorkerlate.queryForObject
				(sql2,new Object[]{name},new WorkerMapper());
		return worker;
	}
class WorkerMapper implements RowMapper<Worker>{
	public Worker mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Worker worker=new Worker();
		worker.setName(rs.getString("name"));
		worker.setPwd(rs.getString("pwd"));
		worker.setPhone(rs.getString("phone"));
		return worker;
	}
	
}
	public Worker delete(String name) {
		// TODO Auto-generated method stub
		String sql1="select name,pwd,phone from worker where name=?";
		String sql2="delete from worker where name=? ";
		Worker worker=jdbcTWorkerlate.queryForObject
				(sql1, new Object[]{name},new WorkerMapper());
		//参数：sql语句，？Object数组，返回值类型
		jdbcTWorkerlate.update(sql2,new Object[]{name});
		return worker;
	}

	public void update(Worker worker) {
		// TODO Auto-generated method stub
		String sql="update worker set pwd=?,phone=? where name=?";
		jdbcTWorkerlate.update
		(sql,new Object[]{worker.getPwd(),worker.getPhone(),worker.getName()});
	}

	public Worker fingByName(String name) {
		// TODO Auto-generated method stub
		String sql="select name,pwd,phone from worker where name=?";
		
		Worker worker=jdbcTWorkerlate.queryForObject
				(sql, new Object[]{name},new WorkerMapper());
		return worker;
	}

	public List<Worker> findAll() {
		// TODO Auto-generated method stub
		String sql="select name,pwd,phone from worker";
		List<Worker> list=jdbcTWorkerlate.query(sql,new WorkerMapper());
		return list;
	}

	public List<Worker> findByPwd(String pwd) {
		// TODO Auto-generated method stub
		String sql="select name,phone,pwd from worker where pwd=?";
		List<Worker> list=jdbcTWorkerlate.query
				(sql, new Object[]{pwd},new WorkerMapper());
		return list;
	}
//Map方式
	public List<Map<String, Object>> findPhoneAndName() {
		// TODO Auto-generated method stub
		String sql="select name,phone from worker";
		List<Map<String, Object>> list=jdbcTWorkerlate.queryForList(sql);
		return list;
	}
	//值对象
class MainInfMapper implements RowMapper<MainInf>{

	public MainInf mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		MainInf mainInf=new MainInf();
		mainInf.setName(arg0.getString("name"));
		mainInf.setPhone(arg0.getString("phone"));
		return mainInf;
	}
	
}
	public List<MainInf> findNameAndPhone() {
		// TODO Auto-generated method stub
		String sql="select name,phone from worker";
		List<MainInf> list=jdbcTWorkerlate.query(sql,new MainInfMapper());
		return list;
	}
	

	

}

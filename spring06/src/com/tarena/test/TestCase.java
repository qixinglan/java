package com.tarena.test;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tarena.dao.MainInf;
import com.tarena.dao.OracleWorkerDao;
import com.tarena.entity.Worker;

public class TestCase {
	
	@Test
	public void test1() throws SQLException{
		String conf="spring-mvc.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		DataSource dateSource=ac.getBean("dataSource",DataSource.class);
		Connection con=dateSource.getConnection();
		System.out.println(con);
		con.close();
		//JdbcTemplate
		JdbcTemplate template=ac.getBean("jdbcTemplate",JdbcTemplate.class);
		String sql="select sysdate from dual";
		String a=template.queryForObject(sql, String.class);
		System.out.println(a);
		OracleWorkerDao oracleWorkerDao=ac.getBean("oracleWorkerDao",OracleWorkerDao.class);
		Worker worker=oracleWorkerDao.add("Andy", "110210326", "18231088827");
		System.out.println(worker);
		
	}
	@Test
	public void test2() throws SQLException{
		String conf="spring-mvc.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		OracleWorkerDao oracleWorkerDao=ac.getBean("oracleWorkerDao",OracleWorkerDao.class);
		Worker worker1=oracleWorkerDao.delete("Andy");
		System.out.println(worker1);
		try{
			Worker worker2=oracleWorkerDao.delete("Andy");//报异常
		}catch(EmptyResultDataAccessException e){
			System.out.println("没有结果报异常");
		}
	}
	@Test
	public void test3() throws SQLException{
		String conf="spring-mvc.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		OracleWorkerDao oracleWorkerDao=ac.getBean("oracleWorkerDao",OracleWorkerDao.class);
		List<Worker> list=oracleWorkerDao.findAll();
		System.out.println(list);
	}
	@Test
	public void test4() throws SQLException{
		String conf="spring-mvc.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		OracleWorkerDao oracleWorkerDao=ac.getBean("oracleWorkerDao",OracleWorkerDao.class);
		List<Worker> list=oracleWorkerDao.findByPwd("110210326");
		System.out.println(list);
	}
	@Test
	public void test5() throws SQLException{
		String conf="spring-mvc.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		OracleWorkerDao oracleWorkerDao=ac.getBean("oracleWorkerDao",OracleWorkerDao.class);
		Worker worker=oracleWorkerDao.fingByName("Tom");
		System.out.println(worker);
	}
	@Test
	public void test6() throws SQLException{
		String conf="spring-mvc.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		OracleWorkerDao oracleWorkerDao=ac.getBean("oracleWorkerDao",OracleWorkerDao.class);
		Worker worker=oracleWorkerDao.fingByName("Tom");
		worker.setPwd("110210326");
		oracleWorkerDao.update(worker);
		Worker worker1=oracleWorkerDao.fingByName("Tom");
		System.out.println(worker1);
	}
	@Test
	public void test7() throws SQLException{
		String conf="spring-mvc.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		OracleWorkerDao oracleWorkerDao=ac.getBean("oracleWorkerDao",OracleWorkerDao.class);
		List<Map<String,Object>> list=oracleWorkerDao.findPhoneAndName();
		for(Map<String,Object> m:list){
			System.out.println(m.get("name")+":"+m.get("phone"));
		}
		List<MainInf> list2=oracleWorkerDao.findNameAndPhone();
		for(MainInf e:list2){
			System.out.println(e);
		}
	}
}

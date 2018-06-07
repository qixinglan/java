package com.tarena.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tarena.dao.MainInfo;
import com.tarena.dao.OracleWorkerDao;
import com.tarena.entity.Worker;

public class TestCase {
	@Test
	public void f1() throws SQLException{
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		JdbcTemplate jdbcTemplate=ac.getBean("jdbcTemplate",JdbcTemplate.class);
		DataSource ds=jdbcTemplate.getDataSource();
		Connection con=ds.getConnection();
		System.out.println(con);
		con.close();
		OracleWorkerDao oracleWorkerDao=ac.getBean("oracleWorkerDao",OracleWorkerDao.class);
		oracleWorkerDao.add(new Worker("Tom1","123","12345"));
		oracleWorkerDao.add("Tom2", "123", "12345");
		List<Worker> list1=oracleWorkerDao.findAll();
		for(Worker e:list1){
			System.out.println(e);
		}
		//oracleWorkerDao.delete("Tom123");
		Worker worker=oracleWorkerDao.findByName("Tom");
		System.out.println(worker);
		List<Worker> list2=oracleWorkerDao.findByPwd("123");
		for(Worker e:list2){
			System.out.println(e);
		}
		List<MainInfo> list3=oracleWorkerDao.findNameAndPhone();
		for(MainInfo e:list3){
			System.out.println(e);
		}
		List<Map<String,Object>> list4=oracleWorkerDao.findPhoneAndName();
		for(Map<String,Object> e:list4){
			System.out.println(e.get("name"));
		}
		List<Worker> list5=oracleWorkerDao.findByNameAndPhone("Tom", "13703218749");
		System.out.println(list5);
		oracleWorkerDao.add("asd","aa");
	}
	
}

package com.tarena.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.dao.OracleWorkerDao;
import com.tarena.entity.Worker;
import com.tarena.entity.WorkerMapper;

public class TestCase {
	@Test
	public void f1() throws SQLException{
		String conf="ApplicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		SqlSession session=ac.getBean("sqlSession",SqlSession.class);
		Connection con=session.getConnection();
		System.out.println(con);
		con.close();
		session.commit();
		session.close();
	}
	@Test
	public void f2() throws SQLException{
		String conf="ApplicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		WorkerMapper workerMapper=ac.getBean("workerMapper",WorkerMapper.class);
		Worker worker=workerMapper.findByName("Tom");
		System.out.println(worker);
	}
	@Test
	public void f3() throws SQLException{
		String conf="ApplicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		OracleWorkerDao oracleWorkerDao=ac.getBean("oracleWorkerDao",OracleWorkerDao.class);
		Worker worker=oracleWorkerDao.findByName("Tom");
		System.out.println(worker);
	}
}

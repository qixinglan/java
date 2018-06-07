package com.tarena.test;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.dao.OracleWorkerDao;
import com.tarena.entity.Worker;
import com.tarena.entity.WorkerMapper;

public class TestCase {
	@Test
	//≤‚ ‘db°£properties∫Õ¡¨Ω”
	public void test1(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		Properties pro=ac.getBean("db",Properties.class);
		System.out.println(pro);
		SqlSession session=ac.getBean("sqlSession",SqlSession.class);
		Connection con=session.getConnection();
		System.out.println(con);
		session.close();
	}
	@Test
	public void test2(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		WorkerMapper wm=ac.getBean("workerMapper",WorkerMapper.class);
		System.out.println(wm.findByName("Tom"));
	}
	@Test
	public void test3(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		OracleWorkerDao owd=ac.getBean("oracleWorkerDao",OracleWorkerDao.class);
		Worker worker=new Worker("Tom3", "110210326", "18231088827");
		int id=owd.addWorker(worker);
		System.out.println(id);
		List<Worker> list=owd.findAll();
		for(Worker l:list){
			System.out.println(l);
		}
	}
	@Test
	public void test4(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		WorkerMapper wm=ac.getBean("workerMapper",WorkerMapper.class);
		OracleWorkerDao owd=ac.getBean("oracleWorkerDao",OracleWorkerDao.class);
		Worker worker=new Worker("Tom","110210326","18231088827");
		owd.updateWorker("Tom", worker);
	}
}

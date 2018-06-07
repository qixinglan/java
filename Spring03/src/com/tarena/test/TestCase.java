package com.tarena.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.dao.OracleWorkerDao;
import com.tarena.jdbc.DateSource;
import com.tarena.service.WorkerService;

public class TestCase {
	@Test 
	public void testService() throws SQLException{
		String conf="ApplicationContext.xml";
		ClassPathXmlApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		Properties pro=(Properties)ac.getBean("db");	
		System.out.println(pro);
		DateSource datesource=ac.getBean("dateSource",DateSource.class);
		Connection con=datesource.getConnection();
		System.out.println(datesource.getConnection());
		datesource.close(con);
		OracleWorkerDao oracleworkerdao=ac.getBean("oracleWorkerDao",OracleWorkerDao.class);
		System.out.println(oracleworkerdao.findByName("Tom"));
		WorkerService workerservice=ac.getBean("workerService",WorkerService.class);
		System.out.println(workerservice.login("Tom","12345"));
	}
}

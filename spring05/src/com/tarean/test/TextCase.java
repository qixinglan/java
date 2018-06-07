package com.tarean.test;

import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ViewResolver;

import com.tarena.dao.OracleDateSource;
import com.tarena.dao.OracleWorkerDao;
import com.tarena.service.LoginService;

public class TextCase {
	@Test
	public void Test1() throws SQLException{
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		Properties db=ac.getBean("db",Properties.class);
		System.out.println(db);
		OracleDateSource oracleDateSource=ac.getBean("oracleDateSource",OracleDateSource.class);
		System.out.println(oracleDateSource.getConnection());
		OracleWorkerDao owd=ac.getBean("oracleWorkerDao",OracleWorkerDao.class);
		System.out.println(owd.findByName("Tom"));
		LoginService ls=ac.getBean("loginService",LoginService.class);
		System.out.println(ls.login("Tom", "12345"));
	}
	@Test
	public void Test2(){
		String conf="spring-mvc.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		ViewResolver vr=ac.getBean("view",ViewResolver.class);
		System.out.println(vr);
	}
	@Test
	public void Test3(){
		String conf="spring-mvc.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		OracleDateSource ods=ac.getBean("oracleDateSource",OracleDateSource.class);
		System.out.println(ods.getDriver());
	}
}

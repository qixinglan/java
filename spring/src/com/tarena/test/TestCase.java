package com.tarena.test;

import java.util.Properties;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
public class TestCase {
	@Test
	public void test(){
		String conf="spring-mvc.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		Properties pro=ac.getBean("db",Properties.class);
		System.out.println(pro);
		JdbcTemplate jtl=ac.getBean("jdbcTemplate",JdbcTemplate.class);
		String sql="select sysdate from dual";
		String s=jtl.queryForObject(sql, String.class);
		System.out.println(s);
	}
	
}

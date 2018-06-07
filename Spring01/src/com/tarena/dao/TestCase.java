package com.tarena.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCase {
	@Test
	public void TestDateSource(){
		String conf="applicationContext2.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		JdbcDateSource jds=ac.getBean("dateSource",JdbcDateSource.class);
		System.out.println(jds);
		try {
			//����bean����ע��
			System.out.println(jds.getDriver());
			System.out.println(jds.getPwd());
			System.out.println(jds.getUrl());
			System.out.println(jds.getUser());
			Connection con=jds.getComncetion();//��ȡ����
			System.out.println(con.getMetaData().getDatabaseProductName());//��ȡ���ݿ�����
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testDemoBean(){
		String conf="applicationContext2.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		DemoBean demobean=ac.getBean("demoBean",DemoBean.class);
		System.out.println(demobean);
	}
	@Test
	public void testOracleUserDao() throws SQLException{
		String conf="applicationContext2.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		OracleUserDao oracleuserdao=ac.getBean("oracleUserDao",OracleUserDao.class);
		System.out.println(oracleuserdao);
		Connection con=oracleuserdao.getDateSource().getComncetion();
		System.out.println(con);
		con.close();
	}
	@Test
	public void testBaseDateBean(){
		String conf="applicationContext2.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		BaseDateBean bdb=ac.getBean("baseDateBean",BaseDateBean.class);
		System.out.println(bdb);
	}
}

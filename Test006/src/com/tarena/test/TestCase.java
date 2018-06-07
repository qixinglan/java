package com.tarena.test;

import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.util.Conn;

public class TestCase {
	public static void main(String[] args) {
		String conf="spring-mvc.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		Properties p=ac.getBean("db",Properties.class);
		System.out.println(p.getProperty("driver"));
		Conn c=ac.getBean("conn",Conn.class);
		System.out.println(c.getDriver());
	}
}

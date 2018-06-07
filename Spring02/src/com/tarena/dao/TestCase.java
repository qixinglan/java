package com.tarena.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCase {
	@Test
	public void testList(){
		String conf="ApplicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		CollectionBean collection=ac.getBean("colBean",CollectionBean.class);
		System.out.println(collection);
	}
	@Test
	public void testList2(){
		String conf="ApplicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		CollectionBean collection=ac.getBean("collectionBean2",CollectionBean.class);
		System.out.println(collection);
		List age=ac.getBean("age",List.class);
		System.out.println(age);
	}
	@Test
	public void testEl(){
		String conf="ApplicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		DemoBean1 mb=ac.getBean("demoBean1",DemoBean1.class);
		System.out.println(mb.getError());
	}
	@Test
	public void textJdbc() throws SQLException{
		String conf="ApplicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		Jdbc jdbc=ac.getBean("jdbc",Jdbc.class);
		Connection con=jdbc.getConnection();
		System.out.println(con);
		con.close();
	}
	//≤‚ ‘◊¢Ω‚
	@Test
	public void textDemoBean2(){
		String conf="ApplicationContext1.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		ac.getBean("bean2");
	}
	//≤‚ ‘“¿¿µ◊¢»Î
	@Test
	public void testFood(){
		String conf="ApplicationContext1.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		Food food=ac.getBean("food",Food.class);
		System.out.println(food);
	}
	@Test 
	public void testPeople(){
		String conf="ApplicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		People people=ac.getBean("people",People.class);
		System.out.println(people);
	}
}

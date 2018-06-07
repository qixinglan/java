package com.tarena.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.tarena.controller.EmpController;

public class TestEmpController {
	
	/**
	 * 测试查询员工
	 */
	@Test
	public void test1() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		EmpController ctl = ctx.getBean(EmpController.class);
		ctl.find();
	}

	/**
	 * 测试批量添加员工
	 */
	@Test
	public void test2() throws ClassNotFoundException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		EmpController ctl = ctx.getBean(EmpController.class);
		ctl.addBatch();
	}
	
}

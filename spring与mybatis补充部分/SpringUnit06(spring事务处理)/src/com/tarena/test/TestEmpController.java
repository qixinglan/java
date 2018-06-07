package com.tarena.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.tarena.controller.EmpController;

public class TestEmpController {
	
	/**
	 * ���Բ�ѯԱ��
	 */
	@Test
	public void test1() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		EmpController ctl = ctx.getBean(EmpController.class);
		ctl.find();
	}

	/**
	 * �����������Ա��
	 */
	@Test
	public void test2() throws ClassNotFoundException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		EmpController ctl = ctx.getBean(EmpController.class);
		ctl.addBatch();
	}
	
}

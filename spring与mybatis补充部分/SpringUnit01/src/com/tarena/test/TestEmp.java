package com.tarena.test;

import java.sql.Date;
import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.tarena.dao.EmpDao;
import com.tarena.entity.Emp;

/**
 *	EmpDao������
 */
public class TestEmp {
	
	private String conf = "applicationContext.xml";
	
	/**
	 * ���Բ�ѯȫ��Ա��
	 */
	@Test
	public void test1() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		EmpDao dao = ctx.getBean(EmpDao.class);
		List<Emp> list = dao.findAll();
		for(Emp e : list) {
			System.out.println(
				e.getEmpno() + " " +
				e.getEname() + " " +
				e.getJob()
			);
		}
	}
	
	/**
	 * ��������һ��Ա������
	 */
	@Test
	public void test2() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		EmpDao dao = ctx.getBean(EmpDao.class);
		Emp e = new Emp();
		e.setEname("����");
		e.setJob("SALESMAN");
		e.setMgr(3);
		e.setHiredate(
			new Date(System.currentTimeMillis()));
		e.setSal(4000.0);
		e.setComm(650.0);
		e.setDeptno(10);
		dao.save(e);
	}
	
	/**
	 * ���Ը���ID��ѯһ��Ա������
	 */
	@Test
	public void test3() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		EmpDao dao = ctx.getBean(EmpDao.class);
		Emp e = dao.findById(361);
		System.out.println(
			e.getEmpno() + " " +
			e.getEname() + " " +
			e.getJob()
		);
	}
	
	/**
	 * �����޸�һ��Ա������
	 */
	@Test
	public void test4() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		EmpDao dao = ctx.getBean(EmpDao.class);
		Emp e = dao.findById(361);
		e.setEname("������");
		e.setJob("MANAGER");
		e.setMgr(9);
		e.setSal(9000.0);
		e.setComm(null);
		e.setDeptno(30);
		dao.update(e);
	}
	
	/**
	 * ����ɾ��һ��Ա������
	 */
	@Test
	public void test5() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		EmpDao dao = ctx.getBean(EmpDao.class);
		dao.delete(361);
	}

}

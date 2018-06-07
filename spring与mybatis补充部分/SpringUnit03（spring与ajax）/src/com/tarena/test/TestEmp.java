package com.tarena.test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.tarena.dao.EmpDao;
import com.tarena.entity.Condition;
import com.tarena.entity.Emp;

/**
 *	EmpDao测试类
 */
public class TestEmp {
	
	private String conf = "applicationContext.xml";
	
	/**
	 * 根据部门查询员工
	 */
	@Test
	public void test1() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		EmpDao dao = ctx.getBean(EmpDao.class);
		Condition cond = new Condition();
		cond.setDeptno(10);
		List<Emp> list = dao.findByDept(cond);
		for(Emp e : list) {
			System.out.println(
				e.getEmpno() + " " +
				e.getEname() + " " +
				e.getJob()
			);
		}
	}
	
	/**
	 * 查询大于当前收入的员工
	 */
	@Test
	public void test2() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		EmpDao dao = ctx.getBean(EmpDao.class);
		Condition cond = new Condition();
		cond.setSalary(100.0);
		List<Emp> list = dao.findBySalary(cond);
		for(Emp e : list) {
			System.out.println(
				e.getEmpno() + " " +
				e.getEname() + " " +
				e.getJob()
			);
		}
	}
	
	/**
	 * 查询当前部门下,大于当前收入的员工
	 */
	@Test
	public void test3() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		EmpDao dao = ctx.getBean(EmpDao.class);
		Condition cond = new Condition();
		cond.setDeptno(20);
		cond.setSalary(2000.0);
		List<Emp> list = dao.findByDeptAndSalary(cond);
		for(Emp e : list) {
			System.out.println(
				e.getEmpno() + " " +
				e.getEname() + " " +
				e.getJob()
			);
		}
	}
	
	/**
	 * 更新员工
	 */
	@Test
	public void test4() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		EmpDao dao = ctx.getBean(EmpDao.class);
		Emp e = new Emp();
		e.setEmpno(14);
		e.setEname("Leo");
		dao.update(e);
	}
	
	/**
	 * 查询当前部门下,大于当前收入的员工
	 */
	@Test
	public void test5() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		EmpDao dao = ctx.getBean(EmpDao.class);
		Condition cond = new Condition();
		cond.setDeptno(20);
		cond.setSalary(2000.0);
		List<Emp> list = dao.findByDeptAndSalary2(cond);
		for(Emp e : list) {
			System.out.println(
				e.getEmpno() + " " +
				e.getEname() + " " +
				e.getJob()
			);
		}
	}
	
	/**
	 * 更新员工
	 */
	@Test
	public void test6() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		EmpDao dao = ctx.getBean(EmpDao.class);
		Emp e = new Emp();
		e.setEmpno(14);
		e.setEname("Lee");
		dao.update2(e);
	}
	
	/**
	 * 根据员工ID查询员工
	 */
	@Test
	public void test7() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		EmpDao dao = ctx.getBean(EmpDao.class);
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(3);
		ids.add(7);
		ids.add(8);
		Condition cond = new Condition();
		cond.setEmpnos(ids);
		List<Emp> list = dao.findByIds(cond);
		for(Emp e : list) {
			System.out.println(
				e.getEmpno() + " " +
				e.getEname() + " " +
				e.getJob()
			);
		}
	}

}

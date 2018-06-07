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
public class TestEmpDao {
	
	/**
	 * 测试查询全部员工
	 */
	@Test
	public void testFindAll() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext(
					"applicationContext.xml");
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
	 * 根据部门查询员工
	 */
	@Test
	public void testFindByDept() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext("applicationContext.xml");
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
	public void testFindBySalary() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext("applicationContext.xml");
		EmpDao dao = ctx.getBean(EmpDao.class);
		Condition cond = new Condition();
		cond.setSalary(4000.0);
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
	public void testFindByDeptAndSalary() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext("applicationContext.xml");
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
	public void testUpdate() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext("applicationContext.xml");
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
	public void testFindByDeptAndSalary2() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext("applicationContext.xml");
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
	public void testUpdate2() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext("applicationContext.xml");
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
	public void testFindByIds() {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext("applicationContext.xml");
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

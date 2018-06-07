package com.tarena.test;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.dao.DeptDao;
import com.tarena.dao.EmpDao;
import com.tarena.dao.VehicleDao;
import com.tarena.entity.Dept;
import com.tarena.entity.Emp;
import com.tarena.entity.Vehicle;

/**
 *	测试MyBatis关联映射
 */
public class TestMapping {
	
	/**
	 * 主键映射：新增一条员工数据
	 */
	@Test
	public void test1() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		EmpDao dao = ctx.getBean(EmpDao.class);
		Emp e = new Emp();
		e.setEname("张三");
		e.setJob("SALESMAN");
		e.setMgr(3);
		e.setHiredate(
			new Date(System.currentTimeMillis()));
		e.setSal(4000.0);
		e.setComm(650.0);
		e.setDeptno(10);
		dao.save(e);
		System.out.println(e.getEmpno());
	}
	
	/**
	 * 多对一嵌套查询映射：
	 * 根据ID查询一条员工数据，并查询他所在的部门。
	 */
	@Test
	public void test2() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		EmpDao dao = ctx.getBean(EmpDao.class);
		Emp e = dao.findById(1);
		System.out.println(
			e.getEmpno() + " " +
			e.getEname() + " " +
			e.getJob() + " " +
			e.getDept().getDeptno() + " " +
			e.getDept().getDname()
		);
	}
	
	/**
	 * 多对一嵌套结果映射：
	 * 根据ID查询一条员工数据，并查询他所在的部门。
	 */
	@Test
	public void test3() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		EmpDao dao = ctx.getBean(EmpDao.class);
		Emp e = dao.findById2(1);
		System.out.println(
			e.getEmpno() + " " +
			e.getEname() + " " +
			e.getJob() + " " +
			e.getDept().getDeptno() + " " +
			e.getDept().getDname()
		);
	}
	
	/**
	 * 一对多嵌套查询映射：
	 * 查询部门及部门下所有的员工。
	 */
	@Test
	public void test4() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		DeptDao dao = ctx.getBean(DeptDao.class);
		Dept d = dao.findById(10);
		System.out.println(
			d.getDeptno() + " " +
			d.getDname() + " " +
			d.getLoc() 
		);
		List<Emp> emps = d.getEmps();
		for(Emp e : emps) {
			System.out.println(
				e.getEmpno() + " " + 
				e.getEname() + " " +
				e.getJob()
			);
		}
	}
	
	/**
	 * 一对多嵌套结果映射：
	 * 查询部门及部门下所有的员工。
	 */
	@Test
	public void test5() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		DeptDao dao = ctx.getBean(DeptDao.class);
		Dept d = dao.findById2(10);
		System.out.println(
			d.getDeptno() + " " +
			d.getDname() + " " +
			d.getLoc() 
		);
		List<Emp> emps = d.getEmps();
		for(Emp e : emps) {
			System.out.println(
				e.getEmpno() + " " + 
				e.getEname() + " " +
				e.getJob()
			);
		}
	}	
	
	/**
	 * 鉴别器：
	 * 查询汽车表，根据类型封装成不同的对象。
	 */
	@Test
	public void test6() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		VehicleDao dao = ctx.getBean(VehicleDao.class);
		List<Vehicle> list = dao.findAll();
		for (Vehicle v : list) {
			System.out.println(v);
		}
	}
	
}
 
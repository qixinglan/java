package com.tarena.test;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.dao.DeptMapper;
import com.tarena.dao.EmpMapper;
import com.tarena.dao.VehicleDao;
import com.tarena.dao.WorkerMapper;
import com.tarena.entity.Dept;
import com.tarena.entity.Emp;
import com.tarena.entity.Vehicle;
import com.tarena.entity.Worker;

public class TestCase {
	@Test
	public void f1(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		WorkerMapper workerMapper=ac.getBean("workerMapper",WorkerMapper.class);
		Worker worker=new Worker();
		worker.setId(2);
		worker.setName("Andy");
		worker.setPhone("13703218749");
		worker.setPwd("110210326");
		workerMapper.insert(worker);
	}
	@Test
	public void f2(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		EmpMapper empMapper=ac.getBean(EmpMapper.class);
		Emp e = new Emp();
		e.setEname("ÕÅÈý");
		e.setJob("SALESMAN");
		e.setMgr(3);
		e.setHiredate(
			new Date(System.currentTimeMillis()));
		e.setSal(4000.0);
		e.setComm(650.0);
		e.setDeptno(10);
		empMapper.save(e);
		System.out.println(e.getEmpno());
	}
	@Test
	public void f3(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		EmpMapper empMapper=ac.getBean(EmpMapper.class);
		Emp emp=empMapper.findById(1);
		System.out.println(emp);
		Emp emp1=empMapper.findById2(1);
		System.out.println(emp1);
	}
	@Test
	public void f4(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		DeptMapper deptMapper=ac.getBean(DeptMapper.class);
		Dept dept=deptMapper.findById(20);
		System.out.println(dept);
		Dept dept1=deptMapper.findById2(20);
		System.out.println(dept1);
	}
	@Test
	public void test1() {
		String conf="applicationContext.xml";
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		VehicleDao dao = ctx.getBean(VehicleDao.class);
		List<Vehicle> list = dao.findAll();
		System.out.println(list);
		for (Vehicle v : list) {
			System.out.println(v);
		}
	}
}

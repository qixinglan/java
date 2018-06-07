package com.tarena.Test;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.tarena.entity.Emp;
import com.tarena.util.HibernateUtil;

/*
 * 测试对象持久性，通过查看是否打印sql可以看出很多原理。
 * 打印sql是进行了数据库操作不打印就是没有数据库操作（除了id生成）
 */
public class TestPersistent {
	/*
	 * 测试持久态对象存在于一级缓存中
	 * 通过新增save（Emp）一个对象，把一个对象持久态。
	 * 然后通过get()查询方法，看有没有打印SQL。
	 * 若没有打印证明持久态对象存在于一级缓存中。
	 */
	@Test
	public void test1(){
		Session session=HibernateUtil.getSession();
		Emp emp=new Emp();
		emp.setAge(25);
		emp.setBirthday(Date.valueOf("1990-05-06"));
		emp.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
		emp.setMarry(false);
		emp.setName("谷亮");
		emp.setSalary(8000.0);
		Transaction ts=session.beginTransaction();
		try {
			//save()方法只是把临时态对象变为持久态，并没有提交SQL,但是生成了Id
			session.save(emp);
			//此时对象已经是持久态了。查询这个对象会从一级缓存中读取。
			System.out.println("1-----------------------");
			Emp emp1 = (Emp) session.get(Emp.class, emp.getId());
			System.out.println(emp1.getName());
			//commint方法会提交SQL，是调用session.flush()实现的。并且提交事务。
			ts.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			ts.rollback();
		}finally{
			session.close();
		}
	}
	
	
	
	/*
	 * 测试持久态对象可以自动更新到数据库
	 * 新增一条员工数据，新增员工后对象是持久态
	 * 直接修改这个对象的数据，再提交事务。看最终
	 * 保存的结果如何。
	 */
	@Test
	public void test2(){
		Session session=HibernateUtil.getSession();
		Emp emp=new Emp();
		emp.setAge(25);
		emp.setBirthday(Date.valueOf("1990-05-06"));
		emp.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
		emp.setMarry(false);
		emp.setName("谷帅");
		emp.setSalary(8000.0);
		Transaction ts=session.beginTransaction();
		try {
			//save()方法只是把临时态对象变为持久态，并没有提交SQL,但是生成了Id
			session.save(emp);
			System.out.println("1-----------------------");
			//此时对象已经是持久态了。查询这个对象会从一级缓存中读取。
			emp.setAge(24);//修改这个对象数据，此时一级缓存中的对象数据也已修改。
			System.out.println("2-----------------------");
			//commint方法会提交SQL，是调用session.flush()实现的。并且提交事务。
			ts.commit();
			//commint先执行了一次insert原来的对象。再执行了一次Update更新你做的数据修改。
			System.out.println("3-----------------------");
			//从一级缓存中找到该数据，并没有真正从数据库查询。
			Emp emp1 = (Emp) session.get(Emp.class, emp.getId());
			System.out.println(emp1.getAge());
		} catch (HibernateException e) {
			e.printStackTrace();
			ts.rollback();
		}finally{
			session.close();
		}
	}
	/**
	 * 自动更新时机是session.flush()
	 * 查询一条员工数据，则查询到的对象是持久态的。
	 * 修改这个对象的值，然后调用session.flush（）
	 * 看控制台打印的SQL
	 */
	@Test
	public void test3() {
		
		Session session = HibernateUtil.getSession();
		//Transaction ts=session.beginTransaction();
		Emp e = (Emp) session.get(Emp.class, 1);
		e.setName("夏侯惇");
		session.flush();//触发执行更新update
		//ts.commit();
		//可以看出打印了update，但是并没有提交事务，所以查询数据库看不到更新。
		session.close();
	}
}

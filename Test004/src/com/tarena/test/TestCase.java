package com.tarena.test;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.tarena.entity.Emp;
import com.tarena.util.HibernateUtil;

public class TestCase {
	@Test
	public void test1(){
		Session session=HibernateUtil.getSession();
		Emp emp1=(Emp)session.get(Emp.class,1);
		System.out.println(emp1);
		session.close();
	}
	@Test
	public void test2(){
		Session session=HibernateUtil.getSession();
		Emp emp=new Emp();
		emp.setName("ËïÎò¿Õ");
		emp.setAge(30);
		emp.setSalary(8000.0);
		emp.setBirthday(Date.valueOf("1984-05-06"));
		emp.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
		emp.setMarry(false);
		
		Transaction ts=session.beginTransaction();
		try{
			session.save(emp);
			ts.commit();
		}catch(Exception e){
			e.printStackTrace();
			ts.rollback();
		}finally{
			session.close();
		}
		
	}
	@Test
	public void test3(){
		Session session=HibernateUtil.getSession();
		Emp emp=(Emp)session.get(Emp.class, 15);
		emp.setName("×¥°Ë½ä");
		Transaction ts=session.beginTransaction();
		try{
			session.update(emp);
			ts.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			ts.rollback();
		}finally{
			session.close();
		}
	}
	@Test
	public void test4(){
		Session session=HibernateUtil.getSession();
		Emp emp=new Emp();
		emp.setId(15);
		Transaction ts=session.beginTransaction();
		try{
			session.delete(emp);
			ts.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			ts.rollback();
		}finally{
			session.close();
		}
	}
	@Test
	public void test5(){
		Session session=HibernateUtil.getSession();
		Transaction ts=session.beginTransaction();
		String hql="from Emp";
		Query query=session.createQuery(hql);
		List<Emp> list=query.list();
		for(Emp e:list){
			System.out.println(e);
		}
		
	}
	
}

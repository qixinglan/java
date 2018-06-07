package com.tarena.Test;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.tarena.entity.Emp;
import com.tarena.util.HibernateUtil;


public class TestLazy {
	/*
	 * 演示session。load（）方法是延迟加载
	 */
	@Test
	public void test1(){
		Session session=HibernateUtil.getSession();
		Emp e=(Emp)session.load(Emp.class, 1);
		//Emp e=(Emp)session.get(Emp.class, 1);//对比着看 
		System.out.println("1------------------");
		System.out.println(e.getName());
		session.close();
	}
	/*
	 * 演示query。iterate（）是延迟加载
	 * 这个方法用的不多，因为效率低
	 * 可以从打印的SQl看出，他是一个一个的查询，所以效率低
	 */
	@Test
	public void test2(){
		String hql="from Emp";
		Session session=HibernateUtil.getSession();
		Query query=session.createQuery(hql);
		Iterator<Emp> it=query.iterate();
		System.out.println("!----------------");
		while(it.hasNext()){
			System.out.println(it.next().getName());
		}
		session.close();
	}

}

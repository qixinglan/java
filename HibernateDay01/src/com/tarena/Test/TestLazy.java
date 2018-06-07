package com.tarena.Test;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.tarena.entity.Emp;
import com.tarena.util.HibernateUtil;


public class TestLazy {
	/*
	 * ��ʾsession��load�����������ӳټ���
	 */
	@Test
	public void test1(){
		Session session=HibernateUtil.getSession();
		Emp e=(Emp)session.load(Emp.class, 1);
		//Emp e=(Emp)session.get(Emp.class, 1);//�Ա��ſ� 
		System.out.println("1------------------");
		System.out.println(e.getName());
		session.close();
	}
	/*
	 * ��ʾquery��iterate�������ӳټ���
	 * ��������õĲ��࣬��ΪЧ�ʵ�
	 * ���ԴӴ�ӡ��SQl����������һ��һ���Ĳ�ѯ������Ч�ʵ�
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

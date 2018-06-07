package com.tarena.Test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.tarena.entity.Emp;
import com.tarena.util.HibernateUtil;

/*
 * ���Զ�������
 */
public class TestSecondCache {
	/*
	 * ���Զ����������
	 * ��Ϊ ����������SessionFactory����ģ����ǹ���ģ��κ�һ��session�����Է��ʡ�
	 * ��������ʹ������session����ѯ��ͬ�����ݣ�
	 * ���ڶ���session��ѯʱ����û�д�ӡSQL�����ɿ�����
	 */
	@Test
	public void test1(){
		Session session1=HibernateUtil.getSession();
		Emp emp1=(Emp)session1.get(Emp.class, 1);
		System.out.println(emp1.getName());
		System.out.println("-------------------");
		Session session2=HibernateUtil.getSession();
		Emp emp2=(Emp)session2.get(Emp.class, 1);
		System.out.println(emp2.getName());
	}
	/*
	 * ���Զ����������
	 */
	@Test
	public void test2(){
		Session session1=HibernateUtil.getSession();
		Emp emp1=(Emp)session1.get(Emp.class, 1);
		System.out.println(emp1.getName());
		System.out.println("-------------------");
		//�������������и������ɾ��
		//HibernateUtil.getAf().evict(Emp.class);
		//����������idΪ1�������ɾ��
		HibernateUtil.getAf().evict(Emp.class,1);
		Session session2=HibernateUtil.getSession();
		Emp emp2=(Emp)session2.get(Emp.class, 1);
		System.out.println(emp2.getName());
		session1.close();
		session2.close();
	}
	/*
	 * ���Բ�ѯ����
	 * ��ѯ���������ڶ������棬�������úö������棬�ſ����ò�ѯ����
	 * ��ѯ����ͬ��������һ����SessionFactory����ģ�Ҳ����˵�ǹ����
	 * ���Բ�ͬsession�����Է��ʲ�ѯ����
	 */
	@Test
	public void test3(){
		String hql1="select count(*) from Emp";
		Session session1=HibernateUtil.getSession();
		Query query1=session1.createQuery(hql1);
		//����query1��ѯ����
		query1.setCacheable(true);
		System.out.println(query1.uniqueResult());
		System.out.println("-------------------");
		
		
		Session session2=HibernateUtil.getSession();
		Query query2=session1.createQuery(hql1);
		//����query2��ѯ����
		query2.setCacheable(true);
		System.out.println(query2.uniqueResult());
		
		session1.close();
		session2.close();
	}
	/*
	 * ���Բ�ѯ�������
	 */
	@Test
	public void test4(){
		String hql1="select count(*) from Emp";
		Session session1=HibernateUtil.getSession();
		Query query1=session1.createQuery(hql1);
		//����query1��ѯ����
		query1.setCacheable(true);
		System.out.println(query1.uniqueResult());
		System.out.println("-------------------");
		//ɾ����ѯ��������������
		HibernateUtil.getAf().evictQueries();
		
		Session session2=HibernateUtil.getSession();
		Query query2=session1.createQuery(hql1);
		//����query1��ѯ����
		query2.setCacheable(true);
		System.out.println(query2.uniqueResult());
		
		session1.close();
		session2.close();
	}
}

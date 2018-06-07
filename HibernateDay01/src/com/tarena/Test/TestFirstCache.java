package com.tarena.Test;

import org.hibernate.Session;
import org.junit.Test;

import com.tarena.entity.Emp;
import com.tarena.util.HibernateUtil;

public class TestFirstCache {
	@Test
	/*
	 * ���Դ���һ������
	 * ���ʹ��ͬһ��Session��ѯͬһ���������Σ�
	 * ����ڶ���û�д�ӡSQL����˵������һ������
	 */
	public void tes1(){
		Session session=HibernateUtil.getSession();
		Emp emp1=(Emp)session.get(Emp.class,1);
		System.out.println(emp1.getName());
		System.out.println("------------------------");
		Emp emp2=(Emp)session.get(Emp.class,1);
		System.out.println(emp2.getName());
	}
	@Test
	/*
	 * ����һ��������ÿ��Session����ģ��������档
	 * ʹ������session�ֱ��ѯid=1��Ա��
	 * ����ڶ���session��ӡ��SQL����˵���ڶ���sessiom
	 * û�ӻ����ж�ȡ���ݣ���֤����һ��������session����ġ�
	 */
	public void test2(){
		Session session1=HibernateUtil.getSession();
		Emp emp1=(Emp)session1.get(Emp.class, 1);
		System.out.println(emp1.getName());
		System.out.println("---------------------");
		Session session2=HibernateUtil.getSession();
		Emp emp2=(Emp)session2.get(Emp.class, 1);
		System.out.println(emp2.getName());
	}
	@Test
	/*
	 * ����һ������Ĺ���취
	 * session.evict(obj);
	 * session.clear();
	 * session.close();
	 */
	public void test3(){
		Session session=HibernateUtil.getSession();
		Emp emp1=(Emp)session.get(Emp.class,1);
		System.out.println(emp1.getName());
		System.out.println("------------------------");
		//session.evict(emp1);
		//session.clear();
		session.close();//�ͷŸ�Session��һ�����棬��������Ҳ��û�ˡ�����session���������������ݿ�
		Emp emp2=(Emp)session.get(Emp.class,1);
		System.out.println(emp2.getName());
	}
}

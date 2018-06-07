package com.tarena.Test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.tarena.entity.Emp;
import com.tarena.util.HibernateUtil;

/*
 * 测试二级缓存
 */
public class TestSecondCache {
	/*
	 * 测试二级缓存存在
	 * 因为 二级缓存是SessionFactory级别的，即是共享的，任何一个session都可以访问。
	 * 所以我们使用两个session，查询相同的数据，
	 * 看第二个session查询时，有没有打印SQL，即可看出。
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
	 * 测试二级缓存管理
	 */
	@Test
	public void test2(){
		Session session1=HibernateUtil.getSession();
		Emp emp1=(Emp)session1.get(Emp.class, 1);
		System.out.println(emp1.getName());
		System.out.println("-------------------");
		//将二级缓存所有该类对象删除
		//HibernateUtil.getAf().evict(Emp.class);
		//将二级缓存id为1该类对象删除
		HibernateUtil.getAf().evict(Emp.class,1);
		Session session2=HibernateUtil.getSession();
		Emp emp2=(Emp)session2.get(Emp.class, 1);
		System.out.println(emp2.getName());
		session1.close();
		session2.close();
	}
	/*
	 * 测试查询缓存
	 * 查询缓存依赖于二级缓存，必须配置好二级缓存，才可以用查询缓存
	 * 查询缓存同二级缓存一样是SessionFactory级别的，也就是说是共享的
	 * 所以不同session都可以访问查询缓存
	 */
	@Test
	public void test3(){
		String hql1="select count(*) from Emp";
		Session session1=HibernateUtil.getSession();
		Query query1=session1.createQuery(hql1);
		//启用query1查询缓存
		query1.setCacheable(true);
		System.out.println(query1.uniqueResult());
		System.out.println("-------------------");
		
		
		Session session2=HibernateUtil.getSession();
		Query query2=session1.createQuery(hql1);
		//启用query2查询缓存
		query2.setCacheable(true);
		System.out.println(query2.uniqueResult());
		
		session1.close();
		session2.close();
	}
	/*
	 * 测试查询缓存管理
	 */
	@Test
	public void test4(){
		String hql1="select count(*) from Emp";
		Session session1=HibernateUtil.getSession();
		Query query1=session1.createQuery(hql1);
		//启用query1查询缓存
		query1.setCacheable(true);
		System.out.println(query1.uniqueResult());
		System.out.println("-------------------");
		//删除查询缓存中所有数据
		HibernateUtil.getAf().evictQueries();
		
		Session session2=HibernateUtil.getSession();
		Query query2=session1.createQuery(hql1);
		//启用query1查询缓存
		query2.setCacheable(true);
		System.out.println(query2.uniqueResult());
		
		session1.close();
		session2.close();
	}
}

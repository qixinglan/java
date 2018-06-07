package com.tarena.Test;

import org.hibernate.Session;
import org.junit.Test;

import com.tarena.entity.Emp;
import com.tarena.util.HibernateUtil;

public class TestFirstCache {
	@Test
	/*
	 * 测试存在一级缓存
	 * 如果使用同一个Session查询同一条数据两次，
	 * 如果第二次没有打印SQL，则说明存在一级缓存
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
	 * 测试一级缓存是每个Session独享的，互不干涉。
	 * 使用两个session分别查询id=1的员工
	 * 如果第二个session打印了SQL，则说明第二个sessiom
	 * 没从缓存中读取数据，既证明了一级缓存是session独享的。
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
	 * 测试一级缓存的管理办法
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
		session.close();//释放该Session的一级缓存，所有数据也就没了。但是session不可以再连接数据库
		Emp emp2=(Emp)session.get(Emp.class,1);
		System.out.println(emp2.getName());
	}
}

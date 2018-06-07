package com.tarena.Test;

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

public class TestCase{
	/*
	 * session.close()表示将当前会话连接关闭,断开与数据库的连接,
	 * 如果需要再次连接数据库需要再次获取session,进行操作.
	 * 如果session不关闭话,将会导致数据库连接数过多,导致系统运行缓慢
	 */
	@Test
	//根据Id查询Emp
	public void test1(){
		//创建Session
		Session session = HibernateUtil.getSession();
		//查询Emp根据Id
		Emp emp=(Emp)session.get(Emp.class, 1);
		System.out.println("name:"+emp.getName()+"\n"+"Id:"+emp.getId());
		//关闭Session
		session.close();
	}
	@Test
	//新增员工
	public void test2(){
		Emp emp=new Emp();
		emp.setName("孙悟空");
		emp.setAge(30);
		emp.setSalary(8000.0);
		emp.setBirthday(Date.valueOf("1984-05-06"));
		emp.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
		emp.setMarry(false);
		//创建Session
		Session session = HibernateUtil.getSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		//执行插入
		try {
			session.save(emp);
			ts.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			ts.rollback();
		}finally{
			//关闭Session
			session.close();
		}
	}
	@Test
	//修改Emp
	public void test3(){
		//创建Session
		Session session = HibernateUtil.getSession();
		//查询Emp根据Id
		Emp emp=(Emp)session.get(Emp.class, 16);
		//开启事务
		Transaction ts=session.beginTransaction();
		//执行修改
		emp.setName("猪八戒");
		try {
			session.update(emp);
			ts.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			ts.rollback();
		}finally{
			//关闭Session
			session.close();
		}
		
	}
	@Test
	//删除Emp
	public void test4(){
		//创建Session
		Session session = HibernateUtil.getSession();
		//要删除的Emp
		Emp emp=new Emp();
		emp.setId(10);
		//开启事务
		Transaction ts=session.beginTransaction();
		//执行删除
		try {
			//默认以Id查找Emp，然后删除
			session.delete(emp);
			ts.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			ts.rollback();
		}finally{
			//关闭Session
			session.close();
		}
	}
	@Test
	//查询全部Emp
	public void test5(){
		//创建Session
		Session session = HibernateUtil.getSession();
		/*
		 * hql中可以包含类名和方法名，不能包含表名和字段名，大小写敏感。
		 * hql是面向对象的
		 */
		String hql="from Emp";//Emp是类名,不是表名
		//编译hql,创建查询对象
		Query query=session.createQuery(hql);
		//执行查询
		List<Emp> list=query.list();
		for(Emp e:list){
			System.out.println(e.getName());
		}
		session.close();
	}


	
}

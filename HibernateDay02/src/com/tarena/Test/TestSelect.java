package com.tarena.Test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.Test;

import com.tarena.entity.Account;
import com.tarena.util.HibernateUtil;

public class TestSelect {
	/*
	 * 测试HQL查询，按条件查询
	 */
	@Test
	public void test1(){
		Session session=HibernateUtil.getSession();
		//注意都是类名和类的属性不是表明和字段名
		String hql="from Account where recommenderId=? and status=?";
		Query query=session.createQuery(hql);
		query.setInteger(0, 1010);
		query.setString(1, "2");
		List<Account> accounts=query.list();
		for(Account a:accounts){
			System.out.println(a.getRealName());
		}
		//当确定只有一个查询结果时可以用query.uniqueResult()
		Account account2=(Account)query.uniqueResult();
		System.out.println(account2.getRealName());
	}
	/*
	 * 测试HQL查询，查询一部分字段
	 * 不支持Select *
	 */
	@Test
	public void test2(){
		Session session=HibernateUtil.getSession();
		//注意都是类名和类的属性不是表明和字段名
		String hql="select accountId,realName from Account where recommenderId=? and status=?";
		Query query=session.createQuery(hql);
		query.setInteger(0, 1010);
		query.setString(1, "2");
		//查询一部分字段时，返回时的不再是对象集合，
		//而是Object[]集合，每一个数组中的值是依次查询出的数据
		List<Object[]> list=query.list();
		for(Object[] o:list){
			System.out.println(o[0]);
			System.out.println(o[1]);
		}
	}
	/*
	 * 测试HQL查询，分页查询
	 */
	@Test
	public void test3(){
		//查询每页数据
		int currentPage=2;
		int pageSize=5;
		int form=(currentPage-1)*pageSize;
		Session session=HibernateUtil.getSession();
		String hql=" from Account ";
		Query query=session.createQuery(hql);
		query.setFirstResult(form);
		query.setMaxResults(pageSize);
		List<Account> accounts=query.list();
		for(Account a:accounts){
			System.out.println(a.getRealName());
		}
		
		//计算总页数
		//hql不支持数据库函数，除了聚合函数
		String hql1="select count(*) from Account";
		Query query1=session.createQuery(hql1);
		Long totalAccount=(Long)query1.uniqueResult();
		long totalPages=totalAccount/pageSize;
		System.out.println( totalPages);
	}
	/*
	 * 测试多表联合查询，对象方式查询
	 */
	@Test
	public void test4(){
		Session session=HibernateUtil.getSession();
		//注意都是类名和类的属性不是表明和字段名
		String hql="select s.serviceId,a.accountId from Account a ," +
				"Service s where s.account.accountId=a.accountId";
		Query query=session.createQuery(hql);
		List<Object[]> list=query.list();
		for(Object[] o:list){
			System.out.println(o[0]);
			System.out.println(o[1]);
		}
	}
	/*
	 * 测试多表联合查询，join方式关联
	 */
	@Test
	public void test5(){
		Session session=HibernateUtil.getSession();
		//注意都是类名和类的属性不是表明和字段名
		String hql="select s.serviceId,a.accountId from Account a " +
				"inner join a.services s";
		Query query=session.createQuery(hql);
		List<Object[]> list=query.list();
		for(Object[] o:list){
			System.out.println(o[0]);
			System.out.println(o[1]);
		}
	}
	/*
	 * 测试多表联合查询，select子句关联
	 */
	@Test
	public void test6(){
		Session session=HibernateUtil.getSession();
		//注意都是类名和类的属性不是表明和字段名
		String hql="select s.serviceId,account.accountId from " +
				" Service s";
		Query query=session.createQuery(hql);
		List<Object[]> list=query.list();
		for(Object[] o:list){
			System.out.println(o[0]);
			System.out.println(o[1]);
		}
	}
	/*
	 * 测试其它查询，直接使用SQL查询
	 */
	@Test
	public void test7(){
		Session session=HibernateUtil.getSession();
		//注意在这直接是sal语句，* 可以使用，使用的表名和字段名
		String sql="select * from account where account_id=?";
		SQLQuery sqlquery=session.createSQLQuery(sql);
		sqlquery.setInteger(0, 1005);
		List<Object[]> list=sqlquery.list();
		for(Object[] o:list){
			System.out.println(o[0]);
			System.out.println(o[1]);
			System.out.println(o[2]);
		}
		//SQLQuery可以设置集合中封装的类型,Query就没有此方法
		sqlquery.addEntity(Account.class);
		List<Account> list2=sqlquery.list();
		for(Account a:list2){
			System.out.println(a.getRealName());
		}
		
		//唯一查询结果
		Account account=(Account)sqlquery.uniqueResult();
		System.out.println(account.getRealName());
	}
}

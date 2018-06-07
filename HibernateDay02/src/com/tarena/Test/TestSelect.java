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
	 * ����HQL��ѯ����������ѯ
	 */
	@Test
	public void test1(){
		Session session=HibernateUtil.getSession();
		//ע�ⶼ��������������Բ��Ǳ������ֶ���
		String hql="from Account where recommenderId=? and status=?";
		Query query=session.createQuery(hql);
		query.setInteger(0, 1010);
		query.setString(1, "2");
		List<Account> accounts=query.list();
		for(Account a:accounts){
			System.out.println(a.getRealName());
		}
		//��ȷ��ֻ��һ����ѯ���ʱ������query.uniqueResult()
		Account account2=(Account)query.uniqueResult();
		System.out.println(account2.getRealName());
	}
	/*
	 * ����HQL��ѯ����ѯһ�����ֶ�
	 * ��֧��Select *
	 */
	@Test
	public void test2(){
		Session session=HibernateUtil.getSession();
		//ע�ⶼ��������������Բ��Ǳ������ֶ���
		String hql="select accountId,realName from Account where recommenderId=? and status=?";
		Query query=session.createQuery(hql);
		query.setInteger(0, 1010);
		query.setString(1, "2");
		//��ѯһ�����ֶ�ʱ������ʱ�Ĳ����Ƕ��󼯺ϣ�
		//����Object[]���ϣ�ÿһ�������е�ֵ�����β�ѯ��������
		List<Object[]> list=query.list();
		for(Object[] o:list){
			System.out.println(o[0]);
			System.out.println(o[1]);
		}
	}
	/*
	 * ����HQL��ѯ����ҳ��ѯ
	 */
	@Test
	public void test3(){
		//��ѯÿҳ����
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
		
		//������ҳ��
		//hql��֧�����ݿ⺯�������˾ۺϺ���
		String hql1="select count(*) from Account";
		Query query1=session.createQuery(hql1);
		Long totalAccount=(Long)query1.uniqueResult();
		long totalPages=totalAccount/pageSize;
		System.out.println( totalPages);
	}
	/*
	 * ���Զ�����ϲ�ѯ������ʽ��ѯ
	 */
	@Test
	public void test4(){
		Session session=HibernateUtil.getSession();
		//ע�ⶼ��������������Բ��Ǳ������ֶ���
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
	 * ���Զ�����ϲ�ѯ��join��ʽ����
	 */
	@Test
	public void test5(){
		Session session=HibernateUtil.getSession();
		//ע�ⶼ��������������Բ��Ǳ������ֶ���
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
	 * ���Զ�����ϲ�ѯ��select�Ӿ����
	 */
	@Test
	public void test6(){
		Session session=HibernateUtil.getSession();
		//ע�ⶼ��������������Բ��Ǳ������ֶ���
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
	 * ����������ѯ��ֱ��ʹ��SQL��ѯ
	 */
	@Test
	public void test7(){
		Session session=HibernateUtil.getSession();
		//ע������ֱ����sal��䣬* ����ʹ�ã�ʹ�õı������ֶ���
		String sql="select * from account where account_id=?";
		SQLQuery sqlquery=session.createSQLQuery(sql);
		sqlquery.setInteger(0, 1005);
		List<Object[]> list=sqlquery.list();
		for(Object[] o:list){
			System.out.println(o[0]);
			System.out.println(o[1]);
			System.out.println(o[2]);
		}
		//SQLQuery�������ü����з�װ������,Query��û�д˷���
		sqlquery.addEntity(Account.class);
		List<Account> list2=sqlquery.list();
		for(Account a:list2){
			System.out.println(a.getRealName());
		}
		
		//Ψһ��ѯ���
		Account account=(Account)sqlquery.uniqueResult();
		System.out.println(account.getRealName());
	}
}

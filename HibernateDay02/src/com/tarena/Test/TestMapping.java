package com.tarena.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.tarena.entity.Account;
import com.tarena.entity.Service;
import com.tarena.util.HibernateUtil;

/*
 * ���Թ���ӳ��
 */
public class TestMapping {
	/*
	 * ����һ�Զ�
	 */
	@Test
	public void test1(){
		Session session=HibernateUtil.getSession();
		Account account=(Account)session.get(Account.class, 1011);
		System.out.println(account.getRealName());
		System.out.println("--------------------------");
		//��������Ĭ���ӳټ���
		Set<Service> services=account.getServices();
		for(Service s:services){
			System.out.println(s.getOsUserName());
		}
		session.close();
	}
	/*
	 * ���Զ��һ
	 */
	@Test
	public void test2(){
		Session session=HibernateUtil.getSession();
		Service service=(Service)session.get(Service.class,2001);
		System.out.println(service.getOsUserName());
		System.out.println("--------------------");
		Account account=service.getAccount();
		//��������Ĭ���ӳټ���
		System.out.println(account.getLoginName());
		session.close();
	}
	/*
	 * ���Լ������
	 */
	@Test
	public void test3(){
		Session session=HibernateUtil.getSession();
		//ģ��Ҫ��ӵ�account
		Account account=new Account();
		account.setBirthdate(Date.valueOf("1811-2-2"));
		account.setCloseDate(null);
		account.setCreateDate(new Timestamp(System.currentTimeMillis()));
		account.setEmail("123222@qq.com");
		account.setGender("1");
		account.setIdcardNo("123456199003170715");
		account.setLastLoginIp("192.168.1.1");
		account.setLoginPassword("123456");
		account.setQq("5543648512");
		account.setRealName("ȡ��");
		account.setStatus("0");
		account.setTelephone("13930797668");
		account.setZipcode("052263");
		account.setLoginName("qujing");
		
		//ģ��Ҫ��ӵ�service
		Service service1=new Service();
		//����Ӧ������accountid,�����Ѿ�û���ˣ�����setAccount����
		service1.setAccount(account);
		service1.setOsUserName("qujing1");
		service1.setStatus("1");
		service1.setUnixHost("192.168.1.2");
		service1.setLoginPassword("123456");
		service1.setCostId(1);
		Service service2=new Service();
		service2.setAccount(account);
		service2.setOsUserName("qujing2");
		service2.setStatus("1");
		service2.setUnixHost("192.168.1.3");
		service2.setLoginPassword("123456");
		service2.setCostId(2);
		
		
		Set<Service> services=new HashSet();
		services.add(service1);
		services.add(service2);
//		account.setServices(new HashSet<Service>());
//		account.getServices().add(service1);
//		account.getServices().add(service2);
		//ͨ���������ԣ����Service����serviceע�뵽account����
		account.setServices(services);
		Transaction ts=session.beginTransaction();
		try {
			//���accountʱͨ�����������Զ����service
			session.save(account);
			ts.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			ts.rollback();
		}finally{
			session.close();
		}
	}
	
	/*
	 * ���Լ����޸�
	 */
	@Test
	public void test4(){
		Session session=HibernateUtil.getSession();
		//ģ��Ҫ�޸õ�Account
		Account account=(Account)session.get(Account.class,2060);
		//�޸�Account
		account.setRealName("ggll");
		account.setLoginName("ggll");
		//�޸Ĺ�����Service
		Set<Service> services=account.getServices();
		for(Service s:services){
			s.setLoginPassword("123456789");
		}
		Transaction ts=session.beginTransaction();
		try {
			//�޸�accountʱͨ�����������Զ��޸�service
			session.update(account);
			ts.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			ts.rollback();
		}finally{
			session.close();
		}
	}
	/*
	 * ��漶��ɾ��
	 * ���������ÿ��Ʒ�ת
	 */
	@Test
	public void test5(){
		Session session=HibernateUtil.getSession();
		//ģ��Ҫɾ����Account
		Account account=(Account)session.get(Account.class,2060);
		Transaction ts=session.beginTransaction();
		try {
			//ɾ��accountʱͨ�����������Զ�ɾ��service
			session.delete(account);
			ts.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			ts.rollback();
		}finally{
			session.close();
		}
	}
	
}

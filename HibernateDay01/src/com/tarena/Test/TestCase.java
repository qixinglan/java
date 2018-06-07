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
	 * session.close()��ʾ����ǰ�Ự���ӹر�,�Ͽ������ݿ������,
	 * �����Ҫ�ٴ��������ݿ���Ҫ�ٴλ�ȡsession,���в���.
	 * ���session���رջ�,���ᵼ�����ݿ�����������,����ϵͳ���л���
	 */
	@Test
	//����Id��ѯEmp
	public void test1(){
		//����Session
		Session session = HibernateUtil.getSession();
		//��ѯEmp����Id
		Emp emp=(Emp)session.get(Emp.class, 1);
		System.out.println("name:"+emp.getName()+"\n"+"Id:"+emp.getId());
		//�ر�Session
		session.close();
	}
	@Test
	//����Ա��
	public void test2(){
		Emp emp=new Emp();
		emp.setName("�����");
		emp.setAge(30);
		emp.setSalary(8000.0);
		emp.setBirthday(Date.valueOf("1984-05-06"));
		emp.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
		emp.setMarry(false);
		//����Session
		Session session = HibernateUtil.getSession();
		//��������
		Transaction ts=session.beginTransaction();
		//ִ�в���
		try {
			session.save(emp);
			ts.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			ts.rollback();
		}finally{
			//�ر�Session
			session.close();
		}
	}
	@Test
	//�޸�Emp
	public void test3(){
		//����Session
		Session session = HibernateUtil.getSession();
		//��ѯEmp����Id
		Emp emp=(Emp)session.get(Emp.class, 16);
		//��������
		Transaction ts=session.beginTransaction();
		//ִ���޸�
		emp.setName("��˽�");
		try {
			session.update(emp);
			ts.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			ts.rollback();
		}finally{
			//�ر�Session
			session.close();
		}
		
	}
	@Test
	//ɾ��Emp
	public void test4(){
		//����Session
		Session session = HibernateUtil.getSession();
		//Ҫɾ����Emp
		Emp emp=new Emp();
		emp.setId(10);
		//��������
		Transaction ts=session.beginTransaction();
		//ִ��ɾ��
		try {
			//Ĭ����Id����Emp��Ȼ��ɾ��
			session.delete(emp);
			ts.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			ts.rollback();
		}finally{
			//�ر�Session
			session.close();
		}
	}
	@Test
	//��ѯȫ��Emp
	public void test5(){
		//����Session
		Session session = HibernateUtil.getSession();
		/*
		 * hql�п��԰��������ͷ����������ܰ����������ֶ�������Сд���С�
		 * hql����������
		 */
		String hql="from Emp";//Emp������,���Ǳ���
		//����hql,������ѯ����
		Query query=session.createQuery(hql);
		//ִ�в�ѯ
		List<Emp> list=query.list();
		for(Emp e:list){
			System.out.println(e.getName());
		}
		session.close();
	}


	
}

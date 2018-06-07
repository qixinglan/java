package com.tarena.Test;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.tarena.entity.Emp;
import com.tarena.util.HibernateUtil;

/*
 * ���Զ���־��ԣ�ͨ���鿴�Ƿ��ӡsql���Կ����ܶ�ԭ��
 * ��ӡsql�ǽ��������ݿ��������ӡ����û�����ݿ����������id���ɣ�
 */
public class TestPersistent {
	/*
	 * ���Գ־�̬���������һ��������
	 * ͨ������save��Emp��һ�����󣬰�һ������־�̬��
	 * Ȼ��ͨ��get()��ѯ����������û�д�ӡSQL��
	 * ��û�д�ӡ֤���־�̬���������һ�������С�
	 */
	@Test
	public void test1(){
		Session session=HibernateUtil.getSession();
		Emp emp=new Emp();
		emp.setAge(25);
		emp.setBirthday(Date.valueOf("1990-05-06"));
		emp.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
		emp.setMarry(false);
		emp.setName("����");
		emp.setSalary(8000.0);
		Transaction ts=session.beginTransaction();
		try {
			//save()����ֻ�ǰ���ʱ̬�����Ϊ�־�̬����û���ύSQL,����������Id
			session.save(emp);
			//��ʱ�����Ѿ��ǳ־�̬�ˡ���ѯ���������һ�������ж�ȡ��
			System.out.println("1-----------------------");
			Emp emp1 = (Emp) session.get(Emp.class, emp.getId());
			System.out.println(emp1.getName());
			//commint�������ύSQL���ǵ���session.flush()ʵ�ֵġ������ύ����
			ts.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			ts.rollback();
		}finally{
			session.close();
		}
	}
	
	
	
	/*
	 * ���Գ־�̬��������Զ����µ����ݿ�
	 * ����һ��Ա�����ݣ�����Ա��������ǳ־�̬
	 * ֱ���޸������������ݣ����ύ���񡣿�����
	 * ����Ľ����Ρ�
	 */
	@Test
	public void test2(){
		Session session=HibernateUtil.getSession();
		Emp emp=new Emp();
		emp.setAge(25);
		emp.setBirthday(Date.valueOf("1990-05-06"));
		emp.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
		emp.setMarry(false);
		emp.setName("��˧");
		emp.setSalary(8000.0);
		Transaction ts=session.beginTransaction();
		try {
			//save()����ֻ�ǰ���ʱ̬�����Ϊ�־�̬����û���ύSQL,����������Id
			session.save(emp);
			System.out.println("1-----------------------");
			//��ʱ�����Ѿ��ǳ־�̬�ˡ���ѯ���������һ�������ж�ȡ��
			emp.setAge(24);//�޸�����������ݣ���ʱһ�������еĶ�������Ҳ���޸ġ�
			System.out.println("2-----------------------");
			//commint�������ύSQL���ǵ���session.flush()ʵ�ֵġ������ύ����
			ts.commit();
			//commint��ִ����һ��insertԭ���Ķ�����ִ����һ��Update���������������޸ġ�
			System.out.println("3-----------------------");
			//��һ���������ҵ������ݣ���û�����������ݿ��ѯ��
			Emp emp1 = (Emp) session.get(Emp.class, emp.getId());
			System.out.println(emp1.getAge());
		} catch (HibernateException e) {
			e.printStackTrace();
			ts.rollback();
		}finally{
			session.close();
		}
	}
	/**
	 * �Զ�����ʱ����session.flush()
	 * ��ѯһ��Ա�����ݣ����ѯ���Ķ����ǳ־�̬�ġ�
	 * �޸���������ֵ��Ȼ�����session.flush����
	 * ������̨��ӡ��SQL
	 */
	@Test
	public void test3() {
		
		Session session = HibernateUtil.getSession();
		//Transaction ts=session.beginTransaction();
		Emp e = (Emp) session.get(Emp.class, 1);
		e.setName("�ĺ");
		session.flush();//����ִ�и���update
		//ts.commit();
		//���Կ�����ӡ��update�����ǲ�û���ύ�������Բ�ѯ���ݿ⿴�������¡�
		session.close();
	}
}

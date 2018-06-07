package com.tarena.Test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.junit.Test;

import com.sun.org.apache.xalan.internal.xsltc.cmdline.Transform;
import com.sun.org.apache.xml.internal.security.encryption.Transforms;
import com.tarena.entity.Emp;
import com.tarena.util.HibernateUtil;

/*
 * hibernate��ѯ��ʽ
 * 1HQL
 * 2Criteria
 * 3SQL
 */
public class HibernateDQL{
	@Test
	public void testHql(){
		//������������ȫ��javaʵ�����Ĳ������ݿ��
		Session session = HibernateUtil.getSession();
		String hql="select name from Emp where id =��";
		Query query=session.createQuery(hql);
		query.setString(0, "12");
		//query.setEntity(arg0, arg1)����������ݶ����ѯ
		List list=query.list();//�������鼯��
		String hql1="from Emp where id =��id";
		Query query1=session.createQuery(hql);
		query.setString("id", "12");
		//query.setEntity("id��, entity)����������ݶ����ѯ
		List list1=query1.list();//����object���󼯺ϣ�sql��ѯ����ָ���������ֶ���hql������
		//query.iterate();
		query.setResultTransformer(TransForms.A);
		session.close();
	}
	@Test
	public void testCriteria(){
		Session session=HibernateUtil.getSession();
		 //criteria��ѯȫ��
		Criteria criteria=session.createCriteria(Emp.class);
		List<Emp> list=criteria.list();
		//��ѯ�����ֶ�
		Criteria criteria1=session.createCriteria(Emp.class);
		//ʹ��ͶӰ��Projecttion
		criteria1.setProjection(Projections.property("name"));//ֻ��ѯһ���ֶ�
		criteria1.setProjection(Projections.projectionList()
				.add(Projections.property("name"),"����").//�����б���
				add(Projections.property("age")));//��ѯ����ֶ�
		criteria1.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		criteria1.setResultTransformer(Transformers.TO_LIST);
		criteria1.setResultTransformer(Transformers.aliasToBean(Emp.class));
		List<Object[]> list1=criteria1.list();//����object���鼯��
		
	}
}
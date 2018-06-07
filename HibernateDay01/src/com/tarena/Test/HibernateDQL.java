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
 * hibernate查询方式
 * 1HQL
 * 2Criteria
 * 3SQL
 */
public class HibernateDQL{
	@Test
	public void testHql(){
		//表明和属性名全是java实体对象的并非数据库的
		Session session = HibernateUtil.getSession();
		String hql="select name from Emp where id =？";
		Query query=session.createQuery(hql);
		query.setString(0, "12");
		//query.setEntity(arg0, arg1)对象参数根据对象查询
		List list=query.list();//返回数组集合
		String hql1="from Emp where id =：id";
		Query query1=session.createQuery(hql);
		query.setString("id", "12");
		//query.setEntity("id“, entity)对象参数根据对象查询
		List list1=query1.list();//返回object对象集合，sql查询可以指定返回哪种对象，hql不可以
		//query.iterate();
		query.setResultTransformer(TransForms.A);
		session.close();
	}
	@Test
	public void testCriteria(){
		Session session=HibernateUtil.getSession();
		 //criteria查询全部
		Criteria criteria=session.createCriteria(Emp.class);
		List<Emp> list=criteria.list();
		//查询部分字段
		Criteria criteria1=session.createCriteria(Emp.class);
		//使用投影类Projecttion
		criteria1.setProjection(Projections.property("name"));//只查询一个字段
		criteria1.setProjection(Projections.projectionList()
				.add(Projections.property("name"),"别名").//可以有别名
				add(Projections.property("age")));//查询多个字段
		criteria1.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		criteria1.setResultTransformer(Transformers.TO_LIST);
		criteria1.setResultTransformer(Transformers.aliasToBean(Emp.class));
		List<Object[]> list1=criteria1.list();//返回object数组集合
		
	}
}
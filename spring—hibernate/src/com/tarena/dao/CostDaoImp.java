package com.tarena.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tarena.entity.Cost;

@Repository
public class CostDaoImp extends HibernateDaoSupport implements CostDao{
		//½«SessionFactory×¢Èë¸øsetSF()
		@Resource
		public void setSF(SessionFactory sf) {
			super.setSessionFactory(sf);
		}
	public List<Cost> findAll() {
		// TODO Auto-generated method stub
		String hql="from Cost";
		return getHibernateTemplate().find(hql);
	}

	public Cost findById(int id) {
		// TODO Auto-generated method stub
		return (Cost) getHibernateTemplate().get(Cost.class, id);
	}

	public void save(Cost cost) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(cost);
	}

	public void update(Cost cost) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(cost);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		Cost cost=new Cost();
		cost.setCostId(id);
		getHibernateTemplate().delete(cost);
	}
	public static void main(String[] args) {
		String conf="applicationContext.xml";
		System.out.println("aa");
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		System.out.println("aa");
		CostDaoImp costDaoImp=(CostDaoImp)ac.getBean("costDaoImp");
		List<Cost> costs=costDaoImp.findAll();
	
		for(Cost c:costs){
			System.out.println(c.getName());
		}
	}

}

package com.tarena.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tarena.entity.Cost;
import com.tarena.entity.costPage;

@Repository
public class CostDaoImp extends HibernateDaoSupport implements CostDao{
		//将SessionFactory注入给setSF()
		@Resource
		public void setSF(SessionFactory sf) {
			super.setSessionFactory(sf);
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

	//查询总条数
	public int findTotalRows() {
		// TODO Auto-generated method stub
		String hql="select count(*) from Cost";
		List list=getHibernateTemplate().find(hql);
		Long l=(Long)list.get(0);//返回的是Long类型，不能强转为INteger
		int TotalRows=Integer.parseInt(String.valueOf(l));
		return TotalRows;
	}
	

	//分页查询Cost
	
	public List<Cost> findCost(final costPage page) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session arg0) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				String hql="from Cost order by costId";
				Query query=arg0.createQuery(hql);
				int currentPage=page.getCurrentPage();
				int pageSize=page.getPageSize();
				query.setFirstResult((currentPage-1)*pageSize);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
	}
	
	

}

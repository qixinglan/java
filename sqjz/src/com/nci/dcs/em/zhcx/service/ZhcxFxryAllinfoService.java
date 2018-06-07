package com.nci.dcs.em.zhcx.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.em.zhcx.dao.ZhcxFxryAllinfoDao;
import com.nci.dcs.em.zhcx.model.ViewCcFxryAllinfo;
import com.nci.dcs.monitor.server.model.ServiceMonitor;

@Service
@Transactional
public class ZhcxFxryAllinfoService extends
		BaseService<ViewCcFxryAllinfo, String> {

	@Autowired
	ZhcxFxryAllinfoDao dao;

	public ZhcxFxryAllinfoDao getDao() {
		return this.dao;
	}

	public void setDao(ZhcxFxryAllinfoDao dao) {
		this.dao = dao;
	}

	@Override
	public void create(ViewCcFxryAllinfo entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ViewCcFxryAllinfo entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ViewCcFxryAllinfo get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<ViewCcFxryAllinfo> find() {
		// TODO Auto-generated method stub
		return dao.find("from ViewCcFxryAllinfo", null);
	}
	
	@Override
	public Page<ViewCcFxryAllinfo> findPaged(Page<ViewCcFxryAllinfo> page,
			ViewCcFxryAllinfo entity) {
		// TODO Auto-generated method stub
		return dao.findByFilters(page, this.buildPropertyFilter(entity));
	}

	@Override
	public void enable(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disable(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void audit(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<ViewCcFxryAllinfo> findPaged(Page<ViewCcFxryAllinfo> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewCcFxryAllinfo entity) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		return filters;
	}
	
	public List<ViewCcFxryAllinfo> find(String col){
		return dao.find("select "+col+" from ViewCcFxryAllinfo ");
	}

	public Page<ViewCcFxryAllinfo> findPaged(Page<ViewCcFxryAllinfo> page) {
		return dao.findByCriteria(page);
	}
	public List<ViewCcFxryAllinfo> findPaged(final Page<ViewCcFxryAllinfo> page, List<SearchRule> searchRules,List<String> columnNames) {
		return dao.findBySeachRule(page,searchRules,columnNames).getResult();
	}
	
	public Page<ViewCcFxryAllinfo> findPaged(Page<ViewCcFxryAllinfo> page,
			List<SearchRule> rules) {
		return dao.findBySeachRule(page, rules);
	}
	
	public List<ViewCcFxryAllinfo> findByCriteria(List<Criterion> criterions, Projection projection) {
		Criteria criteria = dao.createCriteria(criterions);
		if (null != projection) {
			criteria.setProjection(projection);
		}
		if (null != criteria) {
			return criteria.list();
		}
		return null;
	}
	
	public Criteria createCriteria(List<Criterion> criterions, Class table,
			Projection projection, List<Order> orders) {
		Criteria criteria = dao.getSession().createCriteria(
				table);
		if (criterions != null) {
			for (Criterion c : criterions) {
				criteria.add(c);
			}
		}
		if (CommonUtils.isNotNull(orders)) {
			for (Order order : orders) {
				criteria.addOrder(order);
			}
		}
		if (null != projection) {
			criteria.setProjection(projection);
		}
		return criteria;
	}
	
	public List findBySql(String sql,Object... value){
		Query q = dao.createSQLQuery(sql,value);
		return q.list();
	}
	public Page<ViewCcFxryAllinfo> findPageByCriteria(Criteria criteria, final Page<ViewCcFxryAllinfo> page) {
		return dao.findPageByCriteria(criteria, page);
	}
}

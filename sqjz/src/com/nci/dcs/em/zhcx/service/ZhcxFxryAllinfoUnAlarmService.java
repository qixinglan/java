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
import com.nci.dcs.em.zhcx.dao.ZhcxFxryAllinfoUnAlarmDao;
import com.nci.dcs.em.zhcx.model.ViewCcFxryAllinfoUnAlarm;
import com.nci.dcs.monitor.server.model.ServiceMonitor;

@Service
@Transactional
public class ZhcxFxryAllinfoUnAlarmService extends
		BaseService<ViewCcFxryAllinfoUnAlarm, String> {

	@Autowired
	ZhcxFxryAllinfoUnAlarmDao dao;

	public ZhcxFxryAllinfoUnAlarmDao getDao() {
		return this.dao;
	}

	public void setDao(ZhcxFxryAllinfoUnAlarmDao dao) {
		this.dao = dao;
	}

	@Override
	public void create(ViewCcFxryAllinfoUnAlarm entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ViewCcFxryAllinfoUnAlarm entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ViewCcFxryAllinfoUnAlarm get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<ViewCcFxryAllinfoUnAlarm> find() {
		// TODO Auto-generated method stub
		return dao.find("from ViewCcFxryAllinfoUnAlarm", null);
	}
	
	@Override
	public Page<ViewCcFxryAllinfoUnAlarm> findPaged(Page<ViewCcFxryAllinfoUnAlarm> page,
			ViewCcFxryAllinfoUnAlarm entity) {
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
	public Page<ViewCcFxryAllinfoUnAlarm> findPaged(Page<ViewCcFxryAllinfoUnAlarm> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewCcFxryAllinfoUnAlarm entity) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		return filters;
	}
	
	public List<ViewCcFxryAllinfoUnAlarm> find(String col){
		return dao.find("select "+col+" from ViewCcFxryAllinfo ");
	}

	public Page<ViewCcFxryAllinfoUnAlarm> findPaged(Page<ViewCcFxryAllinfoUnAlarm> page) {
		return dao.findByCriteria(page);
	}
	public List<ViewCcFxryAllinfoUnAlarm> findPaged(final Page<ViewCcFxryAllinfoUnAlarm> page, List<SearchRule> searchRules,List<String> columnNames) {
		return dao.findBySeachRule(page,searchRules,columnNames).getResult();
	}
	
	public Page<ViewCcFxryAllinfoUnAlarm> findPaged(Page<ViewCcFxryAllinfoUnAlarm> page,
			List<SearchRule> rules) {
		return dao.findBySeachRule(page, rules);
	}
	
	public List<ViewCcFxryAllinfoUnAlarm> findByCriteria(List<Criterion> criterions, Projection projection) {
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
	public Page<ViewCcFxryAllinfoUnAlarm> findPageByCriteria(Criteria criteria, final Page<ViewCcFxryAllinfoUnAlarm> page) {
		return dao.findPageByCriteria(criteria, page);
	}
}

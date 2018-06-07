package com.nci.dcs.jzgl.cxtj.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.jzgl.cxtj.dao.ViewReportFxryInfoDao;

@Service
@Transactional
@SuppressWarnings("rawtypes")
public class ViewReportFxryInfoService {

	@Autowired
	private ViewReportFxryInfoDao viewReportFxryInfoDao;

	public Criteria createCriteria(List<Criterion> criterions, Class table,
			Projection projection, List<Order> orders) {
		Criteria criteria = viewReportFxryInfoDao.getSession().createCriteria(
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

	public List findByCriteria(List<Criterion> criterions, Class table,
			Projection projection, List<Order> orders) {
		Criteria criteria = createCriteria(criterions, table, projection,
				orders);
		if (null != criteria) {
			return criteria.list();
		}
		return null;
	}

	public List findByCriteria(Criteria criteria) {
		return criteria.list();
	}
}
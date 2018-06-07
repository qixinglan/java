package com.nci.dcs.jzgl.sync.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.sync.dao.FxrySyncInfoDao;
import com.nci.dcs.jzgl.sync.model.FxrySyncInfo;

@Service
@Transactional
public class FxrySyncinfoService extends BaseService<FxrySyncInfo, String> {

	@Autowired
	private FxrySyncInfoDao fxrySyncInfoDao;

	public Page<FxrySyncInfo> findPaged(Page<FxrySyncInfo> page) {
		return fxrySyncInfoDao.findByCriteria(page);
	}

	public int countCriteriaResult(final Criterion... criterions) {
		Criteria c = fxrySyncInfoDao.createCriteria(criterions);
		Object o = c.setProjection(Projections.rowCount()).uniqueResult();
		int totalCount = 0;
		if (o != null) {
			totalCount = (Integer) o;
		}
		return totalCount;
	}

	@SuppressWarnings("unchecked")
	public List<FxrySyncInfo> findPagedByCriteria(int first, int max,
			Order order, final Criterion... criterions) {
		Criteria criteria = fxrySyncInfoDao.createCriteria(criterions);
		criteria.setFirstResult(first);
		criteria.setMaxResults(max);
		if (null != order) {
			criteria.addOrder(order);
		}
		return criteria.list();
	}

	@Override
	public void create(FxrySyncInfo entity) {
		fxrySyncInfoDao.save(entity);
	}

	@Override
	public void update(FxrySyncInfo entity) {
	}

	@Override
	public void delete(String id) {
		fxrySyncInfoDao.delete(id);
	}

	@Override
	public FxrySyncInfo get(String id) {
		return fxrySyncInfoDao.get(id);
	}

	@Override
	public List<FxrySyncInfo> find() {
		return null;
	}

	@Override
	public Page<FxrySyncInfo> findPaged(Page<FxrySyncInfo> page,
			FxrySyncInfo entity) {
		return fxrySyncInfoDao.findByCriteria(page);
	}

	@Override
	public void enable(String id) throws Exception {

	}

	@Override
	public void disable(String id) throws Exception {

	}

	@Override
	public void audit(String id) throws Exception {

	}

	@Override
	public Page<FxrySyncInfo> findPaged(Page<FxrySyncInfo> page, String hql,
			Object... values) {
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FxrySyncInfo entity) {
		return null;
	}

}

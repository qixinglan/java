package com.nci.dcs.supervision.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.supervision.dao.SupervisionModuleDao;
import com.nci.dcs.supervision.model.SupervisionModule;

@Service
@Transactional
public class SupervisionModuleService extends
		BaseService<SupervisionModule, String> {

	@Autowired
	private SupervisionModuleDao supervisionModuleDao;

	public List<SupervisionModule> findByCriteria(final Criterion... criterions) {
		return supervisionModuleDao.findByCriteria(criterions);
	}

	@Override
	public void create(SupervisionModule entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(SupervisionModule entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public SupervisionModule get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SupervisionModule> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SupervisionModule> findPaged(Page<SupervisionModule> page,
			SupervisionModule entity) {
		// TODO Auto-generated method stub
		return null;
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
	public Page<SupervisionModule> findPaged(Page<SupervisionModule> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(SupervisionModule entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

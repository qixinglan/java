package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.FxryCrimeDao;
import com.nci.dcs.jzgl.dagl.model.FxryCrime;

@Service
@Transactional
public class FxryCrimeService extends BaseService<FxryCrime, String> {
	@Autowired
	private FxryCrimeDao fxryCrimeDao;

	public Page<FxryCrime> findPaged(Page<FxryCrime> page) {
		return fxryCrimeDao.findByCriteria(page);
	}

	@Override
	public void create(FxryCrime entity) {
		fxryCrimeDao.save(entity);
	}

	@Override
	public void update(FxryCrime entity) {
		fxryCrimeDao.save(entity);
	}

	@Override
	public void delete(String id) {
		fxryCrimeDao.delete(id);
	}

	@Override
	public FxryCrime get(String id) {
		return fxryCrimeDao.get(id);
	}

	@Override
	public List<FxryCrime> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FxryCrime> findPaged(Page<FxryCrime> page, FxryCrime entity) {
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
	public Page<FxryCrime> findPaged(Page<FxryCrime> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FxryCrime entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
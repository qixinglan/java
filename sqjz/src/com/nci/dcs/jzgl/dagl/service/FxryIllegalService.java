package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.FxryIllegalDao;
import com.nci.dcs.jzgl.dagl.model.FxryIllegal;

@Service
@Transactional
public class FxryIllegalService extends BaseService<FxryIllegal, String> {
	@Autowired
	private FxryIllegalDao fxryIllegalDao;

	public Page<FxryIllegal> findPaged(Page<FxryIllegal> page) {
		return fxryIllegalDao.findByCriteria(page);
	}

	@Override
	public void create(FxryIllegal entity) {
		fxryIllegalDao.save(entity);
	}

	@Override
	public void update(FxryIllegal entity) {
		fxryIllegalDao.save(entity);
	}

	@Override
	public void delete(String id) {
		fxryIllegalDao.delete(id);
	}

	@Override
	public FxryIllegal get(String id) {
		return null;
	}

	@Override
	public List<FxryIllegal> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FxryIllegal> findPaged(Page<FxryIllegal> page,
			FxryIllegal entity) {
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
	public Page<FxryIllegal> findPaged(Page<FxryIllegal> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FxryIllegal entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
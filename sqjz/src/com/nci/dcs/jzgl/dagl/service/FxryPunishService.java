package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.FxryPunishDao;
import com.nci.dcs.jzgl.dagl.model.FxryPunish;

@Service
@Transactional
public class FxryPunishService extends BaseService<FxryPunish, String> {
	@Autowired
	private FxryPunishDao fxryPunishDao;

	public Page<FxryPunish> findPaged(Page<FxryPunish> page) {
		return fxryPunishDao.findByCriteria(page);
	}

	@Override
	public void create(FxryPunish entity) {
		fxryPunishDao.save(entity);
	}

	@Override
	public void update(FxryPunish entity) {
		fxryPunishDao.save(entity);
	}

	@Override
	public void delete(String id) {
		fxryPunishDao.delete(id);
	}

	@Override
	public FxryPunish get(String id) {
		return null;
	}

	@Override
	public List<FxryPunish> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FxryPunish> findPaged(Page<FxryPunish> page, FxryPunish entity) {
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
	public Page<FxryPunish> findPaged(Page<FxryPunish> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FxryPunish entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
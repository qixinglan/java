package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.FxryRewardDao;
import com.nci.dcs.jzgl.dagl.model.FxryReward;

@Service
@Transactional
public class FxryRewardService extends BaseService<FxryReward, String> {
	@Autowired
	private FxryRewardDao fxryRewardDao;

	public Page<FxryReward> findPaged(Page<FxryReward> page) {
		return fxryRewardDao.findByCriteria(page);
	}

	@Override
	public void create(FxryReward entity) {
		fxryRewardDao.save(entity);
	}

	@Override
	public void update(FxryReward entity) {
		fxryRewardDao.save(entity);
	}

	@Override
	public void delete(String id) {
		fxryRewardDao.delete(id);
	}

	@Override
	public FxryReward get(String id) {
		return null;
	}

	@Override
	public List<FxryReward> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FxryReward> findPaged(Page<FxryReward> page, FxryReward entity) {
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
	public Page<FxryReward> findPaged(Page<FxryReward> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FxryReward entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
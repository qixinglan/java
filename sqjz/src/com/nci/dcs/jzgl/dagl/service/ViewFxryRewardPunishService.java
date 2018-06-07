package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.ViewFxryRewardPunishDao;
import com.nci.dcs.jzgl.dagl.model.ViewFxryRewardPunish;

@Service
@Transactional
public class ViewFxryRewardPunishService extends BaseService<ViewFxryRewardPunish, String> {
	@Autowired
	private ViewFxryRewardPunishDao viewFxryRewardPunishDao;

	public Page<ViewFxryRewardPunish> findPaged(Page<ViewFxryRewardPunish> page) {
		return viewFxryRewardPunishDao.findByCriteria(page);
	}

	@Override
	public void create(ViewFxryRewardPunish entity) {
	}

	@Override
	public void update(ViewFxryRewardPunish entity) {
	}

	@Override
	public void delete(String id) {
	}

	@Override
	public ViewFxryRewardPunish get(String id) {
		return viewFxryRewardPunishDao.get(id);
	}

	@Override
	public List<ViewFxryRewardPunish> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ViewFxryRewardPunish> findPaged(Page<ViewFxryRewardPunish> page, ViewFxryRewardPunish entity) {
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
	public Page<ViewFxryRewardPunish> findPaged(Page<ViewFxryRewardPunish> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewFxryRewardPunish entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
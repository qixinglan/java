package com.nci.dcs.em.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.em.dao.FxrySettingDao;
import com.nci.dcs.em.model.ViewFxrySetting;

@Service
@Transactional
public class FxrySettingService extends BaseService<ViewFxrySetting, String> {

	@Autowired
	private FxrySettingDao fxrySettingDao;

	public Page<ViewFxrySetting> findPersonPaged(Page<ViewFxrySetting> page,
			List<SearchRule> searchRules) {
		return fxrySettingDao.findBySeachRule(page, searchRules);
	}

	@Override
	public void create(ViewFxrySetting entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ViewFxrySetting entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ViewFxrySetting get(String id) {
		// TODO Auto-generated method stub
		return fxrySettingDao.get(id);
	}

	@Override
	public List<ViewFxrySetting> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ViewFxrySetting> findPaged(Page<ViewFxrySetting> page,
			ViewFxrySetting entity) {
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
	public Page<ViewFxrySetting> findPaged(Page<ViewFxrySetting> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewFxrySetting entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

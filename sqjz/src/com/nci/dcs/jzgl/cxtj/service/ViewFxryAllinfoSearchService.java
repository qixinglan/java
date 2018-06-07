package com.nci.dcs.jzgl.cxtj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.cxtj.dao.ViewFxryAllinfoSearchDao;
import com.nci.dcs.jzgl.cxtj.model.ViewCcFxryAllinfoSearch;

@Service
@Transactional
public class ViewFxryAllinfoSearchService extends
		BaseService<ViewCcFxryAllinfoSearch, String> {
	@Autowired
	private ViewFxryAllinfoSearchDao viewFxryAllinfoSearchDao;

	public Page<ViewCcFxryAllinfoSearch> findPaged(
			Page<ViewCcFxryAllinfoSearch> page) {
		return viewFxryAllinfoSearchDao.findByCriteria(page);
	}

	@Override
	public void create(ViewCcFxryAllinfoSearch entity) {
	}

	@Override
	public void update(ViewCcFxryAllinfoSearch entity) {
	}

	@Override
	public void delete(String id) {
	}

	@Override
	public ViewCcFxryAllinfoSearch get(String id) {
		return viewFxryAllinfoSearchDao.get(id);
	}

	@Override
	public List<ViewCcFxryAllinfoSearch> find() {
		return null;
	}

	@Override
	public Page<ViewCcFxryAllinfoSearch> findPaged(
			Page<ViewCcFxryAllinfoSearch> page, ViewCcFxryAllinfoSearch entity) {
		return viewFxryAllinfoSearchDao.findByCriteria(page);
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
	public Page<ViewCcFxryAllinfoSearch> findPaged(
			Page<ViewCcFxryAllinfoSearch> page, String hql, Object... values) {
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(
			ViewCcFxryAllinfoSearch entity) {
		return null;
	}
}
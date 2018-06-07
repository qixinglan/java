package com.nci.dcs.jzgl.fingerprint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.fingerprint.dao.FxryTuPianDao;
import com.nci.dcs.jzgl.fingerprint.model.CcFxryTupian;

@Service
@Transactional
public class FxryTuPianService extends BaseService<CcFxryTupian, String>{

	@Autowired
	FxryTuPianDao tuPianDao;

	@Override
	public void create(CcFxryTupian entity) {
		tuPianDao.save(entity);
	}

	@Override
	public void update(CcFxryTupian entity) {
		tuPianDao.save(entity);
		
	}

	@Override
	public void delete(String id) {
		tuPianDao.delete(id);
		
	}

	@Override
	public CcFxryTupian get(String id) {
		return tuPianDao.get(id);
	}

	@Override
	public List<CcFxryTupian> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CcFxryTupian> findPaged(Page<CcFxryTupian> page,
			CcFxryTupian entity) {
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
	public Page<CcFxryTupian> findPaged(Page<CcFxryTupian> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcFxryTupian entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.nci.dcs.em.dwjk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.em.dwjk.dao.FxryCrimeinfoDao;
import com.nci.dcs.em.dwjk.model.CcFxryCrimeinfo;

@Service
@Transactional
public class FxryCrimeinfoService extends BaseService<CcFxryCrimeinfo, String> {

	@Autowired
	FxryCrimeinfoDao dao;
	public FxryCrimeinfoDao getDao() {
		return dao;
	}

	public void setDao(FxryCrimeinfoDao dao) {
		this.dao = dao;
	}

	@Override
	public void create(CcFxryCrimeinfo entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CcFxryCrimeinfo entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CcFxryCrimeinfo get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<CcFxryCrimeinfo> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CcFxryCrimeinfo> findPaged(Page<CcFxryCrimeinfo> page,
			CcFxryCrimeinfo entity) {
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
	public Page<CcFxryCrimeinfo> findPaged(Page<CcFxryCrimeinfo> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcFxryCrimeinfo entity) {
		// TODO Auto-generated method stub
		return null;
	}


}

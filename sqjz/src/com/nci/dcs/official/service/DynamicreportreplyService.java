package com.nci.dcs.official.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.official.dao.DynamicreportreplyDao;
import com.nci.dcs.official.model.CcDynamicreportreply;

@Service
@Transactional
public class DynamicreportreplyService extends BaseService<CcDynamicreportreply, String> {

	@Autowired
	private DynamicreportreplyDao dao;
	
	public DynamicreportreplyDao getDao() {
		return dao;
	}

	public void setDao(DynamicreportreplyDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void create(CcDynamicreportreply entity) {
		dao.save(entity);
	}

	@Override
	public void update(CcDynamicreportreply entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public CcDynamicreportreply get(String id) {
		return dao.get(id);
	}

	@Override
	public List<CcDynamicreportreply> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CcDynamicreportreply> findPaged(
			Page<CcDynamicreportreply> page, CcDynamicreportreply entity) {
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
	public Page<CcDynamicreportreply> findPaged(
			Page<CcDynamicreportreply> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcDynamicreportreply entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

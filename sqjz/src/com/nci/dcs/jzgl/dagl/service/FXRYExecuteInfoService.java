package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.FXRYExecuteInfoDao;
import com.nci.dcs.jzgl.dagl.model.FXRYExecuteInfo;


@Service
@Transactional
public class FXRYExecuteInfoService extends BaseService<FXRYExecuteInfo, String>{
	
	@Autowired
	private FXRYExecuteInfoDao dao;
	
	@Override
	public void create(FXRYExecuteInfo entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(FXRYExecuteInfo entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}
	
	@Override
	public FXRYExecuteInfo get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<FXRYExecuteInfo> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FXRYExecuteInfo> findPaged(Page<FXRYExecuteInfo> page,
			FXRYExecuteInfo entity) {
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
	public Page<FXRYExecuteInfo> findPaged(Page<FXRYExecuteInfo> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FXRYExecuteInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public FXRYExecuteInfo getByFXRYId(String fxryId){
		return dao.findOneByProperty("fxryId", fxryId);
	}
	
	public void deleteByFXRYId(String fxryId){
		FXRYExecuteInfo entity = getByFXRYId(fxryId);
		if (entity != null){
			dao.delete(entity);
		}
	}
}

package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.FXRYEscortInfoDao;
import com.nci.dcs.jzgl.dagl.model.FXRYEscortInfo;


@Service
@Transactional
public class FXRYEscortInfoService extends BaseService<FXRYEscortInfo, String>{
	
	@Autowired
	private FXRYEscortInfoDao dao;
	
	@Override
	public void create(FXRYEscortInfo entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(FXRYEscortInfo entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public FXRYEscortInfo get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<FXRYEscortInfo> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FXRYEscortInfo> findPaged(Page<FXRYEscortInfo> page,
			FXRYEscortInfo entity) {
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
	public Page<FXRYEscortInfo> findPaged(Page<FXRYEscortInfo> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FXRYEscortInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FXRYEscortInfo> getByFXRYId(String fxryId){
		return dao.findByProperty("fxryId", fxryId);
	}
	
	public void deleteByFXRYId(String fxryId){
		List<FXRYEscortInfo> entities = getByFXRYId(fxryId);
		if (entities != null){
			for (FXRYEscortInfo entity : entities){
				dao.delete(entity);
			}
		}
	}
}

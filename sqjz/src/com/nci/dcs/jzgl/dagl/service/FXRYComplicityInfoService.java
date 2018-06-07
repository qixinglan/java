package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.FXRYComplicityInfoDao;
import com.nci.dcs.jzgl.dagl.model.FXRYComplicityInfo;


@Service
@Transactional
public class FXRYComplicityInfoService extends BaseService<FXRYComplicityInfo, String>{
	
	@Autowired
	private FXRYComplicityInfoDao dao;
	
	@Override
	public void create(FXRYComplicityInfo entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(FXRYComplicityInfo entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public FXRYComplicityInfo get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<FXRYComplicityInfo> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FXRYComplicityInfo> findPaged(Page<FXRYComplicityInfo> page,
			FXRYComplicityInfo entity) {
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
	public Page<FXRYComplicityInfo> findPaged(Page<FXRYComplicityInfo> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FXRYComplicityInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FXRYComplicityInfo> getByFXRYId(String fxryId){
		return dao.findByProperty("fxryId", fxryId);
	}
	
	public void deleteByFXRYId(String fxryId){
		List<FXRYComplicityInfo> entities = getByFXRYId(fxryId);
		if (entities != null){
			for (FXRYComplicityInfo entity : entities){
				dao.delete(entity);
			}
		}
	}
}

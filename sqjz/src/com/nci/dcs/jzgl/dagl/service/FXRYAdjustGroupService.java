package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.FXRYAdjustGroupDao;
import com.nci.dcs.jzgl.dagl.model.FXRYAdjustGroup;


@Service
@Transactional
public class FXRYAdjustGroupService extends BaseService<FXRYAdjustGroup, String>{
	
	@Autowired
	private FXRYAdjustGroupDao dao;
	
	@Override
	public void create(FXRYAdjustGroup entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(FXRYAdjustGroup entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public FXRYAdjustGroup get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<FXRYAdjustGroup> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FXRYAdjustGroup> findPaged(Page<FXRYAdjustGroup> page,
			FXRYAdjustGroup entity) {
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
	public Page<FXRYAdjustGroup> findPaged(Page<FXRYAdjustGroup> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FXRYAdjustGroup entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FXRYAdjustGroup> getByFXRYId(String fxryId){
		return dao.findByProperty("fxryId", fxryId);
	}
	
	public void deleteByFXRYId(String fxryId){
		List<FXRYAdjustGroup> entities = getByFXRYId(fxryId);
		if (entities != null){
			for (FXRYAdjustGroup entity : entities){
				dao.delete(entity);
			}
		}
	}
}

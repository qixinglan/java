package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.FXRYImprisonmentChangeDao;
import com.nci.dcs.jzgl.dagl.model.FXRYImprisonmentChange;


@Service
@Transactional
public class FXRYImprisonmentChangeService extends BaseService<FXRYImprisonmentChange, String>{
	
	@Autowired
	private FXRYImprisonmentChangeDao dao;
	
	@Override
	public void create(FXRYImprisonmentChange entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(FXRYImprisonmentChange entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public FXRYImprisonmentChange get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<FXRYImprisonmentChange> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FXRYImprisonmentChange> findPaged(Page<FXRYImprisonmentChange> page,
			FXRYImprisonmentChange entity) {
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
	public Page<FXRYImprisonmentChange> findPaged(Page<FXRYImprisonmentChange> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FXRYImprisonmentChange entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FXRYImprisonmentChange> getByFXRYId(String fxryId){
		return dao.findByProperty("fxryId", fxryId);
	}
	
	public void deleteByFXRYId(String fxryId){
		List<FXRYImprisonmentChange> entities = getByFXRYId(fxryId);
		if (entities != null){
			for (FXRYImprisonmentChange entity : entities){
				dao.delete(entity);
			}
		}
	}
}

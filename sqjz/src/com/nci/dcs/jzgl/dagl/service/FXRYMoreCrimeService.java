package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.FXRYMoreCrimeDao;
import com.nci.dcs.jzgl.dagl.model.FXRYMoreCrime;


@Service
@Transactional
public class FXRYMoreCrimeService extends BaseService<FXRYMoreCrime, String>{
	
	@Autowired
	private FXRYMoreCrimeDao dao;
	
	@Override
	public void create(FXRYMoreCrime entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(FXRYMoreCrime entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public FXRYMoreCrime get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<FXRYMoreCrime> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FXRYMoreCrime> findPaged(Page<FXRYMoreCrime> page,
			FXRYMoreCrime entity) {
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
	public Page<FXRYMoreCrime> findPaged(Page<FXRYMoreCrime> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FXRYMoreCrime entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FXRYMoreCrime> getByFXRYId(String fxryId){
		return dao.findByProperty("fxryId", fxryId);
	}
	
	public void deleteByFXRYId(String fxryId){
		List<FXRYMoreCrime> entities = getByFXRYId(fxryId);
		if (entities != null){
			for (FXRYMoreCrime entity : entities){
				dao.delete(entity);
			}
		}
	}
}

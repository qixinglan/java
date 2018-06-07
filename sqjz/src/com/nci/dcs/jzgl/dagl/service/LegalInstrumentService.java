package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.LegalInstrumentDao;
import com.nci.dcs.jzgl.dagl.model.LegalInstrument;


@Service
@Transactional
public class LegalInstrumentService extends BaseService<LegalInstrument, String>{
	
	@Autowired
	private LegalInstrumentDao dao;
	
	@Override
	public void create(LegalInstrument entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(LegalInstrument entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LegalInstrument get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<LegalInstrument> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<LegalInstrument> findPaged(Page<LegalInstrument> page,
			LegalInstrument entity) {
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
	public Page<LegalInstrument> findPaged(Page<LegalInstrument> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(LegalInstrument entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public LegalInstrument getByFXRYId(String fxryId){
		return dao.findOneByProperty("fxryId", fxryId);
	}
	
	public void deleteByFXRYId(String fxryId){
		LegalInstrument entity = getByFXRYId(fxryId);
		if (entity != null){
			dao.delete(entity);
		}
	}
}

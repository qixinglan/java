package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.FXRYSocialRelationsDao;
import com.nci.dcs.jzgl.dagl.model.FXRYResumeInfo;
import com.nci.dcs.jzgl.dagl.model.FXRYSocialRelations;


@Service
@Transactional
public class FXRYSocialRelationsService extends BaseService<FXRYSocialRelations, String>{
	
	@Autowired
	private FXRYSocialRelationsDao dao;
	
	@Override
	public void create(FXRYSocialRelations entity) {
		dao.save(entity);
	}

	@Override
	public void update(FXRYSocialRelations entity) {
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public FXRYSocialRelations get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<FXRYSocialRelations> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FXRYSocialRelations> findPaged(Page<FXRYSocialRelations> page,
			FXRYSocialRelations entity) {
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
	public Page<FXRYSocialRelations> findPaged(Page<FXRYSocialRelations> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public Page<FXRYSocialRelations> findPaged(Page<FXRYSocialRelations> page) {
		// TODO Auto-generated method stub
		return dao.findByCriteria(page);
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FXRYSocialRelations entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FXRYSocialRelations> getByFXRYId(String fxryId){
		return dao.findByProperty("fxryId", fxryId);
	}
	
	public void deleteByFXRYId(String fxryId){
		List<FXRYSocialRelations> entities = getByFXRYId(fxryId);
		if (entities != null){
			for (FXRYSocialRelations entity : entities){
				dao.delete(entity);
			}
		}
	}
}

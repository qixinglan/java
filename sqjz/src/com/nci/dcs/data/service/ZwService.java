package com.nci.dcs.data.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
//import com.nci.dcs.common.utils.PropertyFilter.MatchType;
import com.nci.dcs.data.dao.ZwDao;

import com.nci.dcs.data.model.Zw;


@Service
@Transactional
public class ZwService extends BaseService<Zw, String> {
	
	@Autowired
	private ZwDao zwDao;

	@Override
	public void audit(String dm) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(Zw entity) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		/*
		PropertyFilter filter = null;
		if(StringUtils.isNotEmpty(entity.getDm())){
			filter = new PropertyFilter();
			filter.setPropertyName("gsmc");
			filter.setMatchType(MatchType.EQUAL);
			filter.setValue(entity.getDm());
			filters.add(filter);
		}
		*/
		return filters;
	}

	@Override
	public void create(Zw entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String dm) {
		
		zwDao.delete(dm);
	}

	@Override
	public void disable(String dm) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enable(String dm) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Zw> find() {
		
		return zwDao.getAll();
	}

	@Override
	public Page<Zw> findPaged(Page<Zw> page, Zw entity) {
		
		return zwDao.findByFilters(page, this.buildPropertyFilter(entity));
	}

	@Override
	public Page<Zw> findPaged(Page<Zw> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return zwDao.find(page, hql);
	}

	@Override
	public Zw get(String dm) {
		
		return zwDao.get(dm);
	}

	@Override
	public void update(Zw entity) {
		
		zwDao.getSession().clear(); //zjc adds
		
		zwDao.save(entity);
	}
	

	
	

}

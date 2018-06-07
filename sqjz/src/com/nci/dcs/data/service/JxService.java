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
import com.nci.dcs.data.dao.JxDao;

import com.nci.dcs.data.model.Jx;


@Service
@Transactional
public class JxService extends BaseService<Jx, String> {
	
	@Autowired
	private JxDao jxDao;

	@Override
	public void audit(String dm) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(Jx entity) {
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
	public void create(Jx entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String dm) {
		
		jxDao.delete(dm);
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
	public List<Jx> find() {
		
		return jxDao.getAll();
	}

	@Override
	public Page<Jx> findPaged(Page<Jx> page, Jx entity) {
		
		return jxDao.findByFilters(page, this.buildPropertyFilter(entity));
	}

	@Override
	public Page<Jx> findPaged(Page<Jx> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return jxDao.find(page, hql);
	}

	@Override
	public Jx get(String dm) {
		
		return jxDao.get(dm);
	}

	@Override
	public void update(Jx entity) {
		
		jxDao.getSession().clear(); //zjc adds
		
		jxDao.save(entity);
	}
	

	
	

}

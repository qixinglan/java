package com.nci.dcs.em.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.em.dao.AlarmTypeManagerDao;
import com.nci.dcs.em.model.CcAlarmType;
import com.nci.dcs.em.model.CcSuperviseDevice;

//定位终端和定位主机管理
@Service
@Transactional
public class AlarmTypeManagerService extends BaseService<CcAlarmType, String>   {

	@Autowired
	AlarmTypeManagerDao dao;
	
	
	public AlarmTypeManagerDao getDao() {
		return dao;
	}

	public void setDao(AlarmTypeManagerDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void create(CcAlarmType entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CcAlarmType entity) {
		this.dao.save(entity);
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CcAlarmType get(String id) {
		return dao.get(id);
	}

	@Override
	public List<CcAlarmType> find() {
		List<CcAlarmType> lst = dao.find("from CcAlarmType", null);
		return lst;
	}

	public Page<CcAlarmType> findPaged(Page<CcAlarmType> page) {
		return dao.findByCriteria(page);
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
	public Page<CcAlarmType> findPaged(Page<CcAlarmType> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcAlarmType entity) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		return filters;
	}

	@Override
	public Page<CcAlarmType> findPaged(Page<CcAlarmType> page,
			CcAlarmType entity) {
		// TODO Auto-generated method stub
		return dao.findByFilters(page, this.buildPropertyFilter(entity));
	}

}

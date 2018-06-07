package com.nci.dcs.jzgl.dagl.service;

import java.util.Date;
import java.util.List;

import org.directwebremoting.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.em.service.SuperviseDeviceService;
import com.nci.dcs.jzgl.dagl.dao.FXRYAdjustTermRecordDao;
import com.nci.dcs.jzgl.dagl.model.CcAdjusttermRecord;


@Service
@Transactional
public class FXRYAdjustTermRecordService extends BaseService<CcAdjusttermRecord, String>{	
	@Autowired
	private FXRYAdjustTermRecordDao dao;
		
	@Override
	public void create(CcAdjusttermRecord entity){
		dao.save(entity);
	}
	@Override
	public void update(CcAdjusttermRecord entity) {
		dao.save(entity);
	}
	@Override
	public void delete(String id) {
		dao.delete(id);
	}
	@Override
	public CcAdjusttermRecord get(String id) {
        return null;
	}
	@Override
	public List<CcAdjusttermRecord> find() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page<CcAdjusttermRecord> findPaged(Page<CcAdjusttermRecord> page,
			CcAdjusttermRecord entity) {
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
	public Page<CcAdjusttermRecord> findPaged(Page<CcAdjusttermRecord> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<PropertyFilter> buildPropertyFilter(CcAdjusttermRecord entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
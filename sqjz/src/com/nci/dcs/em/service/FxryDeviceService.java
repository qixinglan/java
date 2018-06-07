package com.nci.dcs.em.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.em.dwjk.dao.FxryBasicinfoDao;
import com.nci.dcs.em.model.CcFxryDevice;

@Service
@Transactional
public class FxryDeviceService  extends BaseService<CcFxryDevice, String>{

	@Autowired
	private FxryBasicinfoDao fxrydao;
	
	@Override
	public void create(CcFxryDevice entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CcFxryDevice entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CcFxryDevice get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CcFxryDevice> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CcFxryDevice> findPaged(Page<CcFxryDevice> page,
			CcFxryDevice entity) {
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
	public Page<CcFxryDevice> findPaged(Page<CcFxryDevice> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcFxryDevice entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getCount(String hql,Object... values){
		return fxrydao.findInt(hql, values);
	}

}

package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.ViewFxryUncontrolHistoryDao;
import com.nci.dcs.jzgl.dagl.model.ViewFxryTransferInfo;
import com.nci.dcs.jzgl.dagl.model.ViewFxryUncontrolHistory;


@Service
@Transactional
public class ViewFxryUncontrolHistoryService extends BaseService<ViewFxryUncontrolHistory, String>{
	
	@Autowired
	private ViewFxryUncontrolHistoryDao dao;
	
	@Override
	public void create(ViewFxryUncontrolHistory entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(ViewFxryUncontrolHistory entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		
	}

	@Override
	public ViewFxryUncontrolHistory get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<ViewFxryUncontrolHistory> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ViewFxryUncontrolHistory> findPaged(Page<ViewFxryUncontrolHistory> page,
			ViewFxryUncontrolHistory entity) {
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
	public Page<ViewFxryUncontrolHistory> findPaged(Page<ViewFxryUncontrolHistory> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewFxryUncontrolHistory entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<ViewFxryUncontrolHistory> findPaged(Page<ViewFxryUncontrolHistory> page) {
		return dao.findByCriteria(page);
	}}

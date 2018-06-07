package com.nci.dcs.jzgl.dagl.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.jzgl.dagl.dao.FXRYDocExportDao;

@Service
@Transactional
public class FXRYDocExportService extends BaseService {
	@Autowired
	private FXRYDocExportDao dao;

	public List getByFxryId(String sql, Object... values) {
		return dao.find(sql, values);
	}

	@Override
	public void create(Object entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Object entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object get(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page findPaged(Page page, Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enable(Serializable id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void disable(Serializable id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void audit(Serializable id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Page findPaged(Page page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List buildPropertyFilter(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

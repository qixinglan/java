package com.nci.dcs.jzgl.rcbdgl.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.rcbdgl.dao.VacateDao;
import com.nci.dcs.jzgl.rcbdgl.model.CcVacateInfo;

;

@Service
@Transactional
public class VacateService extends BaseService<CcVacateInfo, String> {

	@Autowired
	VacateDao dao;

	public VacateDao getDao() {
		return dao;
	}

	public void setDao(VacateDao dao) {
		this.dao = dao;
	}

	@Override
	public void audit(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void create(CcVacateInfo entity) {
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);

	}

	@Override
	public void disable(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void enable(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CcVacateInfo> find() {
		return dao.find("from CcVacateInfo");
	}

	public List<CcVacateInfo> find(String hql, Object... values) {
		return dao.find(hql, values);
	}

	@Override
	public Page<CcVacateInfo> findPaged(Page<CcVacateInfo> page,
			CcVacateInfo entity) {
		return dao.findByFilters(page, this.buildPropertyFilter(entity));
	}

	@Override
	public Page<CcVacateInfo> findPaged(Page<CcVacateInfo> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CcVacateInfo get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public void update(CcVacateInfo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcVacateInfo entity) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		return filters;
	}

	public List findBySql(String sql, Object... value) {
		Query q = dao.createSQLQuery(sql, value);
		return q.list();
	}

	public Page<CcVacateInfo> findPaged(Page<CcVacateInfo> page) {
		return dao.findByCriteria(page);
	}

}

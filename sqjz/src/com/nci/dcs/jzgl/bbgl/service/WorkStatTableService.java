package com.nci.dcs.jzgl.bbgl.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.bbgl.dao.WorkStatTableDao;
import com.nci.dcs.jzgl.bbgl.model.CcWorkStatisticTable;


@Service
@Transactional
public class WorkStatTableService extends BaseService<CcWorkStatisticTable, String>{
	@Autowired
	private WorkStatTableDao dao;
	public WorkStatTableDao getDao() {
		return dao;
	}

	public void setDao(WorkStatTableDao dao) {
		this.dao = dao;
	}

	@Override
	public void create(CcWorkStatisticTable entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(CcWorkStatisticTable entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public CcWorkStatisticTable get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<CcWorkStatisticTable> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CcWorkStatisticTable> findPaged(
			Page<CcWorkStatisticTable> page, CcWorkStatisticTable entity) {
		// TODO Auto-generated method stub
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
	public Page<CcWorkStatisticTable> findPaged(
			Page<CcWorkStatisticTable> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcWorkStatisticTable entity) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 检查报表是否存在，如果存在返回true,否则返回false
	 * @param year
	 * @param month
	 * @param orgId
	 * @return
	 */
	public boolean checkTable(int year, int month, String orgId){
		List res = dao.findByCriteria(
				Restrictions.eq("year", year),
				Restrictions.eq("month", month),
				Restrictions.eq("orgid", orgId));
		if(res.size()>0){
			return true;
		}else{
			return false;
		}
	}

	public List getChildTable(String superOrg, Integer month, Integer year) {
		List res = dao.find("FROM CcWorkStatisticTable WHERE superOrg=? and month=? and year=? ", superOrg,month,year);
		return res;
	}
	
}

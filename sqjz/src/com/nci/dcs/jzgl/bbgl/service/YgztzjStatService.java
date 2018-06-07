package com.nci.dcs.jzgl.bbgl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.bbgl.dao.YgztzjStatDao;
import com.nci.dcs.jzgl.bbgl.model.CcZtzjStatisticMonth;


@Service
@Transactional
public class YgztzjStatService extends BaseService<CcZtzjStatisticMonth, String>{
	@Autowired
	private YgztzjStatDao dao;

	public YgztzjStatDao getDao() {
		return dao;
	}

	public void setDao(YgztzjStatDao dao) {
		this.dao = dao;
	}

	@Override
	public void create(CcZtzjStatisticMonth entity) {
		dao.save(entity);
	}
	

	@Override
	public void update(CcZtzjStatisticMonth entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	
	public CcZtzjStatisticMonth get(String orgid, Integer year, Integer month) {
		List<CcZtzjStatisticMonth> res = dao.find("from CcZtzjStatisticMonth where orgId=? and year=? and month=?", orgid,year,month);
		if(res.size()>0){
			return res.get(0);
		}else{
			return null;
		}
	}
	@Override
	public CcZtzjStatisticMonth get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<CcZtzjStatisticMonth> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CcZtzjStatisticMonth> findPaged(Page<CcZtzjStatisticMonth> page,
			CcZtzjStatisticMonth entity) {
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
	public Page<CcZtzjStatisticMonth> findPaged(Page<CcZtzjStatisticMonth> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcZtzjStatisticMonth entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getChildTable(String orgId, Integer month, Integer year) {
		List<CcZtzjStatisticMonth> res = dao.find("from CcZtzjStatisticMonth where supOrgId=? and year=? and month=?", orgId,year,month);
		if(res.size()>0){
			return res;
		}else{
			return null;
		}
	}

	public String checkTable(int year, int month, String bm) {
		List<CcZtzjStatisticMonth> res = dao.find("from CcZtzjStatisticMonth where orgId=? and year=? and month=?", bm,year,month);
		if(res.size()>0){
			return "报表已建立！";
		}else{
			return null;
		}
	}
	
	
}

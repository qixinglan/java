package com.nci.dcs.jzgl.bbgl.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.bbgl.dao.StatisticsFxryPerMonthDao;
import com.nci.dcs.jzgl.bbgl.model.CcStatisticsFxryPerMonth;


@Service
@Transactional
public class StatisticsFxryPerMonthService extends BaseService<CcStatisticsFxryPerMonth, String>{
	@Autowired
	private StatisticsFxryPerMonthDao dao;

	public StatisticsFxryPerMonthDao getDao() {
		return dao;
	}

	public void setDao(StatisticsFxryPerMonthDao dao) {
		this.dao = dao;
	}

	@Override
	public void create(CcStatisticsFxryPerMonth entity) {
		dao.save(entity);
	}
	

	@Override
	public void update(CcStatisticsFxryPerMonth entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	
	public CcStatisticsFxryPerMonth get(String orgid, Integer year, Integer month) {
		List<CcStatisticsFxryPerMonth> res = dao.find("from CcStatisticsFxryPerMonth where orgid=? and year=? and month=?", orgid,year,month);
		if(res.size()>0){
			return res.get(0);
		}else{
			return null;
		}
	}
	@Override
	public CcStatisticsFxryPerMonth get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<CcStatisticsFxryPerMonth> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CcStatisticsFxryPerMonth> findPaged(Page<CcStatisticsFxryPerMonth> page,
			CcStatisticsFxryPerMonth entity) {
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
	public Page<CcStatisticsFxryPerMonth> findPaged(Page<CcStatisticsFxryPerMonth> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcStatisticsFxryPerMonth entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

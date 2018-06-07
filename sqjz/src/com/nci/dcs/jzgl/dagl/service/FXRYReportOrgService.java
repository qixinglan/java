package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.FXRYReportOrgDao;
import com.nci.dcs.jzgl.dagl.model.FXRYReportOrg;
import com.nci.dcs.jzgl.dagl.model.LegalInstrument;


@Service
@Transactional
public class FXRYReportOrgService extends BaseService<FXRYReportOrg, String>{
	
	@Autowired
	private FXRYReportOrgDao dao;
	
	@Override
	public void create(FXRYReportOrg entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(FXRYReportOrg entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FXRYReportOrg get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<FXRYReportOrg> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FXRYReportOrg> findPaged(Page<FXRYReportOrg> page,
			FXRYReportOrg entity) {
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
	public Page<FXRYReportOrg> findPaged(Page<FXRYReportOrg> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FXRYReportOrg entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public FXRYReportOrg getByFXRYId(String fxryId){
		return dao.findOneByProperty("fxryId", fxryId);
	}
	
	public void updateByFxryId(FXRYReportOrg report){
		FXRYReportOrg old = getByFXRYId(report.getFxryId());
		if (old != null){
			report.setId(old.getId());
			update(report);
		}
		else{
			report.setId(null);
			create(report);
		}
	}
	
	public void deleteByFXRYId(String fxryId){
		FXRYReportOrg entity = getByFXRYId(fxryId);
		if (entity != null){
			dao.delete(entity);
		}
	}
}

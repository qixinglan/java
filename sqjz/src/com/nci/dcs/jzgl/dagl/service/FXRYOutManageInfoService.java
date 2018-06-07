package com.nci.dcs.jzgl.dagl.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.FXRYOutManageInfoDao;
import com.nci.dcs.jzgl.dagl.model.FXRYOutManageInfo;
import com.nci.dcs.jzgl.dagl.util.Constants.FXRYStateChangeType;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.jzgl.dagl.util.FXRYStateChangeException;


@Service
@Transactional
public class FXRYOutManageInfoService extends BaseService<FXRYOutManageInfo, String>{
	
	@Autowired
	private FXRYOutManageInfoDao dao;
	
	@Autowired
	FXRYBaseInfoService baseinfoService;
	
	@Override
	public void create(FXRYOutManageInfo entity){
		dao.save(entity);
	}

	@Override
	public void update(FXRYOutManageInfo entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public FXRYOutManageInfo get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<FXRYOutManageInfo> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FXRYOutManageInfo> findPaged(Page<FXRYOutManageInfo> page,
			FXRYOutManageInfo entity) {
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
	public Page<FXRYOutManageInfo> findPaged(Page<FXRYOutManageInfo> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FXRYOutManageInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public FXRYOutManageInfo getByFXRYId(String fxryId){
		Criterion owner = Restrictions.eq("fxryId", fxryId);
		Criterion rtime = Restrictions.isNull("endDate");
		return dao.findOneByCriteria(owner, rtime);
	}
	
	public void deleteByFXRYId(String fxryId){
		FXRYOutManageInfo entity = getByFXRYId(fxryId);
		if (entity != null){
			dao.delete(entity);
		}
	}
	
	public void outManage(FXRYOutManageInfo entity, String orgId) throws FXRYStateChangeException{
		baseinfoService.changeStatus(entity.getFxryId(), FXRYState.TG, orgId, FXRYStateChangeType.CHANGE_ORG);
		dao.save(entity);
	}
	
	public void inManage(FXRYOutManageInfo entity, String orgId) throws FXRYStateChangeException{
		baseinfoService.changeStatus(entity.getFxryId(), FXRYState.ZJ, orgId, FXRYStateChangeType.SAME_ORG);
		dao.save(entity);
	}
	public void inManage(FXRYOutManageInfo entity, String orgId ,String zfdc) throws FXRYStateChangeException{
		baseinfoService.changeStatus(entity.getFxryId(), FXRYState.ZJ, orgId, FXRYStateChangeType.SAME_ORG, zfdc);
		dao.save(entity);
	}
	
	public Page<FXRYOutManageInfo> findPaged(Page<FXRYOutManageInfo> page){
		return dao.findByCriteria(page);
	}
}

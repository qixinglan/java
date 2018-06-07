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
import com.nci.dcs.jzgl.dagl.dao.FXRYRemoveAdjustDao;
import com.nci.dcs.jzgl.dagl.model.FXRYBaseinfo;
import com.nci.dcs.jzgl.dagl.model.FXRYRemoveAdjust;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.jzgl.dagl.util.FXRYStateChangeException;
import com.nci.dcs.jzgl.dagl.util.Constants.FXRYStateChangeType;
import com.nci.dcs.system.model.User;


@Service
@Transactional
public class FXRYRemoveAdjustService extends BaseService<FXRYRemoveAdjust, String>{
	
	@Autowired
	private FXRYRemoveAdjustDao dao;
	
	@Autowired
	FXRYBaseInfoService baseinfoService;
	
	@Override
	public void create(FXRYRemoveAdjust entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(FXRYRemoveAdjust entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}
	
	@Override
	public FXRYRemoveAdjust get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<FXRYRemoveAdjust> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FXRYRemoveAdjust> findPaged(Page<FXRYRemoveAdjust> page,
			FXRYRemoveAdjust entity) {
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
	public Page<FXRYRemoveAdjust> findPaged(Page<FXRYRemoveAdjust> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FXRYRemoveAdjust entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public FXRYRemoveAdjust getByFXRYId(String fxryId){
		return dao.findOneByProperty("fxryId", fxryId);
	}
	
	public void deleteByFXRYId(String fxryId){
		FXRYRemoveAdjust entity = getByFXRYId(fxryId);
		if (entity != null){
			dao.delete(entity);
		}
	}
	
	public void removeAdjust(String fxryId, String orgId) throws FXRYStateChangeException{
		try{
			baseinfoService.unequip(fxryId);
		}
		catch (Exception e){
			throw new FXRYStateChangeException("解除设备绑定失败!请联系管理员");
		}
		baseinfoService.changeStatus(fxryId, FXRYState.JJ, orgId, FXRYStateChangeType.SAME_ORG);
	}
}

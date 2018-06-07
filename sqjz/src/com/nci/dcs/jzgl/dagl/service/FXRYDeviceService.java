package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.FXRYDeviceDao;
import com.nci.dcs.jzgl.dagl.model.FXRYDevice;
import com.nci.dcs.jzgl.dagl.model.LegalInstrument;
import com.nci.dcs.jzgl.dagl.util.Constants.DeviceStatus;


@Service
@Transactional
public class FXRYDeviceService extends BaseService<FXRYDevice, String>{
	
	@Autowired
	private FXRYDeviceDao dao;
	
	@Override
	public void create(FXRYDevice entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(FXRYDevice entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public FXRYDevice get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<FXRYDevice> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FXRYDevice> findPaged(Page<FXRYDevice> page,
			FXRYDevice entity) {
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
	public Page<FXRYDevice> findPaged(Page<FXRYDevice> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FXRYDevice entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public FXRYDevice getByFXRYId(String fxryId){
		Criterion user = Restrictions.eq("fxryId", fxryId);
		Criterion using = Restrictions.eq("status", DeviceStatus.USING);
		return dao.findOneByCriteria(user, using);
	}
	
	public void deleteByFXRYId(String fxryId){
		FXRYDevice entity = getByFXRYId(fxryId);
		if (entity != null){
			dao.delete(entity);
		}
	}

	/**
	 * Description:查询该人员是否曾经佩戴过设备
	 * @author Shuzz
	 * @since 2015年11月19日上午11:08:08
	 * @param fxryId
	 * @return
	 */
	public List<FXRYDevice> getAllByFxryId(String fxryId) {
		Criterion user = Restrictions.eq("fxryId", fxryId);
		return dao.findByCriteria(user);
	}
}

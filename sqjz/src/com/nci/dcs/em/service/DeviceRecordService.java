package com.nci.dcs.em.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.em.dao.DeviceRecordDao;
import com.nci.dcs.em.model.CcDeviceRecord;
import com.nci.dcs.em.model.ViewDevicePair;
@Service
@Transactional
public class DeviceRecordService extends BaseService<CcDeviceRecord, String>{
	@Autowired
	private DeviceRecordDao deviceRecordDao;
	//新增一条记录
	public void add(CcDeviceRecord deviceRecord){
		deviceRecordDao.save(deviceRecord);
	}
	//分页查询
	public Page<CcDeviceRecord> searchHistory(Page<CcDeviceRecord> page) {

		return deviceRecordDao.findByCriteria(page);
	}
	//查询所有记录根据deviceNumber按照时间顺序排序
	public List<CcDeviceRecord> findAllByDeviceNumber(String deviceNumber){
		return deviceRecordDao.find("from CcDeviceRecord where deviceNumber=? order by operateDate",deviceNumber);
	}
	@Override
	public void create(CcDeviceRecord entity) {
		// TODO Auto-generated method stub
	}
	@Override
	public void update(CcDeviceRecord entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CcDeviceRecord get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CcDeviceRecord> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CcDeviceRecord> findPaged(Page<CcDeviceRecord> page,
			CcDeviceRecord entity) {
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
	public Page<CcDeviceRecord> findPaged(Page<CcDeviceRecord> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcDeviceRecord entity) {
		// TODO Auto-generated method stub
		return null;
	}
	public DeviceRecordDao getDeviceRecordDao() {
		return deviceRecordDao;
	}

	public void setDeviceRecordDao(DeviceRecordDao deviceRecordDao) {
		this.deviceRecordDao = deviceRecordDao;
	}
}

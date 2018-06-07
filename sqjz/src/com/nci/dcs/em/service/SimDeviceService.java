package com.nci.dcs.em.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.Constants;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.utils.PropertyFilter.MatchType;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.em.dao.SimDeviceDao;
import com.nci.dcs.em.model.CcSimDevice;
@Service
@Transactional
public class SimDeviceService extends BaseService<CcSimDevice, String>{
	@Autowired
	private SimDeviceDao simDeviceDao;
	//状态统计
	public List findTotalNumbersByStatus(String curOrgId,String rootOrgId,String useUnit){
		Query query;
		if(curOrgId.equals(rootOrgId)){
			if(useUnit!=null&&useUnit!=""){
				query=simDeviceDao.createQuery("select count(*) as num,status as status from CcSimDevice where useUnit= ? group by status",useUnit);
			}else{
				query=simDeviceDao.createQuery("select count(*) as num,status as status from CcSimDevice group by status");
			}
		}else{
			query=simDeviceDao.createQuery("select count(*) as num,status as status from CcSimDevice where useUnit= ? group by status ",curOrgId);
		}
		List list=query.list();
		return list;
	}
	//各司法所在用数量统计
	public List findTotalNumbersBySfs(String curOrgId){
		Query query=simDeviceDao.createQuery
				("select count(*)as num,useSfsUnit as name ,useSfsUnit as useUnit from CcSimDevice where useUnit=? and useSfsUnit is not null group by useSfsUnit",curOrgId);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> list=query.list();
		return list;
	}
	//各司法局在用数量统计
	public List findTotalNumbersBySfj(){
		Query query=simDeviceDao.createQuery("select count(*)as num,useUnit as name,useUnit as useUnit from CcSimDevice where useUnit is not null group by useUnit");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> list=query.list();
		return list;
	}
	//类型统计
	public List findTotalNumbersByType(String curOrgId,String rootOrgId,String useUnit){
		Query query;
		if(curOrgId.equals(rootOrgId)){
			if(useUnit!=null&&useUnit!=""){
				query=simDeviceDao.createQuery("select count(*) as num,useDeviceType as type,"+useUnit+"as useUnit from CcSimDevice where useUnit= ? and useDeviceType is not null group by useDeviceType",useUnit);
			}else{
				query=simDeviceDao.createQuery("select count(*) as num,useDeviceType as type from CcSimDevice where useDeviceType is not null group by useDeviceType");
			}
		}else{
			query=simDeviceDao.createQuery("select count(*) as num,useDeviceType as type,"+curOrgId+"as useUnit from CcSimDevice where useUnit= ? and useDeviceType is not null group by useDeviceType",curOrgId);
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> list=query.list();
		return list;
	}
	//根据使用单位和sim卡使用类型找到未用的sim卡
	public List<CcSimDevice> findNoUsedByUseUnitAndUseType(String useUnit,String useDeviceType){
		final List<PropertyFilter> filters=new ArrayList();
		filters.add(new PropertyFilter("status",Constants.DEVICE_NOTUSEDSTATUS,MatchType.EQUAL));
		filters.add(new PropertyFilter("useUnit",useUnit,MatchType.EQUAL));
		filters.add(new PropertyFilter("useDeviceType",useDeviceType,MatchType.EQUAL));
		return simDeviceDao.findByFilters(filters);
	}
	//找到所有未用的Sim卡
	public List<CcSimDevice> findAllNoUsed(){
		return simDeviceDao.findByProperty("status",Constants.DEVICE_NOTUSEDSTATUS);
	}
	//根据卡号找到sim卡
	public CcSimDevice findSimDeviceByPhoneNumber(String phoneNumber){
		return simDeviceDao.findByProperty("phoneNumber",phoneNumber).get(0);
	}
	//根据deviceNumber找到sim卡
	public CcSimDevice findSimDeviceBydeviceNumber(String deviceNumber){
		return simDeviceDao.findByProperty("deviceNumber",deviceNumber).get(0);
	}
	// 按条件分页查询
		public Page<CcSimDevice> findDzzjgsbPaged(
				Page<CcSimDevice> page, List<SearchRule> searchRules) {
			return simDeviceDao.findBySeachRule(page, searchRules);
		}
	//插入一条数据
	public void add(CcSimDevice CcSimDevice){
		simDeviceDao.save(CcSimDevice);
	}
	public List<CcSimDevice> findPaged(
			final Page<CcSimDevice> page, List<SearchRule> searchRules,
			List<String> columnNames) {
		return simDeviceDao.findBySeachRule(page, searchRules,
				columnNames).getResult();
	}
	//检查sim卡单位，格式，状态,类型
	public boolean checkSim(String phoneNumber,String useUnitId,String simType){
		if(phoneNumber.length()==11){
			CcSimDevice ccSimDevice=simDeviceDao.findOneByProperty("phoneNumber",phoneNumber);
			if(useUnitId.equals(ccSimDevice.getUseUnit())&&ccSimDevice.getStatus().equals(Constants.DEVICE_NOTUSEDSTATUS)&&ccSimDevice.getUseDeviceType().equals(simType)){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	@Override
	public void create(CcSimDevice entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CcSimDevice entity) {
		// TODO Auto-generated method stub
		simDeviceDao.save(entity);
	}
	public void delete(String[] deviceNumbers) {
		for(String d:deviceNumbers){
			simDeviceDao.delete(d);
		}
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
	}

	@Override
	public CcSimDevice get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CcSimDevice> find() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Page<CcSimDevice> findPaged(Page<CcSimDevice> page,
			CcSimDevice entity) {
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
	public Page<CcSimDevice> findPaged(Page<CcSimDevice> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcSimDevice entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public SimDeviceDao getSimDeviceDao() {
		return simDeviceDao;
	}

	public void setSimDeviceDao(SimDeviceDao simDeviceDao) {
		this.simDeviceDao = simDeviceDao;
	}

}

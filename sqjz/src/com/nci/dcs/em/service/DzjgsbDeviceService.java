package com.nci.dcs.em.service;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.Constants;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.em.dao.DzjgsbDeviceDao;
import com.nci.dcs.em.model.CcDzjgsbDevice;
@Service
@Transactional
public class DzjgsbDeviceService extends BaseService<CcDzjgsbDevice, String>{
	@Autowired
	private DzjgsbDeviceDao dzjgsbDeviceDao;
	//状态统计
	public List findTotalNumbersByStatus(String curOrgId,String rootOrgId,String useUnit){
		Query query;
		if(curOrgId.equals(rootOrgId)){
			if(useUnit!=null&&useUnit!=""){
				query=dzjgsbDeviceDao.createQuery("select count(*) as num,status as status from CcDzjgsbDevice where useUnit= ? group by status",useUnit);
			}else{
				query=dzjgsbDeviceDao.createQuery("select count(*) as num,status as status from CcDzjgsbDevice group by status");
			}
		}else{
			query=dzjgsbDeviceDao.createQuery("select count(*) as num,status as status from CcDzjgsbDevice where useUnit= ? group by status ",curOrgId);
		}
		List list=query.list();
		return list;
	}
	//各司法所在用数量统计
	public List findTotalNumbersBySfs(String curOrgId){
		Query query=dzjgsbDeviceDao.createQuery
				("select count(*)as num,useSfsUnit as name ,useSfsUnit as useUnit from CcDzjgsbDevice where useUnit=? and useSfsUnit is not null group by useSfsUnit",curOrgId);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> list=query.list();
		return list;
	}
	//各司法局在用数量统计
	public List findTotalNumbersBySfj(){
		Query query=dzjgsbDeviceDao.createQuery("select count(*)as num,useUnit as name,useUnit as useUnit from CcDzjgsbDevice where useUnit is not null group by useUnit");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> list=query.list();
		return list;
	}
	// 按条件分页查询
	public Page<CcDzjgsbDevice> findDzzjgsbPaged(
			Page<CcDzjgsbDevice> page, List<SearchRule> searchRules) {
		return dzjgsbDeviceDao.findBySeachRule(page, searchRules);
	}
	//插入一条数据
	public void add(CcDzjgsbDevice ccDzjgsbDevice){
		if(ccDzjgsbDevice!=null){
			dzjgsbDeviceDao.save(ccDzjgsbDevice);
		}
	}
	//检查设备编号是否可用
	public boolean checkByDeviceNumber(String deviceNumber){
		if(dzjgsbDeviceDao.findByProperty("deviceNumber",deviceNumber).size()==0){
			return true;
		}
		return false;
	}
	//根据ID查询
	public CcDzjgsbDevice findById(String id){
		return dzjgsbDeviceDao.get(id);
	}
	//根据设备编号查询
	public CcDzjgsbDevice findByDeviceNumber(String deviceNumber){
		return dzjgsbDeviceDao.findUniqueByProperty("deviceNumber",deviceNumber);
	}
	public List<CcDzjgsbDevice> findPaged(
			final Page<CcDzjgsbDevice> page, List<SearchRule> searchRules,
			List<String> columnNames) {
		return dzjgsbDeviceDao.findBySeachRule(page, searchRules,
				columnNames).getResult();
	}
	/**
	 * 查询未装备、可用的电子监管设备
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryEquipableMachine(String orgId) {
		List<Map<String, Object>> list= dzjgsbDeviceDao
				.createSQLQuery(
						"select  d.device_Number as \"deviceNumber\",  d.status as \"status\" from CC_DZJGSB_DEVICE d where  d.status="+Constants.DEVICE_NOTUSEDSTATUS+"  and d.use_unit=? for update skip locked"
						,orgId)
				.setResultTransformer(
						CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}
	
	@Override
	public void create(CcDzjgsbDevice entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(CcDzjgsbDevice entity) {
		// TODO Auto-generated method stub
		
	}
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for(String i:ids){
			dzjgsbDeviceDao.delete(i);
		}
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		dzjgsbDeviceDao.delete(id);
	}
	@Override
	public CcDzjgsbDevice get(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<CcDzjgsbDevice> find() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page<CcDzjgsbDevice> findPaged(Page<CcDzjgsbDevice> page,
			CcDzjgsbDevice entity) {
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
	public Page<CcDzjgsbDevice> findPaged(Page<CcDzjgsbDevice> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<PropertyFilter> buildPropertyFilter(CcDzjgsbDevice entity) {
		// TODO Auto-generated method stub
		return null;
	}
	public DzjgsbDeviceDao getDzjgsbDeviceDao() {
		return dzjgsbDeviceDao;
	}
	public void setDzjgsbDeviceDao(DzjgsbDeviceDao dzjgsbDeviceDao) {
		this.dzjgsbDeviceDao = dzjgsbDeviceDao;
	}
	
	
}

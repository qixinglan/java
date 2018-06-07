package com.nci.dcs.em.service;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.em.dao.YwLawDeviceDao;
import com.nci.dcs.em.model.CcDzjgsbDevice;
import com.nci.dcs.em.model.CcLawEnforcement;
import com.nci.dcs.em.model.CcYwLawDevice;
@Service
@Transactional
public class YwLawDeviceService extends BaseService<CcYwLawDevice, String>{
	@Autowired
	private YwLawDeviceDao ywDeviceDao;
	//状态统计
	public List findTotalNumbersByStatus(String curOrgId,String rootOrgId,String useUnit){
		Query query;
		if(curOrgId.equals(rootOrgId)){
			if(useUnit!=null&&useUnit!=""){
				query=ywDeviceDao.createQuery("select count(*) as num,status as status from CcYwLawDevice where useUnit= ? group by status",useUnit);
			}else{
				query=ywDeviceDao.createQuery("select count(*) as num,status as status from CcYwLawDevice group by status");
			}
		}else{
			query=ywDeviceDao.createQuery("select count(*) as num,status as status from CcYwLawDevice where useUnit= ? group by status ",curOrgId);
		}
		List list=query.list();
		return list;
	}
	//各司法所在用数量统计
	public List findTotalNumbersBySfs(String curOrgId){
		Query query=ywDeviceDao.createQuery
				("select count(*)as num,useSfsUnit as name,useSfsUnit as useUnit from CcYwLawDevice where useUnit=? and useSfsUnit is not null group by useSfsUnit",curOrgId);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> list=query.list();
		return list;
	}
	//各司法局在用数量统计
	public List findTotalNumbersBySfj(){
			Query query=ywDeviceDao.createQuery("select count(*)as num,useUnit as name,useUnit as useUnit from CcYwLawDevice where useUnit is not null group by useUnit");
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<Map<String,Object>> list=query.list();
			return list;
	}
	//分页查询
	public Page<CcYwLawDevice> findPaged(Page<CcYwLawDevice> page) {
		return ywDeviceDao.findByCriteria(page);
	}
	//根据id查询
	public CcYwLawDevice findById(String id){
		return ywDeviceDao.get(id);
	}
	// 按条件分页查询
		public Page<CcYwLawDevice> findDzzjgsbPaged(
				Page<CcYwLawDevice> page, List<SearchRule> searchRules) {
			return ywDeviceDao.findBySeachRule(page, searchRules);
		}
	public List<CcYwLawDevice> findPaged(
			final Page<CcYwLawDevice> page, List<SearchRule> searchRules,
			List<String> columnNames) {
		return ywDeviceDao.findBySeachRule(page, searchRules,
				columnNames).getResult();
	}
	@Override
	public void create(CcYwLawDevice entity) {
		// TODO Auto-generated method stub
		ywDeviceDao.save(entity);
	}

	@Override
	public void update(CcYwLawDevice entity) {
		// TODO Auto-generated method stub
	}
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for(String i:ids){
			ywDeviceDao.delete(i);
		}
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CcYwLawDevice get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CcYwLawDevice> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CcYwLawDevice> findPaged(Page<CcYwLawDevice> page,
			CcYwLawDevice entity) {
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
	public Page<CcYwLawDevice> findPaged(Page<CcYwLawDevice> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcYwLawDevice entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public YwLawDeviceDao getYwDeviceDao() {
		return ywDeviceDao;
	}

	public void setYwDeviceDao(YwLawDeviceDao ywDeviceDao) {
		this.ywDeviceDao = ywDeviceDao;
	}

}

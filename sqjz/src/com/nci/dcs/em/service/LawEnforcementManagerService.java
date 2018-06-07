package com.nci.dcs.em.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.utils.PropertyFilter.MatchType;
import com.nci.dcs.em.dao.LawEnforcementManagerDao;
import com.nci.dcs.em.model.CcLawEnforcement;

@Service
@Transactional
public class LawEnforcementManagerService extends BaseService<CcLawEnforcement, String>{

	@Autowired
	LawEnforcementManagerDao dao;
	
	public LawEnforcementManagerDao getDao() {
		return dao;
	}

	public void setDao(LawEnforcementManagerDao dao) {
		this.dao = dao;
	}

	@Override
	public void audit(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public List<PropertyFilter> buildPropertyFilter(CcLawEnforcement entity1,CcLawEnforcement entity2) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		PropertyFilter filter = null;
		if(StringUtils.isNotEmpty(entity1.getEqunumber())){
			filter = new PropertyFilter();
			filter.setPropertyName("equnumber");
			filter.setMatchType(MatchType.LIKE);
			filter.setValue(entity1.getEqunumber());
			filters.add(filter);
		}
		if(StringUtils.isNotEmpty(entity1.getEqustatus())){
			filter = new PropertyFilter();
			filter.setPropertyName("equstatus");
			filter.setMatchType(MatchType.EQUAL);
			filter.setValue(entity1.getEqustatus());
			filters.add(filter);
		}
		if(StringUtils.isNotEmpty(entity1.getEqutype())){
			filter = new PropertyFilter();
			filter.setPropertyName("equtype");
			filter.setMatchType(MatchType.LIKE);
			filter.setValue(entity1.getEqutype());
			filters.add(filter);
		}
		if(StringUtils.isNotEmpty(entity1.getMOrgId())){
			filter = new PropertyFilter();
			filter.setPropertyName("MOrgId");
			filter.setMatchType(MatchType.LIKE);
			filter.setValue(entity1.getMOrgId());
			filters.add(filter);
		}
		if(StringUtils.isNotEmpty(entity1.getOrgId())){
			filter = new PropertyFilter();
			filter.setPropertyName("orgId");
			filter.setMatchType(MatchType.LIKE);
			filter.setValue(entity1.getOrgId());
			filters.add(filter);
		}
		if(entity1.getProcureDate()!=null){
			filter = new PropertyFilter();
			filter.setPropertyName("procureDate");
			filter.setMatchType(MatchType.BETWEEN);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-mm HH:mm:ss");
			filter.setValue(sdf.format(entity1.getProcureDate())+"|"+sdf.format(entity2.getProcureDate()));
			filters.add(filter);
		}
		if(entity1.getUseTime()!=null){
			filter = new PropertyFilter();
			filter.setPropertyName("useTime");
			filter.setMatchType(MatchType.BETWEEN);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-mm HH:mm:ss");
			filter.setValue(sdf.format(entity1.getUseTime())+"|"+sdf.format(entity2.getUseTime()));
			filters.add(filter);
		}
		return filters;
	}

	@Override
	public void create(CcLawEnforcement entity) {
		if(entity.getId()==null)
			entity.setId(CommonUtils.uuid());
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
		
	}

	@Override
	public void disable(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enable(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CcLawEnforcement> find() {
		// TODO Auto-generated method stub
		return dao.find("from CcLawEnforcement", null);
	}
	public List <CcLawEnforcement> find(String col,String where){
		return dao.find("select "+col+" from CcLawEnforcement ");
	}

	@Override
	public Page<CcLawEnforcement> findPaged(Page<CcLawEnforcement> page, CcLawEnforcement entity) {
			return dao.findByFilters(page, this.buildPropertyFilter(entity));
	}

	public Page<CcLawEnforcement> findPaged(Page<CcLawEnforcement> page) {
		return dao.findByCriteria(page);
	}
	
	@Override
	public Page<CcLawEnforcement> findPaged(Page<CcLawEnforcement> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CcLawEnforcement get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public void update(CcLawEnforcement entity) {
		// TODO Auto-generated method stub
		
	}
	public List findByfilter(CcLawEnforcement entity1,CcLawEnforcement entity2){
		return dao.findByFilters(this.buildPropertyFilter(entity1,entity2));
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcLawEnforcement entity) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		return filters;
	}
	
	public List findBySql(String sql,Object... value){
		Query q = dao.createSQLQuery(sql,value);
		return q.list();
	}
	
}

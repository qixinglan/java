package com.nci.dcs.em.dwjk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.em.dwjk.dao.FxryBasicinfoDao;
import com.nci.dcs.em.dwjk.model.CcFxryBaseinfo;

@Service
@Transactional
public class FxryBasicinfoService  extends BaseService<CcFxryBaseinfo, String>{

	@Autowired
	private FxryBasicinfoDao fxrydao;
	
	@Override
	public void create(CcFxryBaseinfo entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CcFxryBaseinfo entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CcFxryBaseinfo get(String id) {
		
		return fxrydao.get(id);
	}

	@Override
	public List<CcFxryBaseinfo> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CcFxryBaseinfo> findPaged(Page<CcFxryBaseinfo> page,
			CcFxryBaseinfo entity) {
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
	public Page<CcFxryBaseinfo> findPaged(Page<CcFxryBaseinfo> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcFxryBaseinfo entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getCount(String hql,Object... values){
		return fxrydao.findInt(hql, values);
	}
	
	public List getPersons(String orgId,List<String> childIds){
		StringBuffer orgIds = new StringBuffer();
		orgIds.append("'").append(orgId);
		if(childIds!=null){
			for(String id:childIds){
				orgIds.append("','").append(id);
			}
		}
		orgIds.append("'");
		String sql = "select r.id,r.PHONE_NUMBER,r.NAME,r.response_org as ORG_ID,a.ac,gis_x,gis_y "
				+ "from  CC_FXRY_BASEINFO r  "
				+ "Left Join (Select Count(1) ac,fxry_ID From CC_ALARM_INFO Where status=2 Group By fxry_id) a On a.fxry_ID = r.id "
				+ "where r.response_org in("+orgIds.toString()+")";
		return fxrydao.createSQLQuery(sql).list();
	}
}

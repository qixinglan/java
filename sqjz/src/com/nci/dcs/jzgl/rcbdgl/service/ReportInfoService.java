package com.nci.dcs.jzgl.rcbdgl.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.utils.PropertyFilter.MatchType;
import com.nci.dcs.em.model.CcLawEnforcement;
import com.nci.dcs.jzgl.rcbdgl.model.ReportInfo;
import com.nci.dcs.jzgl.rcbdgl.dao.ReportInfoDao;;


@Service
@Transactional
public class ReportInfoService extends
BaseService<ReportInfo, String> {

@Autowired
ReportInfoDao dao;

public ReportInfoDao getDao() {
return dao;
}

public void setDao(ReportInfoDao dao) {
this.dao = dao;
}

@Override
public void audit(String id) throws Exception {
// TODO Auto-generated method stub

}

@Override
public void create(ReportInfo entity) {
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

public ReportInfo getByFxryIdAndDataStatus(String fxryid) throws Exception{	
	List<PropertyFilter> filters=new ArrayList<PropertyFilter>();

	PropertyFilter p1=new PropertyFilter();
	p1.setMatchType(MatchType.EQUAL);
	p1.setPropertyName("dataStatus");
	p1.setValue(1);	
	filters.add(p1);
	
	PropertyFilter p2=new PropertyFilter();
	p2.setMatchType(MatchType.EQUAL);
	p2.setPropertyName("fxryId");
	p2.setValue(fxryid);	
	filters.add(p2);
   
	List<ReportInfo> lst=dao.findByFilters(filters);
	if(lst!=null && lst.size()>0)
	{
	    return (ReportInfo)lst.get(0);
	}
	else
	{
		return null;
	}
}

@Override
public void enable(String id) throws Exception {
// TODO Auto-generated method stub

}

@Override
public List<ReportInfo> find() {
// TODO Auto-generated method stub
return dao.find("from ReportInfo", null);
}

public List<ReportInfo> find(String col, String where) {
return dao.find("select " + col + " from ReportInfo ");
}

@Override
public Page<ReportInfo> findPaged(Page<ReportInfo> page,
		ReportInfo entity) {
return dao.findByFilters(page, this.buildPropertyFilter(entity));
}

public Page<ReportInfo> findPaged(Page<ReportInfo> page) {
	return dao.findByCriteria(page);
}

@Override
public Page<ReportInfo> findPaged(Page<ReportInfo> page,
	String hql, Object... values) {
// TODO Auto-generated method stub
return null;
}

@Override
public ReportInfo get(String id) {
// TODO Auto-generated method stub
return dao.get(id);
}

@Override
public void update(ReportInfo entity) {
// TODO Auto-generated method stub

}

@Override
public List<PropertyFilter> buildPropertyFilter(ReportInfo entity) {
List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
return filters;
}

public List findBySql(String sql, Object... value) {
Query q = dao.createSQLQuery(sql, value);
return q.list();
}

}


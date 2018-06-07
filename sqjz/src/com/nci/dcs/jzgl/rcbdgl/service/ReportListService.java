package com.nci.dcs.jzgl.rcbdgl.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.jzgl.rcbdgl.dao.ReportListDao;
import com.nci.dcs.jzgl.rcbdgl.model.ViewFxryReportinfo;


@Service
@Transactional
public class ReportListService extends
BaseService<ViewFxryReportinfo, String> {

@Autowired
ReportListDao dao;

public ReportListDao getDao() {
return dao;
}

public void setDao(ReportListDao dao) {
this.dao = dao;
}

@Override
public void audit(String id) throws Exception {
// TODO Auto-generated method stub

}

@Override
public void create(ViewFxryReportinfo entity) {
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
public List<ViewFxryReportinfo> find() {
// TODO Auto-generated method stub
return dao.find("from ViewFxryReportinfo", null);
}

public List<ViewFxryReportinfo> find(String col, String where) {
return dao.find("select " + col + " from ViewFxryReportinfo ");
}

@Override
public Page<ViewFxryReportinfo> findPaged(Page<ViewFxryReportinfo> page,
		ViewFxryReportinfo entity) {
return dao.findByFilters(page, this.buildPropertyFilter(entity));
}

public Page<ViewFxryReportinfo> findPaged(Page<ViewFxryReportinfo> page) {
	return dao.findByCriteria(page);
}

@Override
public Page<ViewFxryReportinfo> findPaged(Page<ViewFxryReportinfo> page,
	String hql, Object... values) {
// TODO Auto-generated method stub
return null;
}

@Override
public ViewFxryReportinfo get(String id) {
// TODO Auto-generated method stub
return dao.get(id);
}

@Override
public void update(ViewFxryReportinfo entity) {
// TODO Auto-generated method stub

}

@Override
public List<PropertyFilter> buildPropertyFilter(ViewFxryReportinfo entity) {
List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
return filters;
}

public List findBySql(String sql, Object... value) {
Query q = dao.createSQLQuery(sql, value);
return q.list();
}

public Page<ViewFxryReportinfo> findPaged(Page<ViewFxryReportinfo> page, List<SearchRule> rules) {
	return dao.findBySeachRule(page,rules);
}

}


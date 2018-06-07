package com.nci.dcs.em.zhcx.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.em.zhcx.dao.ZhcxFxryMessageDao;
import com.nci.dcs.em.zhcx.model.ViewCcFxryBaseinfo;

@Service
@Transactional
public class ZhcxFxryMessageService extends
		BaseService<ViewCcFxryBaseinfo, String> {

	@Autowired
	ZhcxFxryMessageDao dao;

	public ZhcxFxryMessageDao getDao() {
		return this.dao;
	}

	public void setDao(ZhcxFxryMessageDao dao) {
		this.dao = dao;
	}

	@Override
	public void create(ViewCcFxryBaseinfo entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ViewCcFxryBaseinfo entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ViewCcFxryBaseinfo get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<ViewCcFxryBaseinfo> find() {
		// TODO Auto-generated method stub
		return dao.find("from ViewCcFxryBaseinfo", null);
	}
	
	@Override
	public Page<ViewCcFxryBaseinfo> findPaged(Page<ViewCcFxryBaseinfo> page,
			ViewCcFxryBaseinfo entity) {
		// TODO Auto-generated method stub
		return dao.findByFilters(page, this.buildPropertyFilter(entity));
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
	public Page<ViewCcFxryBaseinfo> findPaged(Page<ViewCcFxryBaseinfo> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewCcFxryBaseinfo entity) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		return filters;
	}
	
	public List<ViewCcFxryBaseinfo> find(String col){
		return dao.find("select "+col+" from ViewCcFxryBaseinfo ");
	}

	public Page<ViewCcFxryBaseinfo> findPaged(Page<ViewCcFxryBaseinfo> page) {
		return dao.findByCriteria(page);
	}
	public List<ViewCcFxryBaseinfo> findPaged(final Page<ViewCcFxryBaseinfo> page, List<SearchRule> searchRules,List<String> columnNames) {
		return dao.findBySeachRule(page,searchRules,columnNames).getResult();
	}
}

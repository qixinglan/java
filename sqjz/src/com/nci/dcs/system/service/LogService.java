package com.nci.dcs.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.utils.PropertyFilter.MatchType;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.system.dao.LogDao;
import com.nci.dcs.system.model.Log;

@Service
@Transactional
public class LogService extends BaseService<Log, Long> {

	@Autowired
	private LogDao logDao;

	@Override
	public void audit(Long id) throws Exception {
		// TODO Auto-generated method stub

	}

	public Page<Log> findPersonPaged(Page<Log> page,
			List<SearchRule> searchRules) {
		return logDao.findBySeachRule(page, searchRules);
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(Log entity) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		if (entity != null) {
			PropertyFilter f = new PropertyFilter();
			f.setMatchType(MatchType.EQUAL);
			f.setPropertyName("rzlx");
			f.setValue(entity.getRzlx());
			filters.add(f);
		}
		return filters;
	}

	@Override
	public void create(Log entity) {
		logDao.save(entity);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void disable(Long id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void enable(Long id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Log> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Log> findPaged(Page<Log> page, Log entity) {
		logDao.findByFilters(page, this.buildPropertyFilter(entity));
		return null;
	}

	@Override
	public Page<Log> findPaged(Page<Log> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Log entity) {
		// TODO Auto-generated method stub

	}

}

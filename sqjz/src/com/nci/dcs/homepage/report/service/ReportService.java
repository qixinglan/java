package com.nci.dcs.homepage.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.homepage.report.dao.ReportModuleDao;
import com.nci.dcs.homepage.report.model.ReportModule;

@Service
public class ReportService extends BaseService<ReportModule, String> {

	@Autowired
	private ReportModuleDao reportModuleDao;

	/**
	 * 获取用户可见的所有快捷操作
	 * 
	 * @return
	 */
	public List<ReportModule> getAllModules() {
		return reportModuleDao.getAll();
	}

	@Override
	public void create(ReportModule entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ReportModule entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ReportModule get(String id) {
		return reportModuleDao.get(id);
	}

	@Override
	public List<ReportModule> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ReportModule> findPaged(Page<ReportModule> page,
			ReportModule entity) {
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
	public Page<ReportModule> findPaged(Page<ReportModule> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ReportModule entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

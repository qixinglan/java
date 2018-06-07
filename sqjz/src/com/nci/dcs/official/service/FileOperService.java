package com.nci.dcs.official.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.official.dao.FileOperDao;
import com.nci.dcs.official.model.Dataupload;

@Service
@Transactional
public class FileOperService extends
		BaseService<Dataupload, String> {
	
	@Autowired
	private FileOperDao dao ;

	public FileOperDao getDao() {
		return dao;
	}

	public void setDao(FileOperDao dao) {
		this.dao = dao;
	}

	@Override
	public void create(Dataupload entity) {
		dao.save(entity);
		
	}

	@Override
	public void update(Dataupload entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public Dataupload get(String id) {
		
		return dao.get(id);
	}

	@Override
	public List<Dataupload> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Dataupload> findPaged(Page<Dataupload> page, Dataupload entity) {
		return dao.findByCriteria(page);
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
	public Page<Dataupload> findPaged(Page<Dataupload> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(Dataupload entity) {
		// TODO Auto-generated method stub
		return null;
	}
	public Page<Dataupload> findPaged(Page<Dataupload> page, List<SearchRule> rules) {
		return dao.findBySeachRule(page,rules);
	}
	public Page<Dataupload> findPaged(
			Page<Dataupload> page) {
		return dao.findByCriteria(page);
	}
}

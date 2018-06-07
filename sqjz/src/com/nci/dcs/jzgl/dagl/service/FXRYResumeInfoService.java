package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.FXRYResumeInfoDao;
import com.nci.dcs.jzgl.dagl.model.FXRYResumeInfo;
import com.nci.dcs.jzgl.dagl.model.ViewFXRYExecuteInfo;

@Service
@Transactional
public class FXRYResumeInfoService extends BaseService<FXRYResumeInfo, String> {

	@Autowired
	private FXRYResumeInfoDao dao;

	@Override
	public void create(FXRYResumeInfo entity) {
		dao.save(entity);
	}

	@Override
	public void update(FXRYResumeInfo entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public FXRYResumeInfo get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<FXRYResumeInfo> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FXRYResumeInfo> findPaged(Page<FXRYResumeInfo> page,
			FXRYResumeInfo entity) {
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
	public Page<FXRYResumeInfo> findPaged(Page<FXRYResumeInfo> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FXRYResumeInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<FXRYResumeInfo> findPaged(Page<FXRYResumeInfo> page) {
		return dao.findByCriteria(page);
	}

	public List<FXRYResumeInfo> getByFXRYId(String fxryId) {
		return dao.findByProperty("fxryId", fxryId);
	}

	public void deleteByFXRYId(String fxryId) {
		List<FXRYResumeInfo> entities = getByFXRYId(fxryId);
		if (entities != null) {
			for (FXRYResumeInfo entity : entities) {
				dao.delete(entity);
			}
		}
	}
}

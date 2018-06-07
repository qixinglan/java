package com.nci.sfj.transmit.service.dbsx;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.homepage.todo.model.TodoCount;
import com.nci.dcs.jzgl.dagl.dao.ViewFxryReadyReleaseInfoDao;
import com.nci.dcs.jzgl.dagl.model.ViewFxryReadyRelease;
import com.nci.dcs.system.model.Bmb;

@Service
@Transactional
public class ViewFxryReadyReleaseInfoServiceDBSX extends
		BaseService<ViewFxryReadyRelease, String> implements ITodoServiceNew {

	@Autowired
	private ViewFxryReadyReleaseInfoDao viewFxryReadyReleaseInfoDao;

	public Page<ViewFxryReadyRelease> findPaged(Page<ViewFxryReadyRelease> page) {
		return viewFxryReadyReleaseInfoDao.findByCriteria(page);
	}

	@Override
	public TodoCount getTodoCount(Bmb org) {
		Criteria criteria = viewFxryReadyReleaseInfoDao.createCriteria();
		
		Integer count = 0;
		if (null != org) {
			if (org.isSfs()) {
				criteria.add(Restrictions.eq("responseOrg", org.getBm()));
			} else if (org.isQxsfj()) {
				criteria.add(Restrictions.eq("supOrgId", org.getBm()));
			}
			criteria.setProjection(Projections.rowCount());
			try {
				count = (Integer) criteria.uniqueResult();
			} catch (Exception e) {
				logger.warn("获取预解矫人数出错:\n", e);
			}
		}
		return new TodoCount(count, 0);
	}

	@Override
	public void create(ViewFxryReadyRelease entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(ViewFxryReadyRelease entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {

	}

	@Override
	public ViewFxryReadyRelease get(String id) {
		// TODO Auto-generated method stub
		return viewFxryReadyReleaseInfoDao.get(id);
	}

	@Override
	public List<ViewFxryReadyRelease> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ViewFxryReadyRelease> findPaged(
			Page<ViewFxryReadyRelease> page, ViewFxryReadyRelease entity) {
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
	public Page<ViewFxryReadyRelease> findPaged(
			Page<ViewFxryReadyRelease> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewFxryReadyRelease entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

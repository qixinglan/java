package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.LoginInfoUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.utils.StrutsSessionManager;
import com.nci.dcs.homepage.todo.model.TodoCount;
import com.nci.dcs.homepage.todo.service.ITodoService;
import com.nci.dcs.jzgl.dagl.dao.ViewFxryTempoutprionExtendDao;
import com.nci.dcs.jzgl.dagl.model.ViewFxryTempoutprionExtend;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.User;

@Service
@Transactional
public class ViewFxryTempoutprionExtendService extends
		BaseService<ViewFxryTempoutprionExtend, String> implements ITodoService {

	@Autowired
	private ViewFxryTempoutprionExtendDao viewFxryTempoutprionExtendDao;

	public Page<ViewFxryTempoutprionExtend> findPaged(
			Page<ViewFxryTempoutprionExtend> page) {
		return viewFxryTempoutprionExtendDao.findByCriteria(page);
	}

	@Override
	public TodoCount getTodoCount(User user) {
		Criteria criteria = viewFxryTempoutprionExtendDao.createCriteria();
		Bmb org = LoginInfoUtils.getCurOrg(StrutsSessionManager.getSession());
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
				logger.warn("获取暂监外续保人数出错:\n", e);
			}
		}
		return new TodoCount(count, 0);
	}

	@Override
	public void create(ViewFxryTempoutprionExtend entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(ViewFxryTempoutprionExtend entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {

	}

	@Override
	public ViewFxryTempoutprionExtend get(String id) {
		// TODO Auto-generated method stub
		return viewFxryTempoutprionExtendDao.get(id);
	}

	@Override
	public List<ViewFxryTempoutprionExtend> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ViewFxryTempoutprionExtend> findPaged(
			Page<ViewFxryTempoutprionExtend> page,
			ViewFxryTempoutprionExtend entity) {
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
	public Page<ViewFxryTempoutprionExtend> findPaged(
			Page<ViewFxryTempoutprionExtend> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(
			ViewFxryTempoutprionExtend entity) {
		// TODO Auto-generated method stub
		return null;
	}
}

package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.LoginInfoUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.utils.StrutsSessionManager;
import com.nci.dcs.homepage.todo.model.TodoCount;
import com.nci.dcs.homepage.todo.service.ITodoService;
import com.nci.dcs.jzgl.dagl.dao.ViewFXRYTransferInfoDao;
import com.nci.dcs.jzgl.dagl.model.ViewFxryTransferInfo;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.system.model.User;

@Service
@Transactional
public class ViewFXRYTransferInfoService extends
		BaseService<ViewFxryTransferInfo, String> implements ITodoService {

	@Autowired
	private ViewFXRYTransferInfoDao viewFXRYTransferInfoDao;

	public Page<ViewFxryTransferInfo> findPaged(Page<ViewFxryTransferInfo> page) {
		return viewFXRYTransferInfoDao.findByCriteria(page);
	}

	@Override
	public TodoCount getTodoCount(User user) {
		Criteria criteria = viewFXRYTransferInfoDao.createCriteria();
		String orgId = LoginInfoUtils.getCurOrgId(StrutsSessionManager
				.getSession());
		//检查用户是否有查看特管人员权限特管
				if("2".equals(user.getIsws())){
					criteria.add(Restrictions.eq("isTgry","2"));
				}
		Integer count = 0;
		if (!CommonUtils.isNull(orgId)) {
			criteria.add(Restrictions.eq("transtatus", 0));
			criteria.add(Restrictions.eq("receiveOrgId", orgId));
			criteria.add(Restrictions.eq("state", FXRYState.ZC));
			criteria.setProjection(Projections.rowCount());
			try {
				count = (Integer) criteria.uniqueResult();
			} catch (Exception e) {
				logger.warn("获取待转入人数出错:\n", e);
			}
		}
		return new TodoCount(count, 0);
	}

	@Override
	public void create(ViewFxryTransferInfo entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(ViewFxryTransferInfo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {

	}

	@Override
	public ViewFxryTransferInfo get(String id) {
		// TODO Auto-generated method stub
		return viewFXRYTransferInfoDao.get(id);
	}

	@Override
	public List<ViewFxryTransferInfo> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ViewFxryTransferInfo> findPaged(
			Page<ViewFxryTransferInfo> page, ViewFxryTransferInfo entity) {
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
	public Page<ViewFxryTransferInfo> findPaged(
			Page<ViewFxryTransferInfo> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewFxryTransferInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

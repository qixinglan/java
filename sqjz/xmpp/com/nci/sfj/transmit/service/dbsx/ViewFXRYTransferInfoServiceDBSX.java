package com.nci.sfj.transmit.service.dbsx;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.homepage.todo.model.TodoCount;
import com.nci.dcs.jzgl.dagl.dao.ViewFXRYTransferInfoDao;
import com.nci.dcs.jzgl.dagl.model.ViewFxryTransferInfo;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.system.model.Bmb;

@Service
@Transactional
public class ViewFXRYTransferInfoServiceDBSX extends
		BaseService<ViewFxryTransferInfo, String> implements ITodoServiceNew {

	@Autowired
	private ViewFXRYTransferInfoDao viewFXRYTransferInfoDao;

	public Page<ViewFxryTransferInfo> findPaged(Page<ViewFxryTransferInfo> page) {
		return viewFXRYTransferInfoDao.findByCriteria(page);
	}

	@Override
	public TodoCount getTodoCount(Bmb bmb) {
		Criteria criteria = viewFXRYTransferInfoDao.createCriteria();
		String orgId = bmb.getBm();
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

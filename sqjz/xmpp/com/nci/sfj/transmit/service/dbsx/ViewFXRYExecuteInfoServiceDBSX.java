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
import com.nci.dcs.jzgl.dagl.dao.ViewFXRYExecuteInfoDao;
import com.nci.dcs.jzgl.dagl.model.ViewFXRYExecuteInfo;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.system.model.Bmb;

@Service
@Transactional
public class ViewFXRYExecuteInfoServiceDBSX extends
		BaseService<ViewFXRYExecuteInfo, String> implements ITodoServiceNew {

	@Autowired
	private ViewFXRYExecuteInfoDao viewFXRYExecuteInfoDao;

	public Page<ViewFXRYExecuteInfo> findPaged(Page<ViewFXRYExecuteInfo> page) {
		return viewFXRYExecuteInfoDao.findByCriteria(page);
	}

	@Override
	public TodoCount getTodoCount(Bmb bmb) {
		Criteria criteria = viewFXRYExecuteInfoDao.createCriteria();
		String orgId = bmb.getBm();
		Integer count = 0;
		if (!CommonUtils.isNull(orgId)) {
			criteria.add(Restrictions.eq("responseOrg", orgId));
			criteria.add(Restrictions.ne("state", FXRYState.JJ));
			criteria.add(Restrictions.isNull("excuteId"));
			criteria.setProjection(Projections.rowCount());
			try {
				count = (Integer) criteria.uniqueResult();
			} catch (Exception e) {
				logger.warn("获取待接收报到人数出错:\n", e);
			}
		}
		return new TodoCount(count, 0);
	}

	@Override
	public void create(ViewFXRYExecuteInfo entity) {
	}

	@Override
	public void update(ViewFXRYExecuteInfo entity) {
	}

	@Override
	public void delete(String id) {
	}

	@Override
	public ViewFXRYExecuteInfo get(String id) {
		return null;
	}

	@Override
	public List<ViewFXRYExecuteInfo> find() {
		return null;
	}

	@Override
	public Page<ViewFXRYExecuteInfo> findPaged(Page<ViewFXRYExecuteInfo> page,
			ViewFXRYExecuteInfo entity) {
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
	public Page<ViewFXRYExecuteInfo> findPaged(Page<ViewFXRYExecuteInfo> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewFXRYExecuteInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

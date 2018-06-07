package com.nci.dcs.jzgl.dagl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
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
import com.nci.dcs.jzgl.dagl.dao.ViewFxryUnmanageHistoryDao;
import com.nci.dcs.jzgl.dagl.model.ViewFxryUnmanageHistory;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.supervision.model.SupervisionCount;
import com.nci.dcs.supervision.service.ISupervisionService;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.User;

@Service
@Transactional
public class ViewFxryUnmanageHistoryService extends
		BaseService<ViewFxryUnmanageHistory, String> implements
		ISupervisionService {

	@Autowired
	private ViewFxryUnmanageHistoryDao dao;

	private Criteria getSupervisionCriteria() {
		Criteria criteria = dao.createCriteria();
		criteria.add(Restrictions.eq("state", FXRYState.TG));
		criteria.add(Restrictions.isNull("endDate"));
		return criteria;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public SupervisionCount getSupervisionCount(User user) {
		Bmb bmb = LoginInfoUtils.getCurOrg(StrutsSessionManager.getSession());
		SupervisionCount count = new SupervisionCount();
		if (bmb != null && !bmb.isSfs()) {
			Criteria criteria = getSupervisionCriteria();
			//过滤特管人员
			if("2".equals(user.getIsws())){
				criteria.add(Restrictions.eq("isTgry","2"));
			}
			String orgId = bmb.getBm();
			if (bmb.isQxsfj()) {
				criteria.add(Restrictions.or(
						Restrictions.eq("responseOrg", orgId),
						Restrictions.eq("supOrgId", orgId)));
			}
			Object o = criteria.setProjection(Projections.rowCount())
					.uniqueResult();
			int totalCount = 0;
			if (o != null) {
				totalCount = (Integer) o;
			}
			count.setTotal(totalCount);
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.rowCount());
			if (bmb.isQxsfj()) {
				projectionList.add(Projections.groupProperty("responseOrg"));
			} else if (bmb.isSj()) {
				projectionList.add(Projections.groupProperty("supOrgId"));
			}
			criteria.setProjection(projectionList);
			List list = criteria.list();
			Map<String, Integer> map = new HashMap<String, Integer>();
			for (Object obj : list) {
				Object[] object = (Object[]) obj;
				map.put((String) object[1], (Integer) object[0]);
			}
			count.setSupervisions(map);
		}
		return count;
	}

	@Override
	public SupervisionCount getSupervisionCount(Bmb bmb,User user) {
		SupervisionCount count = new SupervisionCount();
		if (bmb != null && !bmb.isSj()) {
			Criteria criteria = getSupervisionCriteria();
			//过滤特管人员
			if("2".equals(user.getIsws())){
				criteria.add(Restrictions.eq("isTgry","2"));
			}
			String orgId = bmb.getBm();
			if (bmb.isQxsfj()) {
				criteria.add(Restrictions.or(
						Restrictions.eq("responseOrg", orgId),
						Restrictions.eq("supOrgId", orgId)));
			}
			if (bmb.isSfs()) {
				criteria.add(Restrictions.eq("responseOrg", bmb.getBm()));
			}
			Object o = criteria.setProjection(Projections.rowCount())
					.uniqueResult();
			int totalCount = 0;
			if (o != null) {
				totalCount = (Integer) o;
			}
			count.setTotal(totalCount);
		}
		return count;
	}

	@Override
	public void create(ViewFxryUnmanageHistory entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(ViewFxryUnmanageHistory entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {

	}

	@Override
	public ViewFxryUnmanageHistory get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<ViewFxryUnmanageHistory> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ViewFxryUnmanageHistory> findPaged(
			Page<ViewFxryUnmanageHistory> page, ViewFxryUnmanageHistory entity) {
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
	public Page<ViewFxryUnmanageHistory> findPaged(
			Page<ViewFxryUnmanageHistory> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(
			ViewFxryUnmanageHistory entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<ViewFxryUnmanageHistory> findPaged(
			Page<ViewFxryUnmanageHistory> page) {
		return dao.findByCriteria(page);
	}

}

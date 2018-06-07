package com.nci.dcs.supervision.service;

import java.util.Date;
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
import com.nci.dcs.data.utils.DateUtils;
import com.nci.dcs.supervision.dao.SupervisionPublicWorkDao;
import com.nci.dcs.supervision.model.SupervisionCount;
import com.nci.dcs.supervision.model.SupervisionPublicWork;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.User;

@Service
@Transactional
public class SupervisionPublicWorkService extends
		BaseService<SupervisionPublicWork, String> implements
		ISupervisionService {
	@Autowired
	private SupervisionPublicWorkDao supervisionPublicWorkDao;

	private Criteria getSupervisionCriteria() {
		Criteria criteria = supervisionPublicWorkDao.createCriteria();
		criteria.add(Restrictions.lt("createTime",
				DateUtils.getFirstDayForMonth(new Date())));
		return criteria;
	}

	@Override
	@SuppressWarnings("rawtypes")
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
						Restrictions.eq("supOrg", orgId)));
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
				projectionList.add(Projections.groupProperty("supOrg"));
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
						Restrictions.eq("supOrg", orgId)));
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

	public Page<SupervisionPublicWork> findPaged(
			Page<SupervisionPublicWork> page) {
		return supervisionPublicWorkDao.findByCriteria(page);
	}

	@Override
	public void create(SupervisionPublicWork entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(SupervisionPublicWork entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public SupervisionPublicWork get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SupervisionPublicWork> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SupervisionPublicWork> findPaged(
			Page<SupervisionPublicWork> page, SupervisionPublicWork entity) {
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
	public Page<SupervisionPublicWork> findPaged(
			Page<SupervisionPublicWork> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(SupervisionPublicWork entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
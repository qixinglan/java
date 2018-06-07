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
import com.nci.dcs.supervision.dao.SupervisionPhoneReportDao;
import com.nci.dcs.supervision.model.SupervisionCount;
import com.nci.dcs.supervision.model.SupervisionPhoneReport;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.User;

@Service
@Transactional
public class SupervisionPhoneReportService extends
		BaseService<SupervisionPhoneReport, String> implements
		ISupervisionService {
	@Autowired
	private SupervisionPhoneReportDao supervisionPhoneReportDao;

	private Criteria getSupervisionCriteria() {
		Criteria criteria = supervisionPhoneReportDao.createCriteria();
		criteria.add(Restrictions.lt("createTime",
				DateUtils.getMonday(new Date())));
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

	public Page<SupervisionPhoneReport> findPaged(
			Page<SupervisionPhoneReport> page) {
		return supervisionPhoneReportDao.findByCriteria(page);
	}

	@Override
	public void create(SupervisionPhoneReport entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(SupervisionPhoneReport entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public SupervisionPhoneReport get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SupervisionPhoneReport> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SupervisionPhoneReport> findPaged(
			Page<SupervisionPhoneReport> page, SupervisionPhoneReport entity) {
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
	public Page<SupervisionPhoneReport> findPaged(
			Page<SupervisionPhoneReport> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(
			SupervisionPhoneReport entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

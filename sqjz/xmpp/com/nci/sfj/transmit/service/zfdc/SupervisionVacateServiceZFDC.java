package com.nci.sfj.transmit.service.zfdc;

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
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.supervision.dao.SupervisionVacateDao;
import com.nci.dcs.supervision.model.SupervisionCount;
import com.nci.dcs.supervision.model.SupervisionVacate;
import com.nci.dcs.system.model.Bmb;

@Service
@Transactional
public class SupervisionVacateServiceZFDC extends
		BaseService<SupervisionVacate, String> implements ISupervisionServiceNew {
	@Autowired
	private SupervisionVacateDao supervisionVacateDao;

	@SuppressWarnings("rawtypes")
	@Override
	public SupervisionCount getSupervisionCountNew(Bmb bmb) {
		SupervisionCount count = new SupervisionCount();
		if (bmb != null && !bmb.isSfs()) {
			Criteria criteria = supervisionVacateDao.createCriteria();
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
	public SupervisionCount getSupervisionCount(Bmb bmb) {
		SupervisionCount count = new SupervisionCount();
		if (bmb != null && !bmb.isSj()) {
			Criteria criteria = supervisionVacateDao.createCriteria();
			String orgId = bmb.getBm();
			if (bmb.isQxsfj()) {
				criteria.add(Restrictions.or(
						Restrictions.eq("responseOrg", orgId),
						Restrictions.eq("supOrg", orgId)));
			}
			if (bmb.isSfs()) {
				criteria.add(Restrictions.eq("responseOrg", orgId));
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

	public Page<SupervisionVacate> findPaged(Page<SupervisionVacate> page) {
		return supervisionVacateDao.findByCriteria(page);
	}

	@Override
	public void create(SupervisionVacate entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(SupervisionVacate entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public SupervisionVacate get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SupervisionVacate> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SupervisionVacate> findPaged(Page<SupervisionVacate> page,
			SupervisionVacate entity) {
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
	public Page<SupervisionVacate> findPaged(Page<SupervisionVacate> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(SupervisionVacate entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

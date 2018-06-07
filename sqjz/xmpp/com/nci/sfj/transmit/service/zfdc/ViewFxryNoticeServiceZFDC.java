package com.nci.sfj.transmit.service.zfdc;

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
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.jzgl.dagl.dao.ViewFxryNoticeDao;
import com.nci.dcs.jzgl.dagl.model.ViewFxryNotice;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.supervision.model.SupervisionCount;
import com.nci.dcs.system.model.Bmb;

@Service
@Transactional
public class ViewFxryNoticeServiceZFDC extends BaseService<ViewFxryNotice, String>
		implements ISupervisionServiceNew {

	@Autowired
	private ViewFxryNoticeDao dao;

	@Autowired
	private OrganizationInfoService orgService;

	public Page<ViewFxryNotice> findPaged(Page<ViewFxryNotice> page) {
		return dao.findByCriteria(page);
	}

	@Override
	public void create(ViewFxryNotice entity) {

	}

	@Override
	public void update(ViewFxryNotice entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ViewFxryNotice get(String id) {
		return null;
	}

	@Override
	public List<ViewFxryNotice> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ViewFxryNotice> findPaged(Page<ViewFxryNotice> page,
			ViewFxryNotice entity) {
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
	public Page<ViewFxryNotice> findPaged(Page<ViewFxryNotice> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewFxryNotice entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @name 查询列表信息
	 * @param page
	 * @return
	 * @author caolj
	 * @date 2015年4月1日 上午8:37:48
	 * @message:
	 */
	public Page<ViewFxryNotice> findPaged(Page<ViewFxryNotice> page,
			List<SearchRule> rules) {
		return dao.findBySeachRule(page, rules);
	}

	
	public SupervisionCount getSupervisionCount(Bmb bmb) {
		SupervisionCount count = new SupervisionCount();
		if (bmb != null) {
			Criteria criteria = dao.createCriteria();
			criteria.add(Restrictions.lt("reportTime", new Date()));
			criteria.add(Restrictions.eq("state", FXRYState.ZJ));
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

	@Override
	public SupervisionCount getSupervisionCountNew(Bmb bmb) {
		SupervisionCount count = new SupervisionCount();
		if (bmb != null && !bmb.isSfs()) {
			Criteria criteria = dao.createCriteria();
			criteria.add(Restrictions.lt("reportTime", new Date()));
			criteria.add(Restrictions.eq("state", FXRYState.ZJ));
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
			List<?> list = criteria.list();
			Map<String, Integer> map = new HashMap<String, Integer>();
			for (Object obj : list) {
				Object[] object = (Object[]) obj;
				map.put((String) object[1], (Integer) object[0]);
			}
			count.setSupervisions(map);
		}
		return count;
	}
}

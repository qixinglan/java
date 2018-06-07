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
import com.nci.dcs.official.dao.WeekNoticeDao;
import com.nci.dcs.official.model.CcWeeknotice;
import com.nci.dcs.system.model.Bmb;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class WeekNoticeServiceDBSX extends BaseService<CcWeeknotice, String>
		implements ITodoServiceNew {

	@Autowired
	private WeekNoticeDao weekNoticeDao;

	@Override
	public TodoCount getTodoCount(Bmb bmb) {
		Criteria criteria = weekNoticeDao.createCriteria();
		String orgId = bmb.getBm();
		Integer count = 0;
		if (!CommonUtils.isNull(orgId)) {
			criteria.createAlias("ccNoticereceives", "ccNoticereceives",
					Criteria.LEFT_JOIN);
			criteria.add(Restrictions.eq("ccNoticereceives.orgId", orgId));
			criteria.add(Restrictions.eq("ccNoticereceives.status", "2"));
			criteria.add(Restrictions.eq("status", "1"));
			criteria.setProjection(Projections.rowCount());
			try {
				count = (Integer) criteria.uniqueResult();
			} catch (Exception e) {
				logger.warn("获取待查阅通知数量出错:\n", e);
			}
		}
		return new TodoCount(count, 0);
	}

	public Page<CcWeeknotice> findPaged(Page<CcWeeknotice> page, String orgId,
			String oper, String status) {
		Criteria criteria = weekNoticeDao.createCriteria(page.getCriterions());
		if (oper != null && ("tzView").equals(oper)) {
			// 通知查看进入
			criteria.createAlias("ccNoticereceives", "ccNoticereceives",
					Criteria.LEFT_JOIN);
			criteria.add(Restrictions.eq("ccNoticereceives.orgId", orgId));
			if (!CommonUtils.isNull(status)) {
				criteria.add(Restrictions.eq("ccNoticereceives.status", status));
			}
			criteria.add(Restrictions.eq("status", "1"));
		} else {
			// 通知拟制进入
			criteria.add(Restrictions.eq("recordOrgId", orgId));
		}
		return weekNoticeDao.findPageByCriteria(criteria, page);
	}

	@Override
	public void create(CcWeeknotice entity) {
		weekNoticeDao.save(entity);
	}

	@Override
	public void update(CcWeeknotice entity) {
		// TODO Auto-generated method stub
		weekNoticeDao.save(entity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		weekNoticeDao.delete(id);
	}

	@Override
	public CcWeeknotice get(String id) {
		// TODO Auto-generated method stub

		return weekNoticeDao.get(id);
	}

	@Override
	public List<CcWeeknotice> find() {
		// TODO Auto-generated method stub
		return weekNoticeDao.getAll();
	}

	@Override
	public Page<CcWeeknotice> findPaged(Page<CcWeeknotice> page,
			CcWeeknotice entity) {
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
	public Page<CcWeeknotice> findPaged(Page<CcWeeknotice> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcWeeknotice entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<CcWeeknotice> findPaged(Page<CcWeeknotice> page) {
		return weekNoticeDao.findByCriteria(page);
	}
}

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
import com.nci.dcs.jzgl.mission.dao.ViewInterviewDao;
import com.nci.dcs.jzgl.mission.model.ViewInterview;
import com.nci.dcs.system.model.Bmb;

@Service
@Transactional
public class InterviewServiceDBSX extends BaseService<ViewInterview, String>
		implements ITodoServiceNew {
	@Autowired
	private ViewInterviewDao viewInterviewDao;

	@Override
	public TodoCount getTodoCount(Bmb bmb) {
		Criteria criteria = viewInterviewDao.createCriteria();
		String orgId = bmb.getBm();
		Integer count = 0;
		if (!CommonUtils.isNull(orgId)) {
			criteria.add(Restrictions.eq("orgId", orgId));
			criteria.setProjection(Projections.rowCount());
			try {
				count = (Integer) criteria.uniqueResult();
			} catch (Exception e) {
				logger.warn("获取待走访人数出错:\n", e);
			}
		}
		return new TodoCount(count, 0);
	}

	public Page<ViewInterview> findPaged(Page<ViewInterview> page) {
		return viewInterviewDao.findByCriteria(page);
	}

	@Override
	public void create(ViewInterview entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ViewInterview entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ViewInterview get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ViewInterview> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ViewInterview> findPaged(Page<ViewInterview> page,
			ViewInterview entity) {
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
	public Page<ViewInterview> findPaged(Page<ViewInterview> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewInterview entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

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
import com.nci.dcs.jzgl.mission.dao.ViewPhoneReportDao;
import com.nci.dcs.jzgl.mission.model.ViewPhoneReport;
import com.nci.dcs.system.model.Bmb;

@Service
@Transactional
public class PhoneReportServiceDBSX extends BaseService<ViewPhoneReport, String>
		implements ITodoServiceNew {
	@Autowired
	private ViewPhoneReportDao viewPhoneReportDao;

	@Override
	public TodoCount getTodoCount(Bmb bmb) {
		Criteria criteria = viewPhoneReportDao.createCriteria();
		String orgId = bmb.getBm();
		Integer count = 0;
		if (!CommonUtils.isNull(orgId)) {
			criteria.add(Restrictions.eq("orgId", orgId));
			criteria.setProjection(Projections.rowCount());
			try {
				count = (Integer) criteria.uniqueResult();
			} catch (Exception e) {
				logger.warn("获取待电话报到人数出错:\n", e);
			}
		}
		return new TodoCount(count, 0);
	}

	public Page<ViewPhoneReport> findPaged(Page<ViewPhoneReport> page) {
		return viewPhoneReportDao.findByCriteria(page);
	}

	@Override
	public void create(ViewPhoneReport entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ViewPhoneReport entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ViewPhoneReport get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ViewPhoneReport> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ViewPhoneReport> findPaged(Page<ViewPhoneReport> page,
			ViewPhoneReport entity) {
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
	public Page<ViewPhoneReport> findPaged(Page<ViewPhoneReport> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewPhoneReport entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

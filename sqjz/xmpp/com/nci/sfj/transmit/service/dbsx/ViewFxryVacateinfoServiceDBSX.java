package com.nci.sfj.transmit.service.dbsx;

import java.util.Calendar;
import java.util.Date;
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
import com.nci.dcs.jzgl.rcbdgl.dao.ViewFxryVacateinfoDao;
import com.nci.dcs.jzgl.rcbdgl.model.ViewFxryVacateinfo;
import com.nci.dcs.system.model.Bmb;

@Service
@Transactional
public class ViewFxryVacateinfoServiceDBSX extends
		BaseService<ViewFxryVacateinfo, String> implements ITodoServiceNew {

	@Autowired
	private ViewFxryVacateinfoDao viewFxryVacateinfoDao;

	public Page<ViewFxryVacateinfo> findPaged(Page<ViewFxryVacateinfo> page) {
		return viewFxryVacateinfoDao.findByCriteria(page);
	}

	@Override
	public TodoCount getTodoCount(Bmb bmb) {
		Criteria criteria = viewFxryVacateinfoDao.createCriteria();
		String orgId = bmb.getBm();
		Integer count = 0;
		if (!CommonUtils.isNull(orgId)) {
			// 司法所按负责单位查询
			criteria.add(Restrictions.eq("recordOrgId", orgId));
			criteria.add(Restrictions.isNull("reportDate"));
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			criteria.add(Restrictions.le("endDate", calendar.getTime()));
			criteria.setProjection(Projections.rowCount());
			try {
				count = (Integer) criteria.uniqueResult();
			} catch (Exception e) {
				logger.warn("获取待销假提醒人数出错:\n", e);
			}
		}
		return new TodoCount(count, 0);
	}

	@Override
	public void create(ViewFxryVacateinfo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ViewFxryVacateinfo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ViewFxryVacateinfo get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ViewFxryVacateinfo> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ViewFxryVacateinfo> findPaged(Page<ViewFxryVacateinfo> page,
			ViewFxryVacateinfo entity) {
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
	public Page<ViewFxryVacateinfo> findPaged(Page<ViewFxryVacateinfo> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewFxryVacateinfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

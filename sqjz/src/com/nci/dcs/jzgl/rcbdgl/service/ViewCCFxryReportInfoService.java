package com.nci.dcs.jzgl.rcbdgl.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.LoginInfoUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.utils.StrutsSessionManager;
import com.nci.dcs.homepage.todo.model.TodoCount;
import com.nci.dcs.homepage.todo.service.ITodoService;
import com.nci.dcs.jzgl.rcbdgl.dao.ViewCCFxryReportInfoDao;
import com.nci.dcs.jzgl.rcbdgl.model.ViewCCFxryReportinfo;
import com.nci.dcs.system.model.User;

/**
 * Description:日常工作提醒中获取待报到人员的Service
 * 
 * @author Shuzz
 * @since 2014年10月22日上午9:40:11
 */
@Service
@Transactional
public class ViewCCFxryReportInfoService extends
		BaseService<ViewCCFxryReportinfo, String> implements ITodoService {

	@Autowired
	private ViewCCFxryReportInfoDao viewCCFxryReportInfoDao;

	public List<Criterion> getTodoCriterion(String orgId) {
		List<Criterion> list = new ArrayList<Criterion>();
		if (!CommonUtils.isNull(orgId)) {
			list.add(Restrictions.eq("orgid", orgId));
		}
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DAY_OF_YEAR, -1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		list.add(Restrictions.or(Restrictions.isNull("reportdate"),
				Restrictions.le("reportdate", c.getTime())));
		return list;
	}

	@Override
	public TodoCount getTodoCount(User user) {
		Criteria criteria = viewCCFxryReportInfoDao.createCriteria();
		String orgId = LoginInfoUtils.getCurOrgId(StrutsSessionManager
				.getSession());
		//检查用户是否有查看特管人员权限特管
		if("2".equals(user.getIsws())){
			criteria.add(Restrictions.eq("isTgry","2"));
		}
		Integer count = 0;
		if (!CommonUtils.isNull(orgId)) {
			for (Criterion criterion : getTodoCriterion(orgId)) {
				criteria.add(criterion);
			}
			criteria.setProjection(Projections.rowCount());
			try {
				count = (Integer) criteria.uniqueResult();
			} catch (Exception e) {
				logger.warn("获取待教育人数出错:\n", e);
			}
		}
		return new TodoCount(count, 0);
	}

	@Override
	public void audit(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void create(ViewCCFxryReportinfo entity) {
		viewCCFxryReportInfoDao.save(entity);
	}

	@Override
	public void delete(String id) {
		viewCCFxryReportInfoDao.delete(id);

	}

	@Override
	public void disable(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void enable(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ViewCCFxryReportinfo> find() {
		return null;
	}

	@Override
	public Page<ViewCCFxryReportinfo> findPaged(
			Page<ViewCCFxryReportinfo> page, ViewCCFxryReportinfo entity) {
		return viewCCFxryReportInfoDao.findByFilters(page,
				this.buildPropertyFilter(entity));
	}

	public Page<ViewCCFxryReportinfo> findPaged(Page<ViewCCFxryReportinfo> page) {
		return viewCCFxryReportInfoDao.findByCriteria(page);
	}

	@Override
	public Page<ViewCCFxryReportinfo> findPaged(
			Page<ViewCCFxryReportinfo> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ViewCCFxryReportinfo get(String id) {
		// TODO Auto-generated method stub
		return viewCCFxryReportInfoDao.get(id);
	}

	@Override
	public void update(ViewCCFxryReportinfo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewCCFxryReportinfo entity) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		return filters;
	}

}

package com.nci.sfj.transmit.service.dbsx;

import java.sql.Timestamp;
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
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.homepage.todo.model.TodoCount;
import com.nci.dcs.jzgl.dagl.dao.ViewFxryNoticeDao;
import com.nci.dcs.jzgl.dagl.model.ViewFxryNotice;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.Bmb;

@Service
@Transactional
public class ViewFxryNoticeServiceDBSX extends BaseService<ViewFxryNotice, String>
		implements ITodoServiceNew {

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

	/**
	 * @name 获取数量
	 * @param criterions
	 * @return
	 * @author caolj
	 * @date 2015年4月1日 上午11:26:19
	 * @message:
	 */
	@Override
	public TodoCount getTodoCount(Bmb bmb) {
		String orgId = bmb.getBm();
		Calendar cal = Calendar.getInstance();
		Date dealDate = cal.getTime();
		Timestamp tt = new Timestamp(dealDate.getTime());
		Criteria criteria = dao.createCriteria();
		criteria.add(Restrictions.lt("reportTime", tt));
		criteria.add(Restrictions.eq("state", FXRYState.ZJ));
		if (orgId != null && !orgId.equals("1")) {
			criteria.add(Restrictions.in("responseOrg",
					orgService.getChildrenIds(orgId)));
		}
		Object o = criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		int totalCount = 0;
		if (o != null) {
			totalCount = (Integer) o;
		}
		return new TodoCount(totalCount, 0);
	}

}

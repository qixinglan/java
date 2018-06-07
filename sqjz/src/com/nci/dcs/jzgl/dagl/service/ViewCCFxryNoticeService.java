package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.hibernate.Criteria;
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
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.homepage.todo.model.TodoCount;
import com.nci.dcs.homepage.todo.service.ITodoService;
import com.nci.dcs.jzgl.dagl.dao.ViewCCFxryNoticeDao;
import com.nci.dcs.jzgl.dagl.model.ViewCCFxryNotice;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.User;

@Service
@Transactional
public class ViewCCFxryNoticeService extends BaseService<ViewCCFxryNotice, String> implements ITodoService{

	@Autowired
	private ViewCCFxryNoticeDao dao;
	
	@Autowired
	private OrganizationInfoService orgService;
	
	@Override
	public void create(ViewCCFxryNotice entity) {
		
		
	}

	@Override
	public void update(ViewCCFxryNotice entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ViewCCFxryNotice get(String id) {
		return dao.get(id);
	}

	@Override
	public List<ViewCCFxryNotice> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ViewCCFxryNotice> findPaged(Page<ViewCCFxryNotice> page,
			ViewCCFxryNotice entity) {
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
	public Page<ViewCCFxryNotice> findPaged(Page<ViewCCFxryNotice> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewCCFxryNotice entity) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @name 查询列表信息
	 * @param page
	 * @return
	 * @author caolj
	 * @date 2015年3月30日 上午11:56:56
	 * @message:
	 */
	public Page<ViewCCFxryNotice> findPaged(Page<ViewCCFxryNotice> page, List<SearchRule> rules) {
		return dao.findBySeachRule(page,rules);
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
	public TodoCount getTodoCount(User user) {
		String orgId = LoginInfoUtils.getCurOrgId(StrutsSessionManager.getSession());
		Criteria criteria = dao.createCriteria();
		//特管过滤
		if("2".equals(user.getIsws())){
			criteria.add(Restrictions.eq("isTgry","2"));
		}
		criteria.add(Restrictions.eq("status","2"));
		if (orgId != null && !orgId.equals("1")) {
			criteria.add(Restrictions.in("orgId",orgService.getChildrenIds(orgId)));
		}
		Object o = criteria.setProjection(Projections.rowCount()).uniqueResult();
		int totalCount = 0;
		if (o != null) {
			totalCount = (Integer) o;
		}
		return new TodoCount(totalCount, 0);
	}
}

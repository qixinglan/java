package com.nci.dcs.official.service;

import java.util.List;

import org.hibernate.Criteria;
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
import com.nci.dcs.official.dao.DynamicreportDao;
import com.nci.dcs.official.model.CcDynamicreport;
import com.nci.dcs.system.model.User;

@Service
@Transactional
public class DynamicreportService extends BaseService<CcDynamicreport, String>
		implements ITodoService {

	@Autowired
	private DynamicreportDao dao;

	public DynamicreportDao getDao() {
		return dao;
	}

	public void setDao(DynamicreportDao dao) {
		this.dao = dao;
	}

	@Override
	public TodoCount getTodoCount(User user) {
		Criteria criteria = dao.createCriteria();
		String orgId = LoginInfoUtils.getCurOrgId(StrutsSessionManager
				.getSession());
		Integer count = 0;
		if (!CommonUtils.isNull(orgId)) {
			criteria.add(Restrictions.eq("receiveOrgId", orgId));
			criteria.add(Restrictions.eq("status", "3"));
			criteria.setProjection(Projections.rowCount());
			try {
				count = (Integer) criteria.uniqueResult();
			} catch (Exception e) {
				logger.warn("获取动态信息上报数量出错:\n", e);
			}
		}
		return new TodoCount(count, 0);
	}

	@Override
	public void create(CcDynamicreport entity) {
		dao.save(entity);

	}

	@Override
	public void update(CcDynamicreport entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public CcDynamicreport get(String id) {

		return dao.get(id);
	}

	@Override
	public List<CcDynamicreport> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CcDynamicreport> findPaged(Page<CcDynamicreport> page,
			CcDynamicreport entity) {
		return dao.findByCriteria(page);
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
	public Page<CcDynamicreport> findPaged(Page<CcDynamicreport> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcDynamicreport entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.nci.dcs.jzgl.mission.service;

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
import com.nci.dcs.jzgl.mission.dao.ViewEducationDao;
import com.nci.dcs.jzgl.mission.model.ViewEducation;
import com.nci.dcs.system.model.User;

@Service
@Transactional
public class EducationService extends BaseService<ViewEducation, String>
		implements ITodoService {
	@Autowired
	private ViewEducationDao viewEducationDao;

	@Override
	public TodoCount getTodoCount(User user) {
		Criteria criteria = viewEducationDao.createCriteria();
		String orgId = LoginInfoUtils.getCurOrgId(StrutsSessionManager
				.getSession());
		//检查用户是否有查看特管人员权限特管
				if("2".equals(user.getIsws())){
					criteria.add(Restrictions.eq("isTgry","2"));
				}
		Integer count = 0;
		if (!CommonUtils.isNull(orgId)) {
			criteria.add(Restrictions.eq("orgId", orgId));
			criteria.setProjection(Projections.rowCount());
			try {
				count = (Integer) criteria.uniqueResult();
			} catch (Exception e) {
				logger.warn("获取待教育人数出错:\n", e);
			}
		}
		return new TodoCount(count, 0);
	}

	public Page<ViewEducation> findPaged(Page<ViewEducation> page) {
		return viewEducationDao.findByCriteria(page);
	}

	@Override
	public void create(ViewEducation entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ViewEducation entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ViewEducation get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ViewEducation> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ViewEducation> findPaged(Page<ViewEducation> page,
			ViewEducation entity) {
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
	public Page<ViewEducation> findPaged(Page<ViewEducation> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewEducation entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

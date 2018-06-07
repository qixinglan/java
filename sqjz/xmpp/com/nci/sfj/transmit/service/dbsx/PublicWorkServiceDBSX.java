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
import com.nci.dcs.jzgl.mission.dao.ViewPublicWorkDao;
import com.nci.dcs.jzgl.mission.model.ViewPublicWork;
import com.nci.dcs.system.model.Bmb;

@Service
@Transactional
public class PublicWorkServiceDBSX extends BaseService<ViewPublicWork, String>
		implements ITodoServiceNew {
	@Autowired
	private ViewPublicWorkDao viewPublicWorkDao;

	@Override
	public TodoCount getTodoCount(Bmb bmb) {
		Criteria criteria = viewPublicWorkDao.createCriteria();
		String orgId = bmb.getBm();
		Integer count = 0;
		if (!CommonUtils.isNull(orgId)) {
			criteria.add(Restrictions.eq("orgId", orgId));
			criteria.setProjection(Projections.rowCount());
			try {
				count = (Integer) criteria.uniqueResult();
			} catch (Exception e) {
				logger.warn("获取待社区服务人数出错:\n", e);
			}
		}
		return new TodoCount(count, 0);
	}

	public Page<ViewPublicWork> findPaged(Page<ViewPublicWork> page) {
		return viewPublicWorkDao.findByCriteria(page);
	}

	@Override
	public void create(ViewPublicWork entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ViewPublicWork entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ViewPublicWork get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ViewPublicWork> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ViewPublicWork> findPaged(Page<ViewPublicWork> page,
			ViewPublicWork entity) {
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
	public Page<ViewPublicWork> findPaged(Page<ViewPublicWork> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewPublicWork entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

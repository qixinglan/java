package com.nci.sfj.transmit.service.dbsx;

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
import com.nci.dcs.homepage.todo.model.TodoCount;
import com.nci.dcs.jzgl.mission.dao.FxryIllExaminationDao;
import com.nci.dcs.jzgl.mission.model.ViewIllExamination;
import com.nci.dcs.system.model.Bmb;

@Service
@Transactional
public class IllExaminationServiceDBSX extends
		BaseService<ViewIllExamination, String> implements ITodoServiceNew {
	@Autowired
	private FxryIllExaminationDao fxryIllExaminationDao;

	@Override
	public TodoCount getTodoCount(Bmb org) {
		Criteria criteria = fxryIllExaminationDao.createCriteria();
		Integer count = 0;
		if (null != org) {
			if (org.isSfs()) {
				criteria.add(Restrictions.eq("orgId", org.getBm()));
			} else if (org.isQxsfj()) {
				criteria.add(Restrictions.eq("supOrgId", org.getBm()));
			}
			criteria.setProjection(Projections.rowCount());
			try {
				count = (Integer) criteria.uniqueResult();
			} catch (Exception e) {
				logger.warn("获取待交病检人数出错:\n", e);
			}
		}
		return new TodoCount(count, 0);
	}

	public Page<ViewIllExamination> findPaged(Page<ViewIllExamination> page) {
		return fxryIllExaminationDao.findByCriteria(page);
	}

	@Override
	public void create(ViewIllExamination entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ViewIllExamination entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ViewIllExamination get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ViewIllExamination> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ViewIllExamination> findPaged(Page<ViewIllExamination> page,
			ViewIllExamination entity) {
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
	public Page<ViewIllExamination> findPaged(Page<ViewIllExamination> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewIllExamination entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

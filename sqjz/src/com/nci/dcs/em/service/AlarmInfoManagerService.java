package com.nci.dcs.em.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.nci.dcs.common.utils.LoginInfoUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.utils.StrutsSessionManager;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.em.dao.AlarmInfoManagerDao;
import com.nci.dcs.em.dwjk.model.ViewAlarminfo;
import com.nci.dcs.homepage.todo.model.TodoCount;
import com.nci.dcs.homepage.todo.service.ITodoService;
import com.nci.dcs.system.model.User;

@Service
@Transactional
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AlarmInfoManagerService extends BaseService<ViewAlarminfo, String>
		implements ITodoService {

	@Autowired
	AlarmInfoManagerDao dao;

	public AlarmInfoManagerDao getDao() {
		return dao;
	}

	public void setDao(AlarmInfoManagerDao dao) {
		this.dao = dao;
	}

	@Override
	public TodoCount getTodoCount(User user) {
		Criteria criteria = dao.createCriteria();
		String orgId = LoginInfoUtils.getCurOrgId(StrutsSessionManager
				.getSession());
		//检查用户是否有查看特管人员权限特管
				if("2".equals(user.getIsws())){
					criteria.add(Restrictions.eq("isTgry","2"));
				}
		Integer count = 0;
		if (!CommonUtils.isNull(orgId)) {
			criteria.add(Restrictions.eq("executeUnit", orgId));
			criteria.add(Restrictions.eq("status", "2"));
			criteria.setProjection(Projections.rowCount());
			try {
				count = (Integer) criteria.uniqueResult();
			} catch (Exception e) {
				logger.warn("获取未处理报警数量出错:\n", e);
			}
		}
		return new TodoCount(count, 0);
	}

	@Override
	public void create(ViewAlarminfo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ViewAlarminfo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ViewAlarminfo get(String id) {
		return dao.get(id);
	}

	@Override
	public List<ViewAlarminfo> find() {
		// TODO Auto-generated method stub
		return dao.find("from ViewAlarminfo");
	}

	public List<ViewAlarminfo> find(String col) {
		return dao.find("select " + col
				+ " from ViewAlarminfo order by status,alarmTime");
	}

	public Page<ViewAlarminfo> findPaged(Page<ViewAlarminfo> page) {
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
	public Page<ViewAlarminfo> findPaged(Page<ViewAlarminfo> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewAlarminfo entity) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		return filters;
	}

	@Override
	public Page<ViewAlarminfo> findPaged(Page<ViewAlarminfo> page,
			ViewAlarminfo entity) {
		// TODO Auto-generated method stub
		return dao.findByFilters(page, this.buildPropertyFilter(entity));
	}

	public Page<ViewAlarminfo> getAlarmByPerson(String personId,
			Page<ViewAlarminfo> page) {
		String hql = "from ViewAlarminfo Where  fxryId in (Select fxryId  From  CcReportOrg  Where personId=?) And status ='2' order by alarmTime";
		String sqlCount = "select count(1) from VIEW_ALARMINFO Where  fxry_Id in (Select fxry_Id  From  CC_REPORT_ORG  Where CC_REPORT_ORG.person_Id=?) And status ='2' ";
		List count = dao.createSQLQuery(sqlCount, personId).list();
		page.setTotalCount(((BigDecimal) count.get(0)).intValue());
		List result = dao.createQuery(hql, personId)
				.setFirstResult((page.getPageNo() - 1) * page.getPageSize())
				.setMaxResults(page.getPageSize()).list();
		page.setResult(result);
		return page;
	}

	public Page<ViewAlarminfo> getAlarmByOrg(String orgId, String jgType,
			Page<ViewAlarminfo> page) {
		if (jgType.equals("3")) {
			page.getCriterions().add(Restrictions.eq("sfsid", orgId));
		} else if (jgType.equals("2")) {
			page.getCriterions().add(Restrictions.eq("qxsfjid", orgId));
		}
		page.getCriterions().add(Restrictions.eq("status", "2"));
		dao.findByCriteria(page);
		return page;
	}

	public int getCountByPerson(String personId) {
		String sqlCount = "select count(1) from VIEW_ALARMINFO Where  fxry_Id in (Select fxry_Id  From  CC_REPORT_ORG  Where CC_REPORT_ORG.person_Id=?) And status ='2' ";
		List count = dao.createSQLQuery(sqlCount, personId).list();
		return ((BigDecimal) count.get(0)).intValue();
	}

	public int getCountByJGID(String jgId, String jgType) {
		String sqlCount = "";
		if (jgType.equals("3")) {
			sqlCount = "select count(1) from VIEW_ALARMINFO where sfsid='"
					+ jgId + "' and status='2' ";
		} else if (jgType.equals("2")) {
			sqlCount = "select count(1) from VIEW_ALARMINFO where qxsfjid='"
					+ jgId + "' and status='2' ";
		} else {
			return -1;
		}
		List count = dao.createSQLQuery(sqlCount).list();
		return ((BigDecimal) count.get(0)).intValue();
	}

	public int getCountByJGIDAndTime(String jgId, String jgType) {
		Date currentDate = new Date();
		Date beforeDate = new Date(currentDate.getTime() - 1000 * 60 * 2);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
		String sqlCount = "";
		if (jgType.equals("3")) {
			sqlCount = "select count(1) from VIEW_ALARMINFO where alarm_time>to_date('"
					+ sdf.format(beforeDate)
					+ "','yyyy-MM-dd hh24:mi:ss') and sfsid='"
					+ jgId
					+ "' and status='2' ";
		} else if (jgType.equals("2")) {
			sqlCount = "select count(1) from VIEW_ALARMINFO where alarm_time>to_date('"
					+ sdf.format(beforeDate)
					+ "','yyyy-MM-dd hh24:mi:ss') and qxsfjid='"
					+ jgId
					+ "' and status='2' ";
		} else {
			return -1;
		}
		List count = dao.createSQLQuery(sqlCount).list();
		return ((BigDecimal) count.get(0)).intValue();
	}

	public List<ViewAlarminfo> findPaged(final Page<ViewAlarminfo> page,
			List<SearchRule> searchRules, List<String> columnNames) {
		return dao.findBySeachRule(page, searchRules, columnNames).getResult();
	}

	public List findPaged(List<SearchRule> searchRules, List<String> columnNames) {
		return dao.findBySpecialCriteria(columnNames, searchRules);
	}

}

package com.nci.sfj.business.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.base.dao.SimpleHibernateDao;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.data.model.CcGatherAlarmIgnore;

/**
 * Description:处理聚集报警排除的DAO层
 * 
 * @author Shuzz
 * 
 */
@Repository
public class GatherIgnoreDao extends HibernateDao<CcGatherAlarmIgnore, String> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @param fxryId
	 *            服刑人员主键
	 * @param orgIds
	 *            服刑人员所属机构以及其下属机构
	 * @param time
	 *            报警时间
	 * @return true可忽略;false不可忽略
	 */
	public boolean isGatherIngonre(String fxryId, List<String> orgIds, Date time) {
		// 报警时间段在聚集报警排除的时间段中
		Criterion c1 = Restrictions.le("startTime", time);
		Criterion c2 = Restrictions.ge("endTime", time);
		Criteria cc1 = getSession().createCriteria(entityClass);
		Criteria cc2 = getSession().createCriteria(entityClass);
		cc1.add(c2).add(c1);
		cc2.add(c2).add(c1);
		try {
			// 查询机构的聚集报警排除记录
			cc1.createAlias("ccIgnoreOrgs", "ccIgnoreOrgs",
					SimpleHibernateDao.JOINTYPE_LEFT_OUTER_JOIN);
			cc1.add(Restrictions.in("ccIgnoreOrgs.orgId", orgIds));
			// 查询服刑人员的聚集报警排除记录
			cc2.createAlias("ccIgnorePersons", "ccIgnorePersons",
					SimpleHibernateDao.JOINTYPE_LEFT_OUTER_JOIN);
			cc2.add(Restrictions.eq("ccIgnorePersons.personId", fxryId));
			List l = cc1.list();
			List ll = cc2.list();
			// 有记录返回ture该人员可忽略其聚集报警
			return CommonUtils.isNotNull(l) || CommonUtils.isNotNull(ll);
		} catch (Exception e) {
			logger.error(null, e);
		}
		return false;
	}
}

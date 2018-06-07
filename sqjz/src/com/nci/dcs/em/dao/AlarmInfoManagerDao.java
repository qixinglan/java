package com.nci.dcs.em.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.em.dwjk.model.ViewAlarminfo;

@Repository
@Transactional
public class AlarmInfoManagerDao extends HibernateDao<ViewAlarminfo, String> {
	@SuppressWarnings("rawtypes")
	public List findBySpecialCriteria(List<String> selectCols,
			List<SearchRule> searchRules) {
		try {
			Criteria criteria = organitizCriteria(searchRules, selectCols,
					null, null, false);
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

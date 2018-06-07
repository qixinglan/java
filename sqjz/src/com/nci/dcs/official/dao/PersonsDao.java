package com.nci.dcs.official.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.official.model.Persons;

@Repository
@Transactional
public class PersonsDao extends HibernateDao<Persons, String> {

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @since 2014年6月21日下午12:46:17
	 * @param selectCols
	 * @param searchRules
	 * @param otherOuters
	 * @return
	 */
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

	protected ProjectionList setProjectionList(List<String> projections) {
		ProjectionList proList = null;
		if (projections != null && projections.size() > 0) {
			proList = Projections.projectionList();
			for (String selectCol : projections) {
				proList.add(Projections.property(selectCol));
			}
			return proList;
		}
		return proList;
	}
	
	/**
	 * @name 查询下级机构人员信息
	 * @param orgId
	 * @return
	 * @author clj
	 * @date 2014年9月15日 上午9:34:15 
	 * @message：
	 */
	@SuppressWarnings("unchecked")
	public List<Persons> getPersonsBySupOrg(String orgId,String orgType, String duty){
		if(duty==null || ("").equals(duty)){
			String sql = "from Persons  where org.supOrg.orgId = ? and org.orgType = ?";
			return createQuery(sql, orgId, orgType).list();
		}else{
			String sql = "from Persons  where duty=? and org.supOrg.orgId = ? and org.orgType = ?";
			return createQuery(sql, duty, orgId, orgType).list();
		}
	}
}

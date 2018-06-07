package com.nci.dcs.official.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.official.model.OrganizationInfo;

@Repository
@Transactional
public class OrganizationInfoDao extends HibernateDao<OrganizationInfo, String> {
	public OrganizationInfo findRoot() {
		return (OrganizationInfo) this
				.findOne("from OrganizationInfo where supOrg is null");
	}

	public List<?> getChildrenIds(String id) {
		String sql = "select t.org_id from CC_ORGANIZATION_INFO t start with t.org_id=?"
				+ "connect by prior t.org_id=t.sup_org_id";
		return createSQLQuery(sql, id).list();
	}
	
	/**
	 * @name 获取本级、下一级机关代码
	 * @param id
	 * @return
	 * @author clj
	 * @date 2014年8月29日 上午11:24:02 
	 * @message：
	 */
	public List<?> getBxyjOrgIds(String id) {
		String sql = "select t.org_id from CC_ORGANIZATION_INFO t where t.org_id=? or t.sup_org_id=?";
		return createSQLQuery(sql, id, id).list();
	}
}

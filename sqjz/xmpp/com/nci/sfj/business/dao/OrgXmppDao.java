package com.nci.sfj.business.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.sfj.business.model.OrgXmpp;

/**
 * Description:XMPP通信模块中处理机构信息的DAO层
 * 
 * @author Shuzz
 * 
 */
@Repository
@Transactional
public class OrgXmppDao extends HibernateDao<OrgXmpp, String> {

	/**
	 * Description:XMPP通信模块中查询机构根节点ID
	 * 
	 * @author Shuzz
	 * @return 机构根节点主键
	 */
	public String getRootOrgid() {
		return ((OrgXmpp) this.findOne("from OrgXmpp where supOrg is null"))
				.getOrgId();
	}
}

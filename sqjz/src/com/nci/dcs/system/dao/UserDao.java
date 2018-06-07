package com.nci.dcs.system.dao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.system.model.User;

@Repository
@Transactional
public class UserDao extends HibernateDao<User, String> {
	public Page<User> findForRole(final Page<User> page, Criteria criteria) {
		return this.findByCriteria(criteria, page);
	}

}

package com.nci.dcs.system.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.system.model.Role;

@Repository
@Transactional
public class RoleDao extends HibernateDao<Role, Integer>{

}

package com.nci.dcs.supervision.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.supervision.model.SupervisionModule;

@Repository
@Transactional
public class SupervisionModuleDao extends
		HibernateDao<SupervisionModule, String> {
}

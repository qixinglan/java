package com.nci.dcs.em.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.em.model.CcLawEnforcement;

@Repository
@Transactional
public class LawEnforcementManagerDao extends HibernateDao<CcLawEnforcement, String>{

}

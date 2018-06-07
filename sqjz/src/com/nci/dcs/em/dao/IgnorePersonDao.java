package com.nci.dcs.em.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.data.model.CcIgnorePerson;

@Repository
@Transactional
public class IgnorePersonDao extends HibernateDao<CcIgnorePerson, String> {

}

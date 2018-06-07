package com.nci.dcs.official.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.official.model.CcDynamicreportreply;

@Repository
@Transactional
public class DynamicreportreplyDao extends HibernateDao<CcDynamicreportreply, String> {
}

package com.nci.dcs.system.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.system.model.Authorization;
import com.nci.dcs.system.model.DictionaryDetail;

@Repository
@Transactional
public class DictionaryDetailDao extends HibernateDao<DictionaryDetail, String> {

}

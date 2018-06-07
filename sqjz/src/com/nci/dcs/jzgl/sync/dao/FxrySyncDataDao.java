package com.nci.dcs.jzgl.sync.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.jzgl.sync.model.FxrySyncData;

@Repository
@Transactional
public class FxrySyncDataDao extends HibernateDao<FxrySyncData, String> {
}

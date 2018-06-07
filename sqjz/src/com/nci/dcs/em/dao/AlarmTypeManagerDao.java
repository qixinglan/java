package com.nci.dcs.em.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.em.model.CcAlarmType;
import com.nci.dcs.em.model.CcSuperviseDevice;

@Repository
@Transactional
public class AlarmTypeManagerDao extends HibernateDao<CcAlarmType, String>{

}

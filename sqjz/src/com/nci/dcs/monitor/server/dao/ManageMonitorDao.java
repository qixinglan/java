package com.nci.dcs.monitor.server.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.monitor.server.model.ManageMonitor;

@Repository
@Transactional
public class ManageMonitorDao extends HibernateDao<ManageMonitor, String>{

}

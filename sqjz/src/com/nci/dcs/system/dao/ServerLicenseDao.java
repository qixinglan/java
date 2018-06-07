package com.nci.dcs.system.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.system.model.ServerLicense;

@Repository
@Transactional
public class ServerLicenseDao extends HibernateDao<ServerLicense, Integer>{
	
}

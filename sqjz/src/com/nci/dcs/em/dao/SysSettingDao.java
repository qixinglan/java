package com.nci.dcs.em.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.em.model.SysSetting;

@Repository
@Transactional
public class SysSettingDao extends HibernateDao<SysSetting, String> {
	
}

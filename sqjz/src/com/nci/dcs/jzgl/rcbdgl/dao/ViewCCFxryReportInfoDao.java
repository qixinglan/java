package com.nci.dcs.jzgl.rcbdgl.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.jzgl.rcbdgl.model.ViewCCFxryReportinfo;

@Repository
@Transactional
public class ViewCCFxryReportInfoDao extends
		HibernateDao<ViewCCFxryReportinfo, String> {

}

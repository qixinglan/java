package com.nci.dcs.homepage.report.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.homepage.report.model.ReportModule;

@Repository
@Transactional
public class ReportModuleDao extends HibernateDao<ReportModule, String> {

}

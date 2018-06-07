package com.nci.dcs.jzgl.rcbdgl.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.jzgl.rcbdgl.model.ViewFxryReportinfo;

@Repository
@Transactional
public class ReportListDao extends
             HibernateDao<ViewFxryReportinfo, String> {
}

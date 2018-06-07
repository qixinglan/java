package com.nci.dcs.jzgl.cxtj.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.jzgl.cxtj.model.ViewCcFxryAllinfoSearch;
import com.nci.dcs.jzgl.cxtj.model.ViewReportFxryInfo;

@Repository
@Transactional
public class ViewReportFxryInfoDao extends HibernateDao<ViewReportFxryInfo, String> {

}

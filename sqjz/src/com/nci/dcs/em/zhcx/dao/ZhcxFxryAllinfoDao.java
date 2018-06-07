package com.nci.dcs.em.zhcx.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.em.zhcx.model.ViewCcFxryAllinfo;

@Repository
@Transactional
public class ZhcxFxryAllinfoDao extends HibernateDao<ViewCcFxryAllinfo, String>{

}

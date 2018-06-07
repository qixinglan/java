package com.nci.dcs.em.zhcx.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.em.zhcx.model.ViewCcFxryBaseinfo;

@Repository
@Transactional
public class ZhcxFxryMessageDao extends HibernateDao<ViewCcFxryBaseinfo, String>{

}

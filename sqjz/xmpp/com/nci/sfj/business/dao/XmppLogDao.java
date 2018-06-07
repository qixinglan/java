package com.nci.sfj.business.dao;

import org.springframework.stereotype.Repository;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.sfj.business.model.XmppLog;

@Repository
public class XmppLogDao extends HibernateDao<XmppLog, String> {

}

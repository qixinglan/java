package com.nci.sfj.business.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.sfj.business.model.MsgCrossBorderXmpp;

@Repository
@Transactional
public class MsgCrossBorderXmppDao extends
		HibernateDao<MsgCrossBorderXmpp, String> {
}

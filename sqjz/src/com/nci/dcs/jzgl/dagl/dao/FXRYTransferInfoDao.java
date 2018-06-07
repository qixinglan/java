package com.nci.dcs.jzgl.dagl.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.jzgl.dagl.model.FXRYTransferInfo;

@Repository
@Transactional
public class FXRYTransferInfoDao extends HibernateDao<FXRYTransferInfo, String> {

}

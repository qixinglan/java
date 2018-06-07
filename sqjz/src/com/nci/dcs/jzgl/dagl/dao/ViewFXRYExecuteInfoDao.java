package com.nci.dcs.jzgl.dagl.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.jzgl.dagl.model.ViewFXRYExecuteInfo;

@Repository
@Transactional
public class ViewFXRYExecuteInfoDao extends HibernateDao<ViewFXRYExecuteInfo, String> {

}

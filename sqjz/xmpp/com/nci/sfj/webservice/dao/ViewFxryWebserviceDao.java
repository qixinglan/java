package com.nci.sfj.webservice.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.sfj.webservice.model.ViewFxryWebservice;

@Repository
@Transactional
public class ViewFxryWebserviceDao extends HibernateDao<ViewFxryWebservice, String>{

}

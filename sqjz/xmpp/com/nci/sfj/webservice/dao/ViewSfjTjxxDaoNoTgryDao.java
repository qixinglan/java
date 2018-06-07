package com.nci.sfj.webservice.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.sfj.webservice.model.ViewSfjTjxx;

@Repository
@Transactional
public class ViewSfjTjxxDaoNoTgryDao extends HibernateDao<ViewSfjTjxx, String>{

}

package com.nci.sfj.webservice.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.sfj.webservice.model.ViewQxsfjTjxx;

@Repository
@Transactional
public class ViewQxsfjTjxxDao extends HibernateDao<ViewQxsfjTjxx, String> {

}

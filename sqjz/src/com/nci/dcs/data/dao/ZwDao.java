package com.nci.dcs.data.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.data.model.Zw;

@Repository
@Transactional
public class ZwDao extends HibernateDao<Zw, String>{

}

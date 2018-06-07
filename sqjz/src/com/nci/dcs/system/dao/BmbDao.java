package com.nci.dcs.system.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.system.model.Bmb;

@Repository
@Transactional
public class BmbDao extends HibernateDao<Bmb, String>{

}

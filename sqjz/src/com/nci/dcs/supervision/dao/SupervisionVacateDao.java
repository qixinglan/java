package com.nci.dcs.supervision.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.supervision.model.SupervisionVacate;

@Repository
@Transactional
public class SupervisionVacateDao extends HibernateDao<SupervisionVacate, String> {
}

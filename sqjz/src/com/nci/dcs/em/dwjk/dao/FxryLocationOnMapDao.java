package com.nci.dcs.em.dwjk.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.em.dwjk.model.ViewFxryOnMap;

@Repository
@Transactional
public class FxryLocationOnMapDao extends HibernateDao<ViewFxryOnMap,String>{
}

package com.nci.dcs.em.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.em.model.ViewFxrySetting;

@Repository
@Transactional
public class FxrySettingDao extends HibernateDao<ViewFxrySetting,String>{

}

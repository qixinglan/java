package com.nci.dcs.jzgl.education.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.jzgl.education.model.EducationInfo;

@Repository
@Transactional
public class EducationInfoDao extends HibernateDao<EducationInfo, String>{

}

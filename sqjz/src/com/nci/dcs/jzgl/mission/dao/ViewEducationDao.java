package com.nci.dcs.jzgl.mission.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.jzgl.mission.model.ViewEducation;

@Repository
@Transactional
public class ViewEducationDao extends HibernateDao<ViewEducation, String> {

}

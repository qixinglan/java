package com.nci.dcs.jzgl.mission.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.jzgl.mission.model.ViewInterview;

@Repository
@Transactional
public class ViewInterviewDao extends HibernateDao<ViewInterview, String> {

}

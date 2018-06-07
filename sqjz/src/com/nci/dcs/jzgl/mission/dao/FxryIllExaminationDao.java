package com.nci.dcs.jzgl.mission.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.jzgl.mission.model.ViewIllExamination;
@Repository
@Transactional
public class FxryIllExaminationDao extends HibernateDao<ViewIllExamination,String> {

}

package com.nci.dcs.official.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.official.model.CcWeeknotice;


@Repository
@Transactional
public class WeekNoticeDao extends HibernateDao<CcWeeknotice, String> {

}

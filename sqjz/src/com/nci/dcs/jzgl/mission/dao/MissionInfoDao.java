package com.nci.dcs.jzgl.mission.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.jzgl.mission.model.MissionInfo;

@Repository
@Transactional
public class MissionInfoDao extends HibernateDao<MissionInfo, String> {
}

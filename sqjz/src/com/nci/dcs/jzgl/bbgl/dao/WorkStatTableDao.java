package com.nci.dcs.jzgl.bbgl.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.jzgl.bbgl.model.CcWorkStatisticTable;

@Repository
@Transactional
public class WorkStatTableDao extends HibernateDao<CcWorkStatisticTable, String> {

}

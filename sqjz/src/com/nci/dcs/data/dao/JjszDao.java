package com.nci.dcs.data.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.data.model.CcGatherAlarmIgnore;
@Repository
@Transactional
public class JjszDao extends HibernateDao<CcGatherAlarmIgnore, String>{

}

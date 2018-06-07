package com.nci.dcs.jzgl.fingerprint.dao;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.jzgl.fingerprint.model.CcFingerprintMachine;

@Repository
@Transactional
public class FingerPrintManagerDao extends HibernateDao<CcFingerprintMachine, String>{
	public int getOrderCode(){
		try {
			String sql = "select seq_fingerPersonId.nextval nextvalue from dual";
			Integer maxId = (Integer)(getSession().createSQLQuery(sql)
					.addScalar("nextvalue",Hibernate.INTEGER)).uniqueResult();
			return maxId;
		}catch(Exception e){
			
		}
		return 0;
	}
}

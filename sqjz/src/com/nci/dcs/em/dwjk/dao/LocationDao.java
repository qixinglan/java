package com.nci.dcs.em.dwjk.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.em.dwjk.model.CcLocationInfo;

@Repository
@Transactional
public class LocationDao extends HibernateDao<CcLocationInfo, String> {

	public List<CcLocationInfo> findHistory(String fxryId,Object... obj) throws ParseException {
		Criteria criteria = getSession().createCriteria(entityClass);
		Date d = new Date();
		if(obj.length<1){
			Date date = new Date();
			criteria.add(Restrictions.ge("locTime", new Date(date.getTime()-7*24*60*60*1000)));
			criteria.add(Restrictions.le("locTime", date));
		}else{
			criteria.add(Restrictions.ge("locTime", new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(obj[0].toString())));
			criteria.add(Restrictions.le("locTime", new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(obj[1].toString())));
		}
		
		criteria.add(Restrictions.eq("fxryId", fxryId));
		
		criteria.addOrder(Order.desc("locTime"));
		return criteria.list();
	}
}

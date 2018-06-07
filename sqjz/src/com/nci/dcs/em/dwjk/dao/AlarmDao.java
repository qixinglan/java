package com.nci.dcs.em.dwjk.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.em.dwjk.model.CcAlarmInfo;

@Repository
@Transactional
public class AlarmDao extends HibernateDao<CcAlarmInfo,String>{
	public List<CcAlarmInfo> getAlarmByFxryId(String fxryId,Object... obj) throws ParseException {
		Criteria criteria = getSession().createCriteria(entityClass);
		if(obj.length<1){
			Date date = new Date();
			criteria.add(Restrictions.ge("alarmTime", new Date(date.getTime()-24*60*60*1000)));
			criteria.add(Restrictions.le("alarmTime", date));
		}else{
			criteria.add(Restrictions.ge("alarmTime", new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(obj[0].toString())));
			criteria.add(Restrictions.le("alarmTime", new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(obj[1].toString())));
		}
		
		criteria.add(Restrictions.eq("fxryId", fxryId));
		
		criteria.addOrder(Order.desc("alarmTime"));
		return criteria.list();
	}
	
	public List<CcAlarmInfo> getUntreatAlarmByFxryId(String fxryId,Object... obj) throws ParseException {
		Criteria criteria = getSession().createCriteria(entityClass);
		
		criteria.add(Restrictions.eq("fxryId", fxryId));
		criteria.add(Restrictions.eq("status", "2"));
		if(obj.length>0){
			
		}
		return criteria.list();
	}
}

package com.nci.dcs.jzgl.mission.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.model.FXRYExecuteInfo;
import com.nci.dcs.jzgl.dagl.service.FXRYExecuteInfoService;
import com.nci.dcs.jzgl.mission.dao.MissionInfoDao;
import com.nci.dcs.jzgl.mission.model.MissionInfo;
import com.nci.dcs.system.model.User;

@Service
@Transactional
public class MissionInfoService extends BaseService<MissionInfo, String> {
	@Autowired
	private MissionInfoDao missionInfoDao;
	@Autowired
	private FXRYExecuteInfoService executeService;

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @param calendar
	 * @since 2015年3月26日下午3:17:51
	 */
	private void clearHours(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @param date
	 * @return
	 * @since 2015年3月26日下午3:17:53
	 */
	private Date getMonday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		clearHours(calendar);
		if (date.before(calendar.getTime())) {
			calendar.add(Calendar.DAY_OF_YEAR, -7);
		}
		return calendar.getTime();
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @param date
	 * @return
	 * @since 2015年3月26日下午3:17:56
	 */
	private Date getSunday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		if (calendar.getTime().before(date)) {
			calendar.add(Calendar.DAY_OF_YEAR, 7);
		}
		return calendar.getTime();
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @param date
	 * @return
	 * @since 2015年3月26日下午3:17:58
	 */
	public Date getFirstDayForMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		clearHours(calendar);
		return calendar.getTime();
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @param date
	 * @return
	 * @since 2015年3月26日下午3:18:01
	 */
	public Date getLastDayForMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	
	private Date getLastDayForThirdMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		calendar.add(Calendar.MONTH, 3);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @param fxryId
	 * @param user
	 * @since 2015年3月26日下午3:18:04
	 */
	public void createPhoneReport(String fxryId, User user, Date now, String bz) {
		MissionInfo missionInfo = new MissionInfo();
		missionInfo.setMissionType("1");
		missionInfo.setFxryId(fxryId);
		if (user != null) {
			missionInfo.setPersonId(user.getId());
			missionInfo.setPersonName(user.getName());
		}
		if (!CommonUtils.isNull(bz)) {
			missionInfo.setBz(bz);
		}
//		Date now = new Date();
		missionInfo.setAccomplishDate(now);
		missionInfo.setMissionStart(getMonday(now));
		missionInfo.setMissionEnd(getSunday(now));
		missionInfo.setCreateDate(new Date());
		create(missionInfo);
	}
	
	public void createPhoneReport(String fxryId, User user) {
		MissionInfo missionInfo = new MissionInfo();
		missionInfo.setMissionType("1");
		missionInfo.setFxryId(fxryId);
		if (user != null) {
			missionInfo.setPersonId(user.getId());
			missionInfo.setPersonName(user.getName());
		}
		Date now = new Date();
		missionInfo.setAccomplishDate(now);
		missionInfo.setMissionStart(getMonday(now));
		missionInfo.setMissionEnd(getSunday(now));
		create(missionInfo);
	}


	public void createIllExamination(String fxryId, User user, Date accDate, String bz) {
		MissionInfo missionInfo = new MissionInfo();
		missionInfo.setMissionType("5");
		missionInfo.setFxryId(fxryId);
		if (user != null) {
			missionInfo.setPersonId(user.getId());
			missionInfo.setPersonName(user.getName());
		}
		if (!CommonUtils.isNull(bz)) {
			missionInfo.setBz(bz);
		}
		Date now = getIllExaminationStartTime(fxryId);
		missionInfo.setAccomplishDate(accDate);
		missionInfo.setMissionStart(now);
		missionInfo.setMissionEnd(getLastDayForThirdMonth(now));
		missionInfo.setCreateDate(new Date());
		create(missionInfo);
	}
	
	public void createIllExamination(String fxryId, User user) {
		MissionInfo missionInfo = new MissionInfo();
		missionInfo.setMissionType("5");
		missionInfo.setFxryId(fxryId);
		if (user != null) {
			missionInfo.setPersonId(user.getId());
			missionInfo.setPersonName(user.getName());
		}
		Date now = getIllExaminationStartTime(fxryId);
		missionInfo.setAccomplishDate(new Date());
		missionInfo.setMissionStart(now);
		missionInfo.setMissionEnd(getLastDayForThirdMonth(now));
		create(missionInfo);
	}
	
	private Date getIllExaminationStartTime(String fxryId){
		Criteria criteria = missionInfoDao.createCriteria();
		criteria.addOrder(Order.desc("missionEnd"));
		criteria.add(Restrictions.eq("fxryId", fxryId));
		criteria.add(Restrictions.eq("missionType", "5"));
		List<MissionInfo> missionInfos = criteria.list();
		Calendar c = Calendar.getInstance();
		if(missionInfos.size()==0){
			FXRYExecuteInfo exeInfo = executeService.getByFXRYId(fxryId);
			Date receive=exeInfo.getDateReceive();
			c.setTime(receive);
			Date now=new Date();
			while (c.getTime().before(now)){
				c.add(Calendar.MONTH, 3);
			}
			c.add(Calendar.MONTH, -3);
			if(c.getTime().before(receive)){
				c.setTime(receive);
			}
			clearHours(c);
			return c.getTime();
		}else{
			c.setTime(missionInfos.get(0).getMissionEnd());
			c.add(Calendar.DAY_OF_YEAR, 1);
			clearHours(c);
			return c.getTime();
		}
	}
	
	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @param fxryId
	 * @param user
	 * @since 2015年3月26日下午3:18:21
	 */
	public void createPhoneReport(String[] fxryIds, User user, Date now, String bz) {
		for (String fxryId : fxryIds) {
			createPhoneReport(fxryId, user, now, bz);
		}
	}
	
	public void createPhoneReport(String[] fxryIds, User user) {
		for (String fxryId : fxryIds) {
			createPhoneReport(fxryId, user);
		}
	}
	
	public void createIllExamination(String[] fxryIds, User user, Date accDate, String bz) {
		for (String fxryId : fxryIds) {
			createIllExamination(fxryId, user, accDate, bz);
		}
	}
	
	public void createIllExamination(String[] fxryIds, User user) {
		for (String fxryId : fxryIds) {
			createIllExamination(fxryId, user);
		}
	}

	private void createBase(String fxryId, User user, String type) {
		MissionInfo missionInfo = new MissionInfo();
		missionInfo.setMissionType(type);
		missionInfo.setFxryId(fxryId);
		if (user != null) {
			missionInfo.setPersonId(user.getId());
			missionInfo.setPersonName(user.getName());
		}
		Date now = new Date();
		missionInfo.setAccomplishDate(now);
		missionInfo.setMissionStart(getFirstDayForMonth(now));
		missionInfo.setMissionEnd(getLastDayForMonth(now));
		create(missionInfo);
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @param fxryId
	 * @param user
	 * @since 2015年3月26日下午3:18:06
	 */
	public void createInterview(String fxryId, User user) {
		createBase(fxryId, user, "2");
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @param fxryIds
	 * @param user
	 * @since 2015年3月26日下午4:27:38
	 */
	public void createInterview(String[] fxryIds, User user) {
		for (String fxryId : fxryIds) {
			createInterview(fxryId, user);
		}
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @param fxryId
	 * @param user
	 * @since 2015年3月26日下午4:45:12
	 */
	public void createEducation(String fxryId, User user) {
		createBase(fxryId, user, "3");
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @param fxryIds
	 * @param user
	 * @since 2015年3月26日下午4:45:17
	 */
	public void createEducation(String[] fxryIds, User user) {
		for (String fxryId : fxryIds) {
			createEducation(fxryId, user);
		}
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @param fxryId
	 * @param user
	 * @since 2015年3月26日下午4:45:12
	 */
	public void createPublicWork(String fxryId, User user, Date now, String bz, String sfcy) {
//		createBase(fxryId, user, "4");
		MissionInfo missionInfo = new MissionInfo();
		missionInfo.setMissionType("4");
		missionInfo.setFxryId(fxryId);
		if (user != null) {
			missionInfo.setPersonId(user.getId());
			missionInfo.setPersonName(user.getName());
		}
		if (!CommonUtils.isNull(bz)) {
			missionInfo.setBz(bz);
		}
		missionInfo.setAccomplishDate(now);
		missionInfo.setMissionStart(getFirstDayForMonth(now));
		missionInfo.setMissionEnd(getLastDayForMonth(now));
		missionInfo.setSfcy(sfcy);
		missionInfo.setCreateDate(new Date());
		create(missionInfo);
	}
	
	
	public void createPublicWork(String fxryId, User user) {
		createBase(fxryId, user, "4");
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @param fxryIds
	 * @param user
	 * @since 2015年3月26日下午4:45:17
	 */
	public void createPublicWork(String[] fxryIds, User user, Date now, String bz, String sfcy) {
		for (String fxryId : fxryIds) {
			createPublicWork(fxryId, user, now, bz,sfcy);
		}
	}
	
	public void createPublicWork(String[] fxryIds, User user) {
		for (String fxryId : fxryIds) {
			createPublicWork(fxryId, user);
		}
	}

	@Override
	public void create(MissionInfo entity) {
		missionInfoDao.save(entity);
	}

	public Page<MissionInfo> findPaged(Page<MissionInfo> page) {
		return missionInfoDao.findByCriteria(page);
	}

	@Override
	public void update(MissionInfo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public MissionInfo get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MissionInfo> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<MissionInfo> findPaged(Page<MissionInfo> page,
			MissionInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enable(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void disable(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void audit(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<MissionInfo> findPaged(Page<MissionInfo> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(MissionInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

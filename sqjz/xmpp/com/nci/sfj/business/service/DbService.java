package com.nci.sfj.business.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.beanutils.PropertyUtils;
import org.dom4j.Element;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xmpp.packet.IQ;

import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.em.dao.DevicePairDao;
import com.nci.dcs.em.dao.EmSuperviseDeviceDao;
import com.nci.dcs.em.dao.LocationPeriodDao;
import com.nci.dcs.em.model.CcDevicePair;
import com.nci.dcs.em.model.CcSuperviseDevice;
import com.nci.dcs.em.model.LocationPeriod;
import com.nci.dcs.em.model.SysSetting;
import com.nci.dcs.em.service.SysSettingService;
import com.nci.dcs.jzgl.dagl.dao.FXRYReportOrgDao;
import com.nci.dcs.jzgl.dagl.model.FXRYReportOrg;
import com.nci.dcs.official.dao.PersonsDao;
import com.nci.dcs.official.model.Persons;
import com.nci.sfj.business.dao.AlarmTypeXmppDao;
import com.nci.sfj.business.dao.AlarmXmppDao;
import com.nci.sfj.business.dao.BaseStationDao;
import com.nci.sfj.business.dao.FxryXmppDao;
import com.nci.sfj.business.dao.GatherIgnoreDao;
import com.nci.sfj.business.dao.LocationBufferXmppDao;
import com.nci.sfj.business.dao.LocationXmppDao;
import com.nci.sfj.business.dao.MsgCrossBorderXmppDao;
import com.nci.sfj.business.dao.MsgDisappearXmppDao;
import com.nci.sfj.business.dao.MsgGatherXmppDao;
import com.nci.sfj.business.dao.MsgOutrangeXmppDao;
import com.nci.sfj.business.dao.MsgWaitXmppDao;
import com.nci.sfj.business.dao.OrgXmppDao;
import com.nci.dcs.em.dao.DzjgsbDeviceDao;
import com.nci.sfj.business.dao.UserXmppDao;
import com.nci.sfj.business.dao.XmppDeviceInfoDao;
import com.nci.sfj.business.dao.XmppDeviceStatusDao;
import com.nci.sfj.business.dao.XmppLbsReqDao;
import com.nci.sfj.business.dao.XmppLogDao;
import com.nci.sfj.business.model.AlarmTypeXmpp;
import com.nci.sfj.business.model.AlarmXmpp;
import com.nci.sfj.business.model.BaseStation;
import com.nci.sfj.business.model.FxryXmpp;
import com.nci.sfj.business.model.LocationBufferXmpp;
import com.nci.sfj.business.model.LocationXmpp;
import com.nci.sfj.business.model.MsgCrossBorderXmpp;
import com.nci.sfj.business.model.MsgDisappearXmpp;
import com.nci.sfj.business.model.MsgGatherXmpp;
import com.nci.sfj.business.model.MsgOutrangeXmpp;
import com.nci.sfj.business.model.MsgWaitXmpp;
import com.nci.sfj.business.model.OrgXmpp;
import com.nci.dcs.em.model.CcDzjgsbDevice;
import com.nci.sfj.business.model.UserXmpp;
import com.nci.sfj.business.model.XmppDeviceInfo;
import com.nci.sfj.business.model.XmppDeviceStatus;
//import com.nci.sfj.business.model.XmppJustice;     //different from online
import com.nci.sfj.business.model.XmppLbsReq;
import com.nci.sfj.business.model.XmppLog;
import com.nci.sfj.common.util.Config;
import com.nci.sfj.common.util.DateFormat;
import com.nci.sfj.xmpp.auth.AuthToken;

/**
 * Description:控制数据库操作
 * 
 * @author Shuzz
 * @since 2014年6月10日下午4:11:35
 */
@Service
@Transactional
public class DbService {
	@Autowired
	private DzjgsbDeviceDao dzjgsbDeviceDao;
	@Autowired
	private FxryXmppDao fxryXmppDao;
	@Autowired
	private UserXmppDao userXmppDao;
	@Autowired
	private LocationXmppDao locationXmppDao;
	@Autowired
	private AlarmTypeXmppDao alarmTypeXmppDao;
	@Autowired
	private AlarmXmppDao alarmXmppDao;
	@Autowired
	private MsgWaitXmppDao msgWaitXmppDao;
	@Autowired
	private LocationPeriodDao locationPeriodDao;
	@Autowired
	private GatherIgnoreDao gatherIgnoreDao;
	@Autowired
	private MsgOutrangeXmppDao msgOutrangeXmppDao;
	@Autowired
	private OrgXmppDao orgXmppDao;
	@Autowired
	private EmSuperviseDeviceDao emSuperviseDeviceDao;
	@Autowired
	private MsgGatherXmppDao gatherXmppDao;
	@Autowired
	private PersonsDao personsDao;
	@Autowired
	private MsgCrossBorderXmppDao msgCrossBorderXmppDao;
	@Autowired
	private SysSettingService sysSettingService;
	@Autowired
	private XmppLogDao xmppLogDao;
	@Autowired
	private XmppDeviceInfoDao xmppDeviceInfoDao;
	@Autowired
	private DevicePairDao devicePairDao;
	@Autowired
	private BaseStationDao baseStationDao;
	@Autowired
	private XmppLbsReqDao xmppLbsReqDao;
	@Autowired
	private MsgDisappearXmppDao msgDisappearXmppDao;
	@Autowired
	private FXRYReportOrgDao fxryReportOrgDao;
	@Autowired
	private XmppDeviceStatusDao xmppDeviceStatusDao;
	//different from online
	//@Autowired
	//private XmppJusticeDao xmppJusticeDao;
	//add by yang on 2016/6/14
	@Autowired
	private LocationBufferXmppDao locationBufferXmppDao;

	private String rootOrgId = "1";

	private Logger log = LoggerFactory.getLogger(getClass());

	@PostConstruct
	private void getOrgRoot() {
		List<OrgXmpp> orgs = orgXmppDao.findByCriteria(Restrictions
				.isNull("supOrg"));
		if (CommonUtils.isNotNull(orgs)) {
			rootOrgId = orgs.get(0).getOrgId();
		}
	}

	public String getRootOrgId() {
		return rootOrgId;
	}

	/**
	 * Description:根据定位主机编号获取设备信息
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日上午11:13:18
	 * @param number
	 * @return
	 */
	public CcDzjgsbDevice findSuperviseDeviceByNumber(String number) {
		List<CcDzjgsbDevice> devices = dzjgsbDeviceDao.findByCriteria(
				Restrictions.eq("deviceNumber", number));
		if (CommonUtils.isNotNull(devices)) {
			return devices.get(0);
		}
		return null;
	}

	/**
	 * Description:根据用户名称获取用户信息
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日上午11:13:18
	 * @param number
	 * @return
	 */
	public UserXmpp findUserByName(String userName) {
		return userXmppDao.findUniqueByProperty("userName", userName);
	}

	/**
	 * Description:根据报警类型获取报警类型信息
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日上午11:13:18
	 * @param number
	 * @return
	 */
	public AlarmTypeXmpp findAlramTypeByType(String type) {
		return alarmTypeXmppDao.findOneByProperty("alarmType", type);
	}

	/**
	 * Description:根据服刑人员ID获取其矫正类型
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日上午11:11:15
	 * @param fxryId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String findAdjystType(String fxryId) {
		String sql = "select e.adjust_type from cc_fxry_baseinfo b "
				+ "left join CC_EXECUTE_INFO e on b.id=e.fxry_id "
				+ "where b.id=?";
		String result = "";
		List aType = alarmTypeXmppDao.createSQLQuery(sql, fxryId).list();
		if (CommonUtils.isNotNull(aType)) {
			if (aType.get(0) != null)
				result = aType.get(0).toString();
		}
		return result;
	}

	/**
	 * Description:根据人员id查找离指定时间几分钟内未处理的一条指定类型的报警
	 * 
	 * @author Shuzz
	 * @param fxryId
	 *            服刑人员id
	 * @param alarmType
	 *            报警类型
	 * @param time
	 *            指定时间
	 * @param minute
	 *            偏移分钟
	 * @return
	 */
	public AlarmXmpp findAlarm(String fxryId, String alarmType, Date time,
			int minute) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(Calendar.MINUTE, minute);
		return alarmXmppDao.findOneByCriteria(
				Restrictions.eq("fxryId", fxryId),
				Restrictions.eq("alarmType", alarmType),
				Restrictions.eq("status", "2"),
				Restrictions.ge("alarmTime", c.getTime()));
	}

	/**
	 * Description:根据机构获取该机构下的定位主机编号，用于向该主机发送配置信息
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日下午2:40:30
	 * @param orgId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> findSuperviseDeviceByOrg(String orgId,
			List<String> notOrgId) {
		String sql = "select DEVICE_NUMBER from CC_DZJGSB_DEVICE sd where";
		if (!rootOrgId.equals(orgId)) {
			// sql += " and org_id='" + orgId + "'";
			sql += " BUY_UNIT='" + orgId + "'";
		}
		if (CommonUtils.isNotNull(notOrgId)) {
			// sql += " and org_id not in (";
			sql += " BUY_UNIT not in (";
			for (String id : notOrgId) {
				sql += id + ", ";
			}
			sql = sql.substring(0, sql.length() - 2);
			sql += ") ";
		}
		return dzjgsbDeviceDao.createSQLQuery(sql).list();
	}

	/**
	 * Description:根据用户名授权
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日上午11:15:31
	 * @param userName
	 *            用户名
	 * @return
	 */
	public AuthToken findUserByUserName(String userName) {
		AuthToken token = null;
		UserXmpp user = findUserByName(userName);
		String userId = null;
		String orgId = null;
		if (user != null) {
			userId = user.getId();
			orgId = user.getWunit().getOrgId();
		}
		token = new AuthToken(orgId, userId, null, null, null, userName,
				new Date());
		return token;
	}

	/**
	 * Description:根据设备编号授权
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日上午11:15:43
	 * @param number
	 *            设备编号
	 * @return
	 */
	public AuthToken findFxryByDevice(String number) {
		AuthToken token = null;
		CcDzjgsbDevice device = findSuperviseDeviceByNumber(number);
		if (device != null) {
			FxryXmpp fxry = fxryXmppDao.findUniqueByProperty("deviceNumber",
					number);
			String fxryId = null;
			String orgId = null;
			if (fxry != null) {
				fxryId = fxry.getId();
				orgId = fxry.getOrg();
			}
			token = new AuthToken(orgId, null, fxryId, device.getId(),
					number, null, new Date());
		}
		return token;
	}

	/**
	 * Description:根据主机的电话号码,查找其对应的人员,用于主动定位
	 * 
	 * @author Shuzz
	 * @since 2014年9月12日下午2:23:14
	 * @param phone
	 *            主机电话号码
	 * @return
	 */
	public AuthToken findFxryByDevicePhone(String phone) {
		AuthToken token = null;
		// 首先查询出对应的设备
		List<CcDzjgsbDevice> devices = dzjgsbDeviceDao.findByCriteria(
				Restrictions.eq("phoneNumber", phone));
		CcDzjgsbDevice device = null;
		// 测试情况下可能有多个设备,出现差错的情况
		if (CommonUtils.isNotNull(devices)) {
			device = devices.get(0);
		}
		if (device != null) {
			FxryXmpp fxry = fxryXmppDao.findUniqueByProperty("deviceNumber",
					device.getDeviceNumber());
			String fxryId = null;
			String orgId = null;
			if (fxry != null) {
				fxryId = fxry.getId();
				orgId = fxry.getOrg();
			}
			token = new AuthToken(orgId, null, fxryId, device.getId(),
					device.getDeviceNumber(), null, new Date());
		}
		return token;
	}

	/**
	 * Description:计算是否重复定位，只有时间相同x,y误差较小才认为相同
	 * 
	 * @author Shuzz
	 * @since 2014年8月28日下午3:09:38
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param time
	 *            定位时间
	 * @return
	 */
	public boolean hasSameLocation(double x, double y, Date time) {
		//different from online
		//begin
		/*
		LocationXmpp loc = locationXmppDao.findOneByProperty("locTime", time);
		*/
		String locTime = DateFormat.tranDateToStr(time);
		LocationXmpp loc = (LocationXmpp) locationXmppDao.findOne("from LocationXmpp where locTime=to_date('"+ locTime +"','yyyy-mm-dd hh24:mi:ss')");
		//end
		return sameLoc(x, y, loc);
	}

	/**
	 * Description:判断是否相同的坐标
	 * 
	 * @author Shuzz
	 * @param x
	 *            新x坐标
	 * @param y
	 *            新y坐标
	 * @param loc
	 *            源位置实体
	 * @return
	 */
	private boolean sameLoc(double x, double y, LocationXmpp loc) {
		if (loc != null) {
			double X = (loc.getLongitude() - x) * 10000000000L;
			double Y = (loc.getLatitude() - y) * 10000000000L;
			if (Math.abs(X) < 5 && Math.abs(Y) < 5) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Description:保存定位信息,更新人员坐标
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日上午11:16:00
	 * @param locxmpp
	 * @param token
	 */
	/**
	 * update by yang on 2016/6/13 
	 */
	@SuppressWarnings("unchecked")
	public void saveLoc(LocationXmpp locxmpp, AuthToken token) {
		locxmpp.setRepeat("2");
		try {
			// 如果该坐标与前一个时刻的坐标相同，则标记为重复的点
			Criteria c = locationXmppDao.createCriteria(
					Restrictions.eq("personId", token.getFxryId()),
					Restrictions.lt("locTime", locxmpp.getLocTime()));
			c.addOrder(Order.desc("locTime"));
			c.setFirstResult(0);
			c.setMaxResults(1);
			List<LocationXmpp> loc = c.list();
			if (CommonUtils.isNotNull(loc)) {
				if (sameLoc(locxmpp.getLongitude(), locxmpp.getLatitude(),
						loc.get(0))) {
					locxmpp.setRepeat("1");
				}
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		locationXmppDao.save(locxmpp);
		
		//add by yang on 2016/6/14
		try{
			LocationBufferXmpp locbufferxmpp = new LocationBufferXmpp();
			PropertyUtils.copyProperties(locbufferxmpp, locxmpp);
			locbufferxmpp.setLocId(null);
			locationBufferXmppDao.save(locbufferxmpp);	
		}catch(Exception e){
			log.error(e.getLocalizedMessage());
		}
		
		try {
			// 已有定位信息比该消息新，不更新其坐标
			Criteria c = locationXmppDao.createCriteria(
					Restrictions.eq("personId", token.getFxryId()),
					Restrictions.ne("locTime", locxmpp.getLocTime()));
			c.addOrder(Order.desc("locTime"));
			c.setFirstResult(0);
			c.setMaxResults(1);
			List<LocationXmpp> loc = c.list();
			if (CommonUtils.isNull(loc)
					|| loc.get(0).getLocTime().compareTo(locxmpp.getLocTime()) < 0) {
				///update by yang on 2016/6/14
				locationXmppDao.createSQLQuery(
								"update SQJZ.CC_FXRY_BASEINFO set gis_x=?, gis_y=?,LAST_LOC_ID=?,LOC_TYPE=?,LOC_TIME=? where id=?",
								locxmpp.getLongitude(), locxmpp.getLatitude(), locxmpp.getLocId(), 
								locxmpp.getLocType(), locxmpp.getLocTime(), token.getFxryId())
						.executeUpdate();
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * Description:保存报警信息
	 * 
	 * @author Shuzz
	 * @since 2014年7月4日上午11:43:08
	 * @param alarm
	 */
	public void saveAlarm(AlarmXmpp alarm) {
		alarmXmppDao.save(alarm);
	}

	/**
	 * Description:根据设备编号和类型查询待发送的信息,目前每个设备待发送信息不会超过8条
	 * 
	 * @author Shuzz
	 * @since 2014年7月3日下午4:04:04
	 * @param deviceNumber
	 *            设备编号
	 * @param type
	 *            消息类型
	 * @return
	 */
	private MsgWaitXmpp findMsgWait(String deviceNumber, String type) {
		MsgWaitXmpp xmpp = msgWaitXmppDao.findOneByCriteria(
				Restrictions.eq("type", type),
				Restrictions.eq("deviceNumber", deviceNumber));
		return xmpp;
	}

	/**
	 * Description:保存某一个设备等待发送的信息
	 * 
	 * @author Shuzz
	 * @since 2014年9月12日下午2:37:08
	 * @param deviceNumber
	 *            设备编号
	 * @param type
	 *            消息类型
	 * @param content
	 *            消息内容
	 */
	public void saveEmMessageReport(String deviceNumber, String type,
			String content) {
		if (!CommonUtils.isNull(deviceNumber)) {
			MsgWaitXmpp xmpp = findMsgWait(deviceNumber, type);
			if (xmpp == null) {
				xmpp = new MsgWaitXmpp();
				xmpp.setType(type);
				xmpp.setDeviceNumber(deviceNumber);
			}
			xmpp.setContent(content);
			msgWaitXmppDao.save(xmpp);
		}
	}

	/**
	 * Description:保存待批量发送的信息
	 * 
	 * @author Shuzz
	 * @since 2014年7月3日下午2:02:29
	 * @param deviceNumbers
	 *            设备编号
	 * @param type
	 *            消息类型
	 * @param content
	 *            消息内容
	 */
	public void saveEmMessageReport(List<String> deviceNumbers, String type,
			String content) {
		for (String deviceNumber : deviceNumbers) {
			saveEmMessageReport(deviceNumber, type, content);
		}
	}

	/**
	 * Description:根据设备编号查找其所有的待发送消息
	 * 
	 * @author Shuzz
	 * @since 2014年7月4日上午11:43:01
	 * @param deviceNumber
	 *            设备编号
	 * @return
	 */
	public List<MsgWaitXmpp> findMsgWaits(String deviceNumber) {
		return msgWaitXmppDao.findByCriteria(Restrictions.eq("deviceNumber",
				deviceNumber));
	}

	/**
	 * Description:根据设备编号查找特定类型的待发送消息
	 * 
	 * @author Shuzz
	 * @since 2014年8月4日下午4:47:13
	 * @param deviceNumber
	 *            设备编号
	 * @param type
	 *            消息类型
	 * @return
	 */
	public List<MsgWaitXmpp> findMsgWaits(String deviceNumber, String type) {
		return msgWaitXmppDao.findByCriteria(
				Restrictions.eq("deviceNumber", deviceNumber),
				Restrictions.eq("type", type));
	}

	/**
	 * Description: 根据系统设置信息的id查询其对应的定位周期配置
	 * 
	 * @author Shuzz
	 * @since 2014年7月4日上午11:43:03
	 * @param id
	 *            系统设置信息的id
	 * @return
	 */
	public List<LocationPeriod> getLocationPeriod(String id) {
		return locationPeriodDao.findByCriteria(Restrictions.eq(
				"sysSetting.settingId", id));
	}

	/**
	 * Description:待发送消息发送完成,从库中删除该信息
	 * 
	 * @author Shuzz
	 * @since 2014年7月4日上午11:42:58
	 * @param deviceNumber
	 * @param type
	 */
	public void accomplishMsg(String deviceNumber, String type) {
		msgWaitXmppDao.createQuery(
				"delete MsgWaitXmpp where deviceNumber=? and type=?",
				deviceNumber, type).executeUpdate();
	}

	/**
	 * Description:某设备准备开始工作前，清除其所有的待发送消息
	 * 
	 * @author Shuzz
	 * @since 2014年7月4日上午11:42:58
	 * @param deviceNumber
	 */
	public void accomplishMsg(String deviceNumber) {
		msgWaitXmppDao.createQuery("delete MsgWaitXmpp where deviceNumber=?",
				deviceNumber).executeUpdate();
	}

	/**
	 * Description:判断该人员的聚集报警是否忽略
	 * 
	 * @author Shuzz
	 * 
	 * @since 2014年7月4日上午11:41:33
	 * 
	 * @param fxryId
	 *            服刑人员ID
	 * @param orgId
	 *            服刑人员所属ID
	 * @param time
	 *            报警时间
	 * @return true可忽略;false不可忽略
	 */
	@SuppressWarnings("unchecked")
	public boolean isGatherIngonre(String fxryId, String orgId, Date time) {
		List<String> orgIds = gatherIgnoreDao.createSQLQuery(
				"select t.org_id from CC_ORGANIZATION_INFO t start with t.org_id=? "
						+ "connect by prior t.sup_org_id=t.org_id", orgId)
				.list();
		return gatherIgnoreDao.isGatherIngonre(fxryId, orgIds, time);
	}

	/**
	 * Description:查找某人的待处理超距信息
	 * 
	 * @author Shuzz
	 * @param fxryId
	 *            服刑人员id
	 * @return
	 */
	private List<MsgOutrangeXmpp> findMsgOutrangeByfxry(String fxryId) {
		return msgOutrangeXmppDao.findByCriteria(
				Restrictions.eq("fxryId", fxryId),
				Restrictions.eq("status", "2"));
	}

	/**
	 * Description:判断该人机分离是否到报警时限
	 * 
	 * @param fxryId
	 * @param time
	 * @param sparate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean isTimeOutOfOutrange(String fxryId, Date time, Integer sparate) {
		boolean flag = false;
		Criteria c = msgOutrangeXmppDao.createCriteria(
				Restrictions.eq("fxryId", fxryId),
				Restrictions.eq("status", "2"));
		c.addOrder(Order.asc("msgTime"));
		List<MsgOutrangeXmpp> outs = c.list();
		if (CommonUtils.isNotNull(outs)) {
			if (null != sparate) {
				long range = time.getTime()
						- outs.get(0).getMsgTime().getTime();
				log.debug("与已有的最早超距报警的时间间隔为:{}", range);
				if (range > sparate * 60 * 1000) {
					flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * Description:超距到一定时限产生报警后更新以前的超距记录的报警ID
	 * 
	 * @param fxryId
	 *            服刑人员Id
	 * @param alarmId
	 *            报警id
	 */
	public void alarmMsgOutrange(String fxryId, String alarmId) {
		List<MsgOutrangeXmpp> outs = findMsgOutrangeByfxry(fxryId);
		for (MsgOutrangeXmpp out : outs) {
			out.setAlarmId(alarmId);
			msgOutrangeXmppDao.save(out);
		}
	}

	/**
	 * Description:针对某个报警处理某人的超距信息
	 * 
	 * @author Shuzz
	 * @param alarmId
	 */
	public void handleOutrangeByAlarm(String alarmId) {
		List<MsgOutrangeXmpp> outs = msgOutrangeXmppDao.findByCriteria(
				Restrictions.eq("alarmId", alarmId),
				Restrictions.eq("status", "2"));
		;
		for (MsgOutrangeXmpp out : outs) {
			out.setStatus("1");
			msgOutrangeXmppDao.save(out);
		}
	}

	/**
	 * Description:处理某人的所有超距信息
	 * 
	 * @author Shuzz
	 * @param fxryId
	 *            服刑人员id
	 */
	public void handleOutrangeByFxry(String fxryId) {
		List<MsgOutrangeXmpp> outs = findMsgOutrangeByfxry(fxryId);
		for (MsgOutrangeXmpp out : outs) {
			out.setStatus("1");
			msgOutrangeXmppDao.save(out);
		}
	}

	/**
	 * Description:保存超距信息
	 * 
	 * @author Shuzz
	 * @since 2014年7月23日上午10:32:27
	 * @param msgOutrange
	 */
	public void saveMsgOutrange(MsgOutrangeXmpp msgOutrange) {
		msgOutrangeXmppDao.save(msgOutrange);
	}

	public OrgXmpp getOrg(String orgId) {
		return orgXmppDao.get(orgId);
	}

	/**
	 * Description:根据机构ID获取机构名称
	 * 
	 * @author Shuzz
	 * @since 2014年7月23日上午10:31:00
	 * @param orgId
	 *            机构ID
	 * @return
	 */
	public String getOrgName(String orgId) {
		OrgXmpp org = orgXmppDao.get(orgId);
		if (org != null) {
			return org.getOrgName();
		}
		return null;
	}

	/**
	 * Description:根据腕表编号获取人员id
	 * 
	 * @author Shuzz
	 * @since 2014年7月22日上午10:36:54
	 * @param number
	 *            腕表编号
	 * @return
	 */
	public AuthToken findFxryByWbnumber(String number) {
		List<CcSuperviseDevice> devices = emSuperviseDeviceDao.findByCriteria(
				Restrictions.eq("deviceNumber", number),
				Restrictions.eq("deviceType", "2"));
		if (CommonUtils.isNotNull(devices)) {
			if (devices.get(0).getDevicePair() != null
					&& devices.get(0).getDevicePair().getMachine() != null
					&& devices.get(0).getDevicePair().getMachine()
							.getDeviceNumber() != null) {
				String machineNumber = devices.get(0).getDevicePair()
						.getMachine().getDeviceNumber();
				return findFxryByDevice(machineNumber);
			}
		}
		return null;
	}

	/**
	 * Description:保存聚集信息
	 * 
	 * @author Shuzz
	 * @since 2014年7月23日上午10:30:16
	 * @param fxry1
	 *            服刑人员ID
	 * @param fxry2
	 *            服刑人员ID
	 * @param time
	 *            聚集时间
	 */
	public void saveGatherInfo(String fxry1, String fxry2, Date time) {
		String id1, id2;
		if (fxry1.compareTo(fxry2) < 0) {
			id1 = fxry1;
			id2 = fxry2;
		} else {
			id1 = fxry2;
			id2 = fxry1;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(Calendar.MINUTE, -2);
		List<MsgGatherXmpp> list = gatherXmppDao.findByCriteria(
				Restrictions.eq("fxry1", id1), Restrictions.eq("fxry2", id2),
				Restrictions.ge("time", c.getTime()));
		if (!CommonUtils.isNotNull(list)) {
			MsgGatherXmpp gather = new MsgGatherXmpp();
			gather.setFxry1(id1);
			gather.setFxry2(id2);
			gather.setTime(time);
			gatherXmppDao.save(gather);
		}
	}

	/**
	 * Description:根据机构ID获取其上级机构ID
	 * 
	 * @author Shuzz
	 * @since 2014年7月23日上午10:29:46
	 * @param orgId
	 *            机构ID
	 * @return
	 */
	public String getSubOrgid(String orgId) {
		OrgXmpp org = orgXmppDao.get(orgId);
		if (org != null) {
			return org.getSupOrg().getOrgId();
		}
		return null;
	}

	/**
	 * Description:根据服刑人员ID获取其信息
	 * 
	 * @author Shuzz
	 * @since 2014年7月23日上午10:29:05
	 * @param fxryId
	 *            服刑人员ID
	 * @return
	 */
	public FxryXmpp getFxryById(String fxryId) {
		if (fxryId != null) {
			return fxryXmppDao.get(fxryId);
		} else {
			return null;
		}
	}

	/**
	 * Description:根据人员ID获取其姓名
	 * 
	 * @author Shuzz
	 * @since 2014年7月23日上午10:28:36
	 * @param pesronId
	 *            人员ID
	 * @return
	 */
	public String getPersonName(String pesronId) {
		String result = "";
		Persons per = personsDao.get(pesronId);
		if (per != null) {
			result = per.getName();
		}
		return result;
	}

	public void saveMsgCrossBorder(String X, String Y, Date time,
			String isCross, String fxryId) {
		double x = DateFormat.tranStrToDouble(X);
		double y = DateFormat.tranStrToDouble(Y);
		MsgCrossBorderXmpp xmpp = new MsgCrossBorderXmpp();
		xmpp.setFxryId(fxryId);
		xmpp.setGis_x(x);
		xmpp.setGis_y(y);
		xmpp.setTime(time);
		xmpp.setIsCross(isCross);
		msgCrossBorderXmppDao.save(xmpp);
	}

	/**
	 * Description:根据设备编号查获其采集频率的设置id
	 * 
	 * @author Shuzz
	 * @since 2014年8月4日下午4:59:53
	 * @param deviceNumber
	 * @return
	 */
	public String findSysSettingByDevice(String deviceNumber) {
		String result = null;
		CcDzjgsbDevice device = findSuperviseDeviceByNumber(deviceNumber);
		if (device != null) {
			String orgId = device.getBuyUnit();
			if (orgId.equals(orgXmppDao.getRootOrgid())) {
				SysSetting sys = sysSettingService.getByOrgID(orgId);
				if (sys != null) {
					result = sys.getSettingId();
				}
			} else {
				SysSetting sys = sysSettingService.getByOrgID(orgId);
				if (sys != null && sys.getLocPeriods().size() > 0) {
					result = sys.getSettingId();
				} else {
					SysSetting rootSys = sysSettingService
							.getByOrgID(orgXmppDao.getRootOrgid());
					if (rootSys != null) {
						result = rootSys.getSettingId();
					}
				}
			}
		}
		return result;
	}

	/**
	 * Description:记录日志
	 * 
	 * @author Shuzz
	 * @since 2014年9月3日上午10:06:38
	 * @param deviceNumber
	 *            设备编号
	 * @param type
	 *            消息类型
	 * @param messageTime
	 *            消息达到时间
	 * @param packet
	 *            消息本体
	 */
	public void createXmppLog(String deviceNumber, String type,
			Date messageTime, IQ packet) {
		XmppLog log = new XmppLog();
		log.setDeviceNumber(deviceNumber);
		log.setType(type);
		log.setMessageTime(messageTime);
		log.setMessageId(packet.getID());
		log.setMessageType(packet.getType().toString());
		String message = packet.getChildElement().asXML();
		if (message.length() > 1000) {
			message = message.substring(0, 1000);
		}
		log.setMessage(message);
		xmppLogDao.save(log);
	}

	/**
	 * Description:记录登录登出日志
	 * 
	 * @author Shuzz
	 * @since 2014年9月3日上午10:05:19
	 * @param deviceNumber
	 *            设备编号
	 * @param deviceType
	 *            设备类型(主机还是移动端)
	 * @param isLogin
	 *            是否登录(1是，2否)
	 * @param loginTime
	 *            登录时间
	 */
	public void login(String deviceNumber, String deviceType, String isLogin,
			Date loginTime) {
		XmppDeviceInfo info = xmppDeviceInfoDao.findDevice(deviceNumber,
				deviceType);
		if (info == null) {
			info = new XmppDeviceInfo();
			info.setDeviceNumber(deviceNumber);
			info.setType(deviceType);
		}
		info.setIsLogin(isLogin);
		if ("1".equals(isLogin)) {
			info.setLoginTime(loginTime);
		}
		xmppDeviceInfoDao.save(info);
	}

	/**
	 * Description:查出所有正在使用中的设备
	 * 
	 * @author Shuzz
	 * @since 2014年9月3日上午10:03:43
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> findAllUsedDevice() {
		String sql = "select t.device_code from cc_fxry_baseinfo t "
				+ "where t.state!='4' and t.device_code is not null order by t.device_code";
		return dzjgsbDeviceDao.createSQLQuery(sql).list();
	}

	/**
	 * Description:查询某设备最新的一条特定类型的日志
	 * 
	 * @author Shuzz
	 * @since 2014年9月3日上午10:02:26
	 * @param deviceNumber
	 *            设备编号
	 * @param type
	 *            日志类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public XmppLog findOneLogByType(String deviceNumber, String type) {
		Criteria location = xmppLogDao.createCriteria(
				Restrictions.eq("deviceNumber", deviceNumber),
				Restrictions.eq("type", type));
		location.addOrder(Order.desc("messageTime"));
		location.setFirstResult(0);
		location.setMaxResults(1);
		List<XmppLog> result = location.list();
		if (CommonUtils.isNotNull(result)) {
			return result.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Description:获取使用中的设备的启用时间
	 * 
	 * @author Shuzz
	 * @since 2014年9月3日上午11:35:10
	 * @param deviceNumber
	 *            设备编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Date findRunning(String deviceNumber) {
		Criteria location = devicePairDao.createCriteria(
				Restrictions.eq("status", "03"),
				Restrictions.eq("machine.deviceNumber", deviceNumber));
		location.setFirstResult(0);
		location.setMaxResults(1);
		List<CcDevicePair> result = location.list();
		if (CommonUtils.isNotNull(result)) {
			return result.get(0).getBeginDate();
		} else {
			return null;
		}
	}

	/**
	 * Description:判断该越界是否超过越界时间限制
	 * 
	 * @author Shuzz
	 * @since 2014年9月3日上午9:53:34
	 * @param fxryId
	 *            服刑人员id
	 * @param limit
	 *            时间限制(分钟)
	 * @param time
	 *            越界时间
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean calCrossBorder(String fxryId, Integer limit, Date time) {
		// 首先判断这段时间间隔内是否有未越界的时候
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(Calendar.MINUTE, -limit);
		Date begin = c.getTime();
		List<MsgCrossBorderXmpp> notCross = msgCrossBorderXmppDao
				.findByCriteria(Restrictions.eq("fxryId", fxryId),
						Restrictions.eq("isCross", "2"),
						Restrictions.ge("time", begin),
						Restrictions.le("time", time));
		if (CommonUtils.isNotNull(notCross)) {
			// 存在未越界的情况，不需要产生报警
			return false;
		} else {
			// 而后判断这段时间间隔之前的情况
			c.setTime(time);
			c.add(Calendar.DAY_OF_YEAR, -1);
			Criteria location = msgCrossBorderXmppDao.createCriteria(
					Restrictions.eq("fxryId", fxryId),
					Restrictions.lt("time", begin),
					Restrictions.ge("time", c.getTime()));
			location.addOrder(Order.desc("time"));
			location.setFirstResult(0);
			location.setMaxResults(1);
			List<MsgCrossBorderXmpp> result = location.list();
			if (CommonUtils.isNotNull(result)) {
				// 有记录则判断之前一条是否越界，是则报警
				return "1".equals(result.get(0).getIsCross());
			} else {
				// 无记录则判断这段时间间隔之内最早的情况
				Criteria criteria = msgCrossBorderXmppDao.createCriteria(
						Restrictions.eq("fxryId", fxryId),
						Restrictions.ge("time", c.getTime()));
				location.addOrder(Order.asc("time"));
				location.setFirstResult(0);
				location.setMaxResults(1);
				List<MsgCrossBorderXmpp> cb = criteria.list();
				if (CommonUtils.isNotNull(cb)) {
					// 判断时间差距
					return (time.getTime() - cb.get(0).getTime().getTime()) > (limit * 60 * 1000);
				} else {
					return false;
				}
			}
		}
	}

	/**
	 * Description:修改定位信息的报警类型
	 * 
	 * @author Shuzz
	 * @since 2014年9月3日上午11:03:42
	 * @param locId
	 *            定位id
	 * @param alarmType
	 *            报警类型
	 */
	public void updateLoc(String locId, String alarmType) {
		LocationXmpp loc = locationXmppDao.get(locId);
		if (loc != null) {
			loc.setAlarmType(alarmType);
			locationXmppDao.save(loc);
		}
	}

	/**
	 * Description:根据机构获取该机构下需要发送采集周期信息的定位主机编号，该视图已经将有个人采集周期的设备过滤了
	 * 
	 * @author Shuzz
	 * @since 2014年9月9日上午10:07:00
	 * @param orgId
	 * @param notOrgId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> findFrequencyDevice(String orgId, List<String> notOrgId) {
		String sql = "select device_number from VIEW_CC_NOFXRY_SETTING t where 1=1 ";
		if (!rootOrgId.equals(orgId)) {
			sql += " and PROCURE_UNIT='" + orgId + "'";
		}
		if (CommonUtils.isNotNull(notOrgId)) {
			sql += " and PROCURE_UNIT not in (";
			for (String id : notOrgId) {
				sql += id + ", ";
			}
			sql = sql.substring(0, sql.length() - 2);
			sql += ") ";
		}
		return dzjgsbDeviceDao.createSQLQuery(sql).list();
	}

	/**
	 * Description:查获相应的基站
	 * 
	 * @author Shuzz
	 * @since 2014年9月11日上午10:54:49
	 * @param cell
	 * @param lac
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public BaseStation findBaseStation(Long cell, Long lac) {
		Criteria criteria = baseStationDao.createCriteria(
				Restrictions.eq("id.cell", cell),
				Restrictions.eq("id.lac", lac));
		criteria.setFirstResult(0);
		criteria.setMaxResults(1);
		List<BaseStation> list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * Description:判断是否需要进行LBS定位<br>
	 * 最近5分钟内有或则该定位消息的时间已经请求过则不再LBS定位
	 * 
	 * @author Shuzz
	 * @param deviceNumber
	 *            设备编号
	 * @param locTime
	 *            定位时间
	 * @return
	 * @since 2014年11月5日上午9:49:21
	 */
	public boolean needLbs(String deviceNumber, Date locTime) {
		//different from online
		//begin
		/*
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.SECOND, -30);
		Criteria criteria = xmppLbsReqDao.createCriteria(
				Restrictions.eq("deviceNumber", deviceNumber),
				Restrictions.gt("reqTime", c.getTime()));
		*/
		Criteria criteria = xmppLbsReqDao.createCriteria(
				Restrictions.eq("deviceNumber", deviceNumber),
				Restrictions.eq("locTime", locTime));
		//end
		criteria.setFirstResult(0);
		criteria.setMaxResults(1);
		if (CommonUtils.isNull(criteria.list())) {
			/*
			 * Criteria cri = xmppLbsReqDao.createCriteria(
			 * Restrictions.eq("deviceNumber", deviceNumber),
			 * Restrictions.eq("locTime", locTime)); cri.setFirstResult(0);
			 * cri.setMaxResults(1); if (CommonUtils.isNull(cri.list())) {
			 * return true; }
			 */
			return true;
		}
		return false;
	}

	/**
	 * Description:保存LBS定位请求信息
	 * 
	 * @author Shuzz
	 * @param deviceNumber
	 * @param locTime
	 * @since 2014年11月5日上午9:51:47
	 */
	public void saveLbsReq(String deviceNumber, Date locTime) {
		XmppLbsReq xmppLbsReq = new XmppLbsReq();
		xmppLbsReq.setDeviceNumber(deviceNumber);
		xmppLbsReq.setLocTime(locTime);
		xmppLbsReq.setReqTime(new Date());
		xmppLbsReqDao.save(xmppLbsReq);
	}

	/**
	 * Description:查找所有在佩戴设备的手机号
	 * 
	 * @author Shuzz
	 * @return
	 * @since 2014年11月26日上午10:12:27
	 */
	@SuppressWarnings("unchecked")
	public List<String> findAllUsedDevicePhoneNumber() {
		String sql = "select distinct(sd.PHONE_NUMBER) from cc_fxry_baseinfo t "
				+ "left join CC_DZJGSB_DEVICE sd on t.device_code=sd.device_number "
				+ "where t.state!='4' and t.device_code is not null order by sd.PHONE_NUMBER";
		return dzjgsbDeviceDao.createSQLQuery(sql).list();
	}

	/**
	 * Description:判断人机分离消息是否生成报警
	 * 
	 * @author Shuzz
	 * @since 2014年11月28日上午11:05:11
	 * @param fxryId
	 * @param time
	 * @return
	 */
	public boolean isTimeDisappear(String fxryId, Date time) {
		boolean flag = false;
		int dTime = Config.getInt("xmpp.alarm.disappear.time", 70);
		int dCount = Config.getInt("xmpp.alarm.disappear.count", 5);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.add(Calendar.MINUTE, 0 - dTime);
		Criteria criteria = msgDisappearXmppDao.createCriteria(
				Restrictions.eq("fxryId", fxryId),
				Restrictions.eq("status", "2"),
				Restrictions.ge("msgTime", calendar.getTime()),
				Restrictions.le("msgTime", time));
		criteria.setMaxResults(dCount + 1);
		List list = criteria.list();
		if (CommonUtils.isNotNull(list)) {
			if (list.size() >= dCount) {
				flag = true;
			}
		}
		return flag;
	}

	public void saveMsgDisappear(MsgDisappearXmpp msgDisappear) {
		msgDisappearXmppDao.save(msgDisappear);
	}

	public void alarmMsgDisappear(String fxryId, String alarmId, Date time) {
		int dTime = Config.getInt("xmpp.alarm.disappear.time", 70);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.add(Calendar.MINUTE, 0 - dTime);
		Criteria criteria = msgDisappearXmppDao.createCriteria(
				Restrictions.eq("fxryId", fxryId),
				Restrictions.eq("status", "2"),
				Restrictions.ge("msgTime", calendar.getTime()),
				Restrictions.le("msgTime", time));
		List<MsgDisappearXmpp> disappears = criteria.list();
		for (MsgDisappearXmpp disappear : disappears) {
			disappear.setAlarmId(alarmId);
			disappear.setStatus("1");
			msgDisappearXmppDao.save(disappear);
		}
	}

	/**
	 * Description:保存腕表状态信息
	 * 
	 * @author Shuzz
	 * @param token
	 *            记录登录信息的token
	 * @param deviceStatus
	 *            带有腕表信息的XMLdom4j实体
	 */
	public void saveDeviceStatus(AuthToken token, Element deviceStatus) {
		XmppDeviceStatus status = new XmppDeviceStatus();
		status.setFxryId(token.getFxryId());
		status.setDeviceId(token.getDeviceId());
		status.setDeviceNum(token.getDeviceNumber());
		String power = deviceStatus.elementText("power");
		String close = deviceStatus.elementText("close");
		String slient = deviceStatus.elementText("slient");
		String Time = deviceStatus.elementText("time");
		Date time = DateFormat.tranStrToDate(Time);
		status.setPower(power);
		status.setSilentState(slient);
		status.setCloseState(close);
		status.setTime(time);
		xmppDeviceStatusDao.save(status);
	}

	/**
	 * Description: 判断几秒内是否有该人员的相同报警
	 * 
	 * @author Shuzz
	 * @since 2015年6月23日下午3:26:04
	 * @param fxryId
	 * @param alarmType
	 * @param time
	 * @param second
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public boolean hasSameAlarm(String fxryId, String alarmType, Date time,
			int second) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(Calendar.SECOND, second);
		List list = alarmXmppDao.findByCriteria(
				Restrictions.eq("fxryId", fxryId),
				Restrictions.eq("alarmType", alarmType),
				Restrictions.ge("alarmTime", c.getTime()));
		return CommonUtils.isNotNull(list);
	}

	/**
	 * Description:查找服刑人员的责任人
	 * 
	 * @author Shuzz
	 * @since 2015年6月29日上午10:10:17
	 * @param fxryId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public FXRYReportOrg findReportByFxryId(String fxryId) {
		FXRYReportOrg reportOrg = null;
		List<FXRYReportOrg> reportOrgs = fxryReportOrgDao.createCriteria(
				Restrictions.eq("fxryId", fxryId)).list();
		if (CommonUtils.isNotNull(reportOrgs)) {
			reportOrg = reportOrgs.get(0);
		}
		return reportOrg;

	}

	/**
	 * Description:查询移动执法终端授权情况
	 * 
	 * @author Shuzz
	 * @since 2015年8月11日上午10:18:24
	 * @param imei
	 *            加密后的设备号
	 * @return
	 */
	//different from online
	//@SuppressWarnings("unchecked")
	/*
	public XmppJustice findJustice(String imei) {
		XmppJustice justice = null;
		List<XmppJustice> justices = xmppJusticeDao.createCriteria(
				Restrictions.eq("imei", imei)).list();
		if (CommonUtils.isNotNull(justices)) {
			justice = justices.get(0);
		}
		return justice;
	}
	*/

	/**
	 * Description:判断服刑人员是否暂停监控
	 * 
	 * @author Shuzz
	 * @since 2015年9月17日下午3:59:53
	 * @param fxryId
	 *            服刑人员id
	 * @return true:暂停监控;false:监控中
	 */
	public boolean isPauseMonitor(String fxryId) {
		SysSetting sysSetting = sysSettingService.getByFxry(fxryId);
		if (null != sysSetting) {
			return "1".equals(sysSetting.getStatus());
		}
		return false;
	}
}
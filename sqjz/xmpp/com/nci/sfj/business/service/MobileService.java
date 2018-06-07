package com.nci.sfj.business.service;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import javax.annotation.PostConstruct;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Encoder;

import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.em.dwjk.dao.AlarmDao;
import com.nci.dcs.em.dwjk.model.CcAlarmInfo;
import com.nci.dcs.system.dao.BmbDao;
import com.nci.dcs.system.dao.UserDao;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.User;
import com.nci.dcs.system.service.DictionaryInfoService;
import com.nci.sfj.business.dao.LocationXmppDao;
import com.nci.sfj.business.model.LocationXmpp;
import com.nci.sfj.common.util.Charsets;
import com.nci.sfj.common.util.Config;
import com.nci.sfj.webservice.dao.ViewAlarmInfoDao;
import com.nci.sfj.webservice.dao.ViewFxryWebserviceDao;
import com.nci.sfj.webservice.dao.ViewQxsfjTjxxDao;
import com.nci.sfj.webservice.dao.ViewSfjTjxxDao;
import com.nci.sfj.webservice.model.ViewAlarmInfo;
import com.nci.sfj.webservice.model.ViewFxryWebservice;
import com.nci.sfj.webservice.model.ViewQxsfjTjxx;
import com.nci.sfj.webservice.model.ViewSfjTjxx;
import com.nci.sfj.xmpp.auth.AuthToken;
import com.nci.sfj.xmpp.exception.PacketMessageException;

/**
 * Description:提供至移动终端的Service
 * 
 * @author Shuzz
 * @since 2014年5月23日上午9:51:54
 */
@Service
@Transactional
public class MobileService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private BmbDao bmbDao;
	@Autowired
	private DictionaryInfoService dictionaryInfoService;
	@Autowired
	private ViewAlarmInfoDao viewAlarmInfoDao;
	@Autowired
	private ViewQxsfjTjxxDao viewQxsfjTjxxDao;
	@Autowired
	private ViewSfjTjxxDao viewSfjTjxxDao;
	@Autowired
	private ViewFxryWebserviceDao fxryWebserviceDao;
	@Autowired
	private LocationXmppDao locationXmppDao;
	@Autowired
	private AlarmDao alarmDao;

	private Map<String, String> cached_stat;

	private final String wssf = "yyyy-MM-dd HH:mm:ss";

	@PostConstruct
	private void organtizCache() {
		cached_stat = new HashMap<String, String>();
		cached_stat.put("1", "在矫");
		cached_stat.put("2", "漏管");
		cached_stat.put("3", "脱管");
		cached_stat.put("4", "解矫");
		cached_stat.put("5", "请假");
		cached_stat.put("6", "正在转出");
		cached_stat.put("7", "离京");
		cached_stat.put("999", "其它");
	}

	/**
	 * Description:组织时间数据的XML节点
	 * 
	 * @author Shuzz
	 * @param root
	 *            XML根节点
	 * @param ename
	 *            新节点名
	 * @param date
	 *            时间数据
	 */
	private void organtizElement(Element root, String ename, Date date) {
		Element e = root.addElement(ename);
		if (null != date) {
			SimpleDateFormat wsf = new SimpleDateFormat(wssf);
			e.setText(wsf.format(date));
		}
	}

	/**
	 * Description:组织int数据的XML节点
	 * 
	 * @author Shuzz
	 * @param root
	 *            XML根节点
	 * @param ename
	 *            新节点名
	 * @param value
	 *            int数据
	 */
	private void organtizElement(Element root, String ename, Integer value) {
		Element e = root.addElement(ename);
		if (null != value) {
			e.setText(Integer.toString(value));
		} else {
			e.setText("0");
		}
	}

	/**
	 * Description:组织包含字典数据的XML节点
	 * 
	 * @author Shuzz
	 * @param root
	 *            XML根节点
	 * @param ename
	 *            新节点名
	 * @param text
	 *            字典项数据
	 * @param map
	 *            对应的字典信息
	 */
	private void organtizElement(Element root, String ename, String text,
			Map<String, String> map) {
		Element e = root.addElement(ename);
		if (!CommonUtils.isNull(text) && !CommonUtils.isNull(map.get(text))) {
			e.setText(map.get(text));
		}
	}

	/**
	 * Description:组织String类型数据的XML节点
	 * 
	 * @author Shuzz
	 * @param root
	 *            XML根节点
	 * @param ename
	 *            新节点名
	 * @param text
	 *            String数据
	 */
	private void organtizElement(Element root, String ename, String text) {
		Element e = root.addElement(ename);
		if (!CommonUtils.isNull(text)) {
			e.setText(text);
		}
	}

	/**
	 * Description:压缩数据
	 * 
	 * @author Shuzz
	 * @since 2014年9月12日上午11:37:54
	 * @param root
	 *            Element根节点
	 * @param isCompress
	 *            是否压缩
	 * @return
	 */
	private String compress(Element root, String isCompress) {
		return compress(root.asXML(), isCompress);
	}

	/**
	 * Description:压缩数据
	 * 
	 * @author Shuzz
	 * @param text
	 *            数据内容
	 * @param isCompress
	 *            是否压缩
	 * @return
	 * @since 2014年11月5日上午10:07:35
	 */
	private String compress(String text, String isCompress) {
		String result = "";
		try {
			if (isCompress != null && "true".equals(isCompress)) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				GZIPOutputStream gzip = new GZIPOutputStream(out);
				gzip.write(text.getBytes(Charsets.UTF8));
				gzip.close();
				BASE64Encoder encoder = new BASE64Encoder();
				result = encoder.encode(out.toByteArray());
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * Description:组织报警信息
	 * 
	 * @author Shuzz
	 * @since 2014年8月19日上午10:56:48
	 * @param list
	 * @param orgId
	 * @return
	 */
	private Element organtizAlarmInfo(List<ViewAlarmInfo> list, String orgId) {
		if (CommonUtils.isNotNull(list)) {
			Element root = DocumentFactory.getInstance()
					.createElement("alarms");
			root.addAttribute("orgId", orgId);
			Map<String, String> bjjb = dictionaryInfoService
					.getDictMapByKey("BJJB");
			Map<String, String> bjlx = dictionaryInfoService
					.getDictMapByKey("BJLX");
			Map<String, String> jzlb = dictionaryInfoService
					.getDictMapByKey("JZLB");
			for (int i = 0; i < list.size() && i < 100; i++) {
				ViewAlarmInfo alarminfo = list.get(i);
				Element alarm = DocumentFactory.getInstance().createElement(
						"alarm");
				organtizElement(alarm, "id", alarminfo.getAlarmId());
				organtizElement(alarm, "fxryId", alarminfo.getFxryId());
				organtizElement(alarm, "name", alarminfo.getName());
				organtizElement(alarm, "alarmType", alarminfo.getAlarmType(),
						bjlx);
				organtizElement(alarm, "alarmLevel", alarminfo.getAlarmLevel(),
						bjjb);
				organtizElement(alarm, "adjustType", alarminfo.getAdjustType(),
						jzlb);
				organtizElement(alarm, "alarmTime", alarminfo.getAlarmTime());
				organtizElement(alarm, "executeOrg", alarminfo.getExecuteUnit());
				organtizElement(alarm, "executeOrgName",
						alarminfo.getExecuteUnitName());
				organtizElement(alarm, "isautoAlert", alarminfo.isautoAlert());
				organtizElement(alarm, "alert", alarminfo.getAlert());
				organtizElement(alarm, "alarmContent", alarminfo.getAlarm());
				organtizElement(alarm, "handler", alarminfo.getHandler());
				organtizElement(alarm, "handleTime", alarminfo.getHandleTime());
				organtizElement(alarm, "record", alarminfo.getRecord());
				organtizElement(alarm, "status", alarminfo.status());
				root.add(alarm);
			}
			return root;
		}
		return null;
	}

	/**
	 * Description:根据报警时间项组织报警数据
	 * @author Shuzz
	 *
	 */
	@SuppressWarnings("rawtypes")
	class ComparatorAlarm implements Comparator {
		public int compare(Object o1, Object o2) {
			ViewAlarmInfo alarm1 = (ViewAlarmInfo) o1;
			ViewAlarmInfo alarm2 = (ViewAlarmInfo) o2;
			int flag = -alarm1.getAlarmTime().compareTo(alarm2.getAlarmTime());
			return flag;
		}

	}

	/**
	 * Description:获取报警信息
	 * 
	 * @author Shuzz
	 * @since 2014年8月19日上午10:51:37
	 * @param orgId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Element getAlarmInfo(String orgId, String isCompress) {
		Element result = orgatizeResultRoot();
		int count = 10;
		if (!CommonUtils.isNull(orgId)) {
			List<ViewAlarmInfo> alarms = new ArrayList<ViewAlarmInfo>();
			try {
				List<ViewFxryWebservice> fxryList = fxryWebserviceDao
						.findByCriteria(Restrictions.eq("responseOrg", orgId),
								Restrictions.ne("state", "4"));
				for (ViewFxryWebservice fxry : fxryList) {
					Criteria criteria = viewAlarmInfoDao
							.createCriteria(Restrictions.eq("fxryId",
									fxry.getId()));
					criteria.addOrder(Order.desc("alarmTime"));
					criteria.setFirstResult(0);
					criteria.setMaxResults(count);
					List<ViewAlarmInfo> list = criteria.list();
					if (CommonUtils.isNotNull(list)) {
						alarms.addAll(list);
					}
					Collections.sort(alarms, new ComparatorAlarm());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			Element root = organtizAlarmInfo(alarms, orgId);
			if (root != null) {
				String text = compress(root, isCompress);
				result.setText(text);
			} else {
				String text = compress("null", isCompress);
				result.setText(text);
			}
		}
		return result;
	}

	/**
	 * Description:获取区县司法局的统计信息
	 * 
	 * @author Shuzz
	 * @since 2014年8月19日上午10:56:15
	 * @param root
	 * @param list
	 */
	private void organtizSfjFxryTjxx(Element root, List<ViewSfjTjxx> list) {
		if (CommonUtils.isNotNull(list)) {
			for (ViewSfjTjxx fxryTjxx : list) {
				Element fxry = DocumentFactory.getInstance().createElement(
						"fxryTjxx");
				fxry.addAttribute("orgType", "2");
				organtizElement(fxry, "orgId", fxryTjxx.getOrgId());
				organtizElement(fxry, "orgName", fxryTjxx.getCname());
				organtizElement(fxry, "zjry", fxryTjxx.getZjry());
				organtizElement(fxry, "jkry", fxryTjxx.getJkry());
				organtizElement(fxry, "wclbj", fxryTjxx.getWclbj());
				organtizElement(fxry, "gisx", fxryTjxx.getGisx());
				organtizElement(fxry, "gisy", fxryTjxx.getGisy());
				organtizElement(fxry, "supOrg", "1");
				root.add(fxry);
			}
		}
	}

	/**
	 * Description:获取司法所的人员统计信息
	 * 
	 * @author Shuzz
	 * @since 2014年8月19日上午10:55:54
	 * @param root
	 * @param list
	 */
	private void organtizSfsFxryTjxx(Element root, List<ViewQxsfjTjxx> list) {
		if (CommonUtils.isNotNull(list)) {
			for (ViewQxsfjTjxx fxryTjxx : list) {
				Element fxry = DocumentFactory.getInstance().createElement(
						"fxryTjxx");
				organtizElement(fxry, "orgId", fxryTjxx.getOrgId());
				organtizElement(fxry, "orgName", fxryTjxx.getCname());
				organtizElement(fxry, "zjry", fxryTjxx.getZjry());
				organtizElement(fxry, "jkry", fxryTjxx.getJkry());
				organtizElement(fxry, "wclbj", fxryTjxx.getWclbj());
				organtizElement(fxry, "gisx", fxryTjxx.getGisx());
				organtizElement(fxry, "gisy", fxryTjxx.getGisy());
				organtizElement(fxry, "supOrg", fxryTjxx.getSupOrg());
				root.add(fxry);
			}
		}
	}

	private Element orgatizeRoot(Bmb bmb) {
		Element root = DocumentFactory.getInstance().createElement("fxryTjxxs");
		root.addAttribute("orgId", bmb.getBm());
		root.addAttribute("orgName", bmb.getMc());
		root.addAttribute("orgType", bmb.getOrgType());
		return root;
	}

	private Element orgatizeResultRoot() {
		Element root = DocumentFactory.getInstance().createElement("result");
		return root;
	}

	/**
	 * Description:获取人员统计信息
	 * 
	 * @author Shuzz
	 * @since 2014年8月19日上午10:55:28
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public Element getFxryTjxx(String userName, String passWord,
			String isCompress) {
		Element result = orgatizeResultRoot();
		if (!CommonUtils.isNull(userName)) {
			List<User> users = userDao.findByCriteria(Restrictions.eq(
					"userName", userName));
			if (CommonUtils.isNotNull(users)) {
				if (null != users.get(0).getWunit()) {
					Element root = null;
					Bmb bmb = users.get(0).getWunit();
					if (bmb.getUnit().equals("0")) {
						bmb = bmbDao.get(bmb.getSupOrg());
					}
					String orgId = bmb.getBm();
					String orgType = bmb.getOrgType();
					if (orgType.equals("1")) {
						root = orgatizeRoot(bmb);
						List<ViewSfjTjxx> list = viewSfjTjxxDao.getAll();
						organtizSfjFxryTjxx(root, list);
						List<ViewQxsfjTjxx> listSfs = viewQxsfjTjxxDao.getAll();
						organtizSfsFxryTjxx(root, listSfs);
					} else if (orgType.equals("2")) {
						root = orgatizeRoot(bmb);
						List<ViewSfjTjxx> list = viewSfjTjxxDao
								.findByCriteria(Restrictions.eq("orgId", orgId));
						organtizSfjFxryTjxx(root, list);
						List<ViewQxsfjTjxx> listSfs = viewQxsfjTjxxDao
								.findByCriteria(Restrictions
										.eq("supOrg", orgId));
						organtizSfsFxryTjxx(root, listSfs);
					} else if (orgType.equals("3")) {
						root = orgatizeRoot(bmb);
						List<ViewQxsfjTjxx> listSfs = viewQxsfjTjxxDao
								.findByCriteria(Restrictions.eq("orgId", orgId));
						organtizSfsFxryTjxx(root, listSfs);
					}
					if (root != null) {
						String text = compress(root, isCompress);
						result.setText(text);
					} else {
						String text = compress("null", isCompress);
						result.setText(text);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Description:组织服刑人员信息
	 * 
	 * @author Shuzz
	 * @since 2014年8月19日下午4:44:55
	 * @param list
	 * @param bmb
	 * @return
	 */
	private Element organtizFxryInfo(List<ViewFxryWebservice> list, Bmb bmb) {
		if (CommonUtils.isNotNull(list)) {
			Element root = DocumentFactory.getInstance().createElement(
					"fxryInfos");
			root.addAttribute("orgId", bmb.getBm());
			root.addAttribute("orgName", bmb.getMc());
			Map<String, String> bjlx = dictionaryInfoService
					.getDictMapByKey("BJLX");
			for (ViewFxryWebservice fxryinfo : list) {
				Element fxry = DocumentFactory.getInstance().createElement(
						"fxryInfo");
				organtizElement(fxry, "id", fxryinfo.getId());
				organtizElement(fxry, "name", fxryinfo.getName());
				organtizElement(fxry, "userdName", fxryinfo.getUserdName());
				organtizElement(fxry, "sex", fxryinfo.getSex());
				organtizElement(fxry, "idCard", fxryinfo.getIdentityCard());
				organtizElement(fxry, "phone", fxryinfo.getPhoneNumber());
				organtizElement(fxry, "deviceCode", fxryinfo.getDeviceCode());
				organtizElement(fxry, "org", fxryinfo.getResponseOrg());
				organtizElement(fxry, "orgName", fxryinfo.getCname());
				organtizElement(fxry, "address", fxryinfo.getAddress());
				organtizElement(fxry, "state", fxryinfo.getState(), cached_stat);
				organtizElement(fxry, "GIS_X", fxryinfo.getGIS_X());
				organtizElement(fxry, "GIS_Y", fxryinfo.getGIS_Y());
				organtizElement(fxry, "status", fxryinfo.getStatus(), bjlx);
				root.add(fxry);
			}
			return root;
		}
		return null;
	}

	/**
	 * Description:获取服刑人员信息
	 * 
	 * @author Shuzz
	 * @since 2014年8月19日上午11:08:23
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public Element getFxryInfo(String userName, String passWord,
			String isCompress) {
		Element result = orgatizeResultRoot();
		if (!CommonUtils.isNull(userName)) {
			List<User> users = userDao.findByCriteria(Restrictions.eq(
					"userName", userName));
			if (CommonUtils.isNotNull(users)) {
				Bmb bmb = users.get(0).getWunit();
				if (null != bmb) {
					if (bmb.getUnit().equals("0")) {
						bmb = bmbDao.get(bmb.getSupOrg());
					}
					String orgId = bmb.getBm();
					List<ViewFxryWebservice> list = fxryWebserviceDao
							.findByCriteria(Restrictions.eq("responseOrg",
									orgId));
					try {
						Element root = organtizFxryInfo(list, bmb);
						if (root != null) {
							String text = compress(root, isCompress);
							result.setText(text);
						} else {
							String text = compress("null", isCompress);
							result.setText(text);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	/**
	 * Description:提供给区县局和市局查询服刑人员
	 * 
	 * @author Shuzz
	 * @param userName
	 * @param passWord
	 * @param orgId
	 * @param isCompress
	 * @return
	 * @since 2014年10月10日上午9:12:38
	 */
	public Element getFxryInfo(String userName, String passWord, String orgId,
			String isCompress) {
		Element result = orgatizeResultRoot();
		if (!CommonUtils.isNull(userName)) {
			List<User> users = userDao.findByCriteria(Restrictions.eq(
					"userName", userName));
			if (CommonUtils.isNotNull(users)) {
				Bmb bmb = users.get(0).getWunit();
				if (null != bmb) {
					if (bmb.getUnit().equals("0")) {
						bmb = bmbDao.get(bmb.getSupOrg());
					}
					if (bmb.isSfs()) {
						orgId = bmb.getBm();
					} else if (!CommonUtils.isNull(orgId)) {
						bmb = bmbDao.get(orgId);
					}
					List<ViewFxryWebservice> list = fxryWebserviceDao
							.findByCriteria(Restrictions.eq("responseOrg",
									orgId));
					try {
						Element root = organtizFxryInfo(list, bmb);
						if (root != null) {
							String text = compress(root, isCompress);
							result.setText(text);
						} else {
							String text = compress("null", isCompress);
							result.setText(text);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	private Element organtizLocation(List<LocationXmpp> list, String fxryId) {
		if (CommonUtils.isNotNull(list)) {
			Element root = DocumentFactory.getInstance().createElement(
					"locations");
			root.addAttribute("fxryId", fxryId);
			for (int i = 0; i < list.size(); i++) {
				LocationXmpp locs = list.get(i);
				Element loc = DocumentFactory.getInstance()
						.createElement("loc");
				organtizElement(loc, "x", locs.getLongitude().toString());
				organtizElement(loc, "y", locs.getLatitude().toString());
				organtizElement(loc, "time", locs.getLocTime());
				root.add(loc);
			}
			return root;
		}
		return null;
	}

	/**
	 * Description:获取服刑人员历时轨迹<br>
	 * 可按照时间段查询,由配置文件设定最大查询间隔天数以及最大结果条数
	 * 
	 * @author Shuzz
	 * @param fxryId
	 *            服刑人员id
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param isCompress
	 *            是否压缩
	 * @return
	 * @since 2014年11月5日上午10:17:12
	 */
	@SuppressWarnings("unchecked")
	public Element getLocations(String fxryId, Date startTime, Date endTime,
			String isCompress) {
		Element result = orgatizeResultRoot();
		if (!CommonUtils.isNull(fxryId)) {
			Criteria criteria = locationXmppDao.createCriteria(Restrictions.eq(
					"personId", fxryId), Restrictions.or(
					Restrictions.eq("repeat", "2"),
					Restrictions.isNull("repeat")));
			Calendar c = Calendar.getInstance();
			int maxday = Config.getInt("xmpp.ws.loc.maxday", 7);
			int maxcount = Config.getInt("xmpp.ws.loc.max", 1000);
			if (startTime != null || endTime != null) {
				if (endTime == null) {
					c.setTime(startTime);
					c.add(Calendar.DAY_OF_YEAR, maxday);
					endTime = c.getTime();
				} else if (null == startTime) {
					maxday = 0 - maxday;
					c.setTime(endTime);
					c.add(Calendar.DAY_OF_YEAR, maxday);
					startTime = c.getTime();
				} else {
					maxday = 0 - maxday;
					c.setTime(endTime);
					c.add(Calendar.DAY_OF_YEAR, maxday);
					if (startTime.before(c.getTime())) {
						startTime = c.getTime();
					}
				}
				criteria.add(Restrictions.ge("locTime", startTime));
				criteria.add(Restrictions.le("locTime", endTime));
			}
			criteria.addOrder(Order.desc("locTime"));
			criteria.setMaxResults(maxcount);
			criteria.setFirstResult(0);
			List<LocationXmpp> list = criteria.list();
			Element root = organtizLocation(list, fxryId);
			if (root != null) {
				String text = compress(root, isCompress);
				result.setText(text);
			} else {
				String text = compress("null", isCompress);
				result.setText(text);
			}
		}
		return result;
	}

	private Element organtizSxal(List<ViewFxryWebservice> list, String orgId) {
		if (CommonUtils.isNotNull(list)) {
			Element root = DocumentFactory.getInstance().createElement(
					"sxfxryals");
			root.addAttribute("orgId", orgId);
			Map<String, String> bjlx = dictionaryInfoService
					.getDictMapByKey("BJLX");
			for (ViewFxryWebservice fxryinfo : list) {
				Element fxry = DocumentFactory.getInstance().createElement(
						"fxryInfo");
				organtizElement(fxry, "id", fxryinfo.getId());
				organtizElement(fxry, "name", fxryinfo.getName());
				organtizElement(fxry, "state", fxryinfo.getState(), cached_stat);
				organtizElement(fxry, "GIS_X", fxryinfo.getGIS_X());
				organtizElement(fxry, "GIS_Y", fxryinfo.getGIS_Y());
				organtizElement(fxry, "status", fxryinfo.getStatus(), bjlx);
				root.add(fxry);
			}
			return root;
		}
		return null;
	}

	/**
	 * Description:市县局定时更新服刑人员的位置信息和报警状态
	 * 
	 * @author Shuzz
	 * @param orgId
	 * @param isCompress
	 * @return
	 * @since 2014年10月13日下午4:25:05
	 */
	public Element getSxal(String orgId, String isCompress) {
		Element result = orgatizeResultRoot();
		if (!CommonUtils.isNull(orgId)) {
			List<ViewFxryWebservice> list = fxryWebserviceDao
					.findByCriteria(Restrictions.eq("responseOrg", orgId));
			try {
				Element root = organtizSxal(list, orgId);
				if (root != null) {
					String text = compress(root, isCompress);
					result.setText(text);
				} else {
					String text = compress("null", isCompress);
					result.setText(text);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Element dealAlarm(String fxryId, String content, AuthToken token) {
		Criteria criteria = alarmDao.createCriteria(
				Restrictions.eq("fxryId", fxryId),
				Restrictions.eq("status", "2"));
		criteria.setMaxResults(1);
		criteria.setFirstResult(0);
		List<CcAlarmInfo> alarms = criteria.list();
		Element result = orgatizeResultRoot();
		Element root = DocumentFactory.getInstance().createElement("dealAlarm");
		if (!CommonUtils.isNotNull(alarms)) {
			organtizElement(root, "res", "false");
			organtizElement(root, "detail", "该人员无报警");
		} else {
			try {
				String name = "";
				if (token != null && token.getUserId() != null) {
					name = userDao.get(token.getUserId()).getName();
				}
				String sql = "update CcAlarmInfo set status=1,record=?,handler=?,handleTime=?"
						+ "where fxryId=? and status=2";
				Query q = alarmDao.createQuery(sql, content, name, new Date(),
						fxryId);
				q.executeUpdate();
				organtizElement(root, "res", "true");
			} catch (Exception e) {
				organtizElement(root, "res", "false");
				organtizElement(root, "detail", "系统发生异常");
			}
		}
		result.addText(root.asXML());
		return result;
	}

	/**
	 * Description:转换时间
	 * 
	 * @author Shuzz
	 * @since 2014年10月31日上午11:49:20
	 * @param value
	 * @return
	 */
	private Date paseStrToDate(String value) {
		Date date = null;
		try {
			SimpleDateFormat wsf = new SimpleDateFormat(wssf);
			date = wsf.parse(value);
		} catch (Exception e) {

		}
		return date;
	}

	public Element handleMobile(Element root, AuthToken token)
			throws PacketMessageException {
		String type = root.attributeValue("type");
		String isCompress = root.attributeValue("compress");
		if ("alarm".equals(type)) {
			Element org = root.element("orgId");
			if (org != null) {
				return getAlarmInfo(org.getText(), isCompress);
			} else {
				throw new PacketMessageException();
			}
		} else if ("fxrytj".equals(type)) {
			Element userName = root.element("userName");
			Element passWord = root.element("passWord");
			if (userName != null && passWord != null) {
				return getFxryTjxx(userName.getText(), passWord.getText(),
						isCompress);
			} else {
				throw new PacketMessageException();
			}
		} else if ("fxry".equals(type)) {
			Element userName = root.element("userName");
			Element passWord = root.element("passWord");
			if (userName != null && passWord != null) {
				return getFxryInfo(userName.getText(), passWord.getText(),
						isCompress);
			} else {
				throw new PacketMessageException();
			}
		} else if ("locations".equals(type)) {
			Element fxry = root.element("fxryId");
			Element start = root.element("startTime");
			Element end = root.element("endTime");
			Date startTime = null, endTime = null;
			if (null != start) {
				startTime = paseStrToDate(start.getText());
			}
			if (null != end) {
				endTime = paseStrToDate(end.getText());
			}
			if (fxry != null) {
				return getLocations(fxry.getText(), startTime, endTime,
						isCompress);
			} else {
				throw new PacketMessageException();
			}
		} else if ("alarmDeal".equals(type)) {
			Element alarmId = root.element("alarmId");
			Element content = root.element("content");
			if (alarmId != null && content != null) {
				return dealAlarm(alarmId.getText(), content.getText(), token);
			} else {
				throw new PacketMessageException();
			}
		} else if ("sxfxry".equals(type)) {
			Element userName = root.element("userName");
			Element passWord = root.element("passWord");
			Element org = root.element("orgId");
			if (userName != null && passWord != null && org != null) {
				return getFxryInfo(userName.getText(), passWord.getText(),
						org.getText(), isCompress);
			} else {
				throw new PacketMessageException();
			}
		} else if ("sxal".equals(type)) {
			Element org = root.element("orgId");
			if (org != null) {
				return getSxal(org.getText(), isCompress);
			} else {
				throw new PacketMessageException();
			}
		}
		return null;
	}
}

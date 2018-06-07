package com.nci.sfj.business.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.SpringContextUtil;
import com.nci.dcs.data.service.DzwlService;
import com.nci.dcs.data.service.JjszSevice;
import com.nci.dcs.em.model.SysSetting;
import com.nci.dcs.em.service.SysSettingService;
import com.nci.dcs.jzgl.dagl.model.FXRYReportOrg;
import com.nci.dcs.jzgl.dagl.service.FXRYReportOrgService;
import com.nci.dcs.webservices.dxpt.service.DXPTService;
import com.nci.sfj.business.model.AlarmTypeXmpp;
import com.nci.sfj.business.model.AlarmXmpp;
import com.nci.sfj.business.model.BaseStation;
import com.nci.sfj.business.model.FxryXmpp;
import com.nci.sfj.business.model.LocationXmpp;
import com.nci.sfj.business.model.MsgOutrangeXmpp;
import com.nci.dcs.em.model.CcDzjgsbDevice;
import com.nci.sfj.business.model.XmppLog;
import com.nci.sfj.common.util.Config;
import com.nci.sfj.common.util.DateFormat;
import com.nci.sfj.common.util.XMPPConstants;
import com.nci.sfj.location.LocationConstants;
import com.nci.sfj.location.LocationMessage;
import com.nci.sfj.location.sender.LocationSenderService;
import com.nci.sfj.xmpp.auth.AuthToken;
import com.nci.sfj.xmpp.model.IQModelUtil;
import com.nci.sfj.xmpp.sender.EmMessageSendService;

/**
 * Description:收到xmpp消息后的业务处理服务
 * 
 * @author Shuzz
 * 
 */
@Service
public class XmppBusinessService {

	@Autowired
	private SysSettingService sysSettingService;
	@Autowired
	private DzwlService dzwlService;
	@Autowired
	private DbService dbService;
	@Autowired
	private EmMessageSendService emMessageSendService;
	@Autowired
	private DXPTService dxptService;
	@Autowired
	private JjszSevice jjszSevice;
	@Autowired
	private FXRYReportOrgService fxryReportOrgService;
	@Autowired
	private LocationSenderService locationSenderService;

	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 用于存储协议中的类型和显示的报警类型的差别
	 */
	private static Map<String, String> alarmType = new HashMap<String, String>();
	static {
		alarmType.put(XMPPConstants.XMPP_ALARM_TYPE_BREAK,
				XMPPConstants.ALARM_TYPE_BREAK);
		alarmType.put(XMPPConstants.XMPP_ALARM_TYPE_SLIENT,
				XMPPConstants.ALARM_TYPE_SLIENT);
		alarmType.put(XMPPConstants.XMPP_ALARM_TYPE_OUTRANGE,
				XMPPConstants.ALARM_TYPE_OUTRANGE);
		alarmType.put(XMPPConstants.XMPP_ALARM_TYPE_NOTOUTRANGE,
				XMPPConstants.ALARM_TYPE_OUTRANGE);
		alarmType.put(XMPPConstants.XMPP_ALARM_TYPE_GATHER,
				XMPPConstants.ALARM_TYPE_GATHER);
		alarmType.put(XMPPConstants.XMPP_ALARM_TYPE_EXCEPTION,
				XMPPConstants.ALARM_TYPE_EXCEPTION);
		alarmType.put(XMPPConstants.XMPP_ALARM_TYPE_LOWVOL_WB,
				XMPPConstants.ALARM_TYPE_LOWVOL);
		alarmType.put(XMPPConstants.XMPP_ALARM_TYPE_LOWVOL_ZJ,
				XMPPConstants.ALARM_TYPE_LOWVOL);
		alarmType.put(XMPPConstants.XMPP_ALARM_TYPE_DISAPPEAR_WB,
				XMPPConstants.ALARM_TYPE_OUTRANGE);
		/**
		 * 越界报警
		 */
		alarmType.put(XMPPConstants.XMPP_ALARM_TYPE_AREA,
				XMPPConstants.ALARM_TYPE_AREA);
	}

	/**
	 * Description:创建报警信息
	 * 
	 * @author Shuzz
	 * @since 2014年7月21日上午9:22:07
	 * @param typeXmpp
	 * @param adjustType
	 * @param alarmtype
	 * @param time
	 * @param token
	 * @return
	 */
	private String createAlarm(String X, String Y, AlarmTypeXmpp typeXmpp,
			String adjustType, String type, Date time, AuthToken token) {
		String alarmtype = alarmType.get(type);
		AlarmXmpp alarm = new AlarmXmpp();
		alarm.setAlarmLevel(typeXmpp.getAlarmLevel());
		alarm.setAlarmLevel(typeXmpp.getAlarmLevel());
		alarm.setAlarmType(alarmtype);
		alarm.setAlarmTime(time);
		alarm.setExecuteUnit(token.getOrgId());
		alarm.setFxryId(token.getFxryId());
		alarm.setIsautoAlert(typeXmpp.getIsautoAlert());
		// ---------2014-10-31区分不同的低电报警---------
		String alert = null != typeXmpp.getAlertContent() ? typeXmpp
				.getAlertContent() : "";
		String alacon = null != typeXmpp.getAlarmContent() ? typeXmpp
				.getAlarmContent() : "";
		if (XMPPConstants.XMPP_ALARM_TYPE_LOWVOL_WB.equals(type)) {
			alert = alert + "(腕表低电)";
			alacon = alacon + "(腕表低电)";
		} else if (XMPPConstants.XMPP_ALARM_TYPE_LOWVOL_ZJ.equals(type)) {
			alert = alert + "(主机低电)";
			alacon = alacon + "(主机低电)";
		}
		alarm.setAlert(alert);
		alarm.setAlarm(alacon);
		alarm.setAdjustType(adjustType);
		alarm.setStatus("2");
		Double x = DateFormat.tranStrToDoubleNull(X);
		Double y = DateFormat.tranStrToDoubleNull(Y);
		alarm.setX(x);
		alarm.setY(y);
		dbService.saveAlarm(alarm);
		// 发送短信
		try {
			// 短信警告提醒发送给服刑人员
			FxryXmpp fxry = dbService.getFxryById(token.getFxryId());
			if (typeXmpp.getIsautoAlert() == 1) {
				if (fxry != null && !CommonUtils.isNull(alarm.getAlert())) {
					CcDzjgsbDevice device = dbService
							.findSuperviseDeviceByNumber(token
									.getDeviceNumber());
					dxptService.sendPlatformMessage(device.getPhoneNumber(),
							fxry.getName(), alarm.getAlert());
				}
			}
			// 短信报警提醒发送给责任干警
			if (typeXmpp.getIsautoAlarm() == 1) {
				FXRYReportOrg reportOrg = fxryReportOrgService
						.getByFXRYId(token.getFxryId());
				if (reportOrg != null && reportOrg.getPhone() != null
						&& reportOrg.getPersonId() != null) {
					String name = dbService.getPersonName(reportOrg
							.getPersonId());
					String content = "";
					if (null != alarm.getAlarm()) {
						content = alarm.getAlarm();
					}
					if (fxry != null) {
						content += "服刑人员：" + fxry.getName();
						if (null != fxry.getPhone()) {
							content += ",服刑人员电话：" + fxry.getPhone();
						} else {
							content += ",服刑人员电话：无";
						}
						dxptService.sendPlatformMessage(reportOrg.getPhone(),
								name, content);
					}
				}
			}
		} catch (Exception e) {
			log.error("", e);
		}
		return alarm.getAlarmId();
	}

	/**
	 * Description:处理报警信息
	 * 
	 * @author Shuzz
	 * @since 2014年7月22日上午9:30:17
	 * @param type
	 * @param time
	 * @param token
	 */
	private String handleAlarm(String X, String Y, Element alm, String type,
			Date time, AuthToken token) {
		String resultType = null;
		try {
			String alarmtype = alarmType.get(type);
			AlarmTypeXmpp typeXmpp = dbService.findAlramTypeByType(alarmtype);
			String adjustType = dbService.findAdjystType(token.getFxryId());
			AlarmXmpp alarm = null;
			if ("1".equals(typeXmpp.getStatus())) {
				Element toJust = alm.createCopy();
				// 处理超距报警
				if (XMPPConstants.XMPP_ALARM_TYPE_OUTRANGE.equals(type)) {
					log.debug("进入超距报警处理");
					String alarmId = null;
					//---------2014-10-31超距报警不再生成报警，只记录到数据库中---------
					/*boolean isTimeOut = dbService.isTimeOutOfOutrange(
							token.getFxryId(), time, typeXmpp.getSparateTime());
					if (isTimeOut) {
						alarm = dbService.findAlarm(token.getFxryId(), alarmtype,
								time, -10);
						if (alarm != null) {
							alarm.setAlarmTime(time);
							alarmId = alarm.getAlarmId();
							dbService.saveAlarm(alarm);
						} else {
							alarmId = createAlarm(X, Y, typeXmpp, adjustType,
									alarmtype, time, token);
							resultType = alarmtype;
							Element e = toJust.addElement("alarmId");
							if (alarmId != null) {
								e.setText(alarmId);
							}
							emMessageSendService.resendToJustice(toJust, token,
									"dzjg:iq:alert");
							dbService.alarmMsgOutrange(token.getFxryId(), alarmId);
						}
					}*/
					MsgOutrangeXmpp outrange = new MsgOutrangeXmpp();
					outrange.setAlarmId(alarmId);
					outrange.setStatus("2");
					outrange.setFxryId(token.getFxryId());
					outrange.setMsgTime(time);
					dbService.saveMsgOutrange(outrange);
					log.debug("处理超距报警完成");
				} else if (XMPPConstants.XMPP_ALARM_TYPE_NOTOUTRANGE
						.equals(type)) {
					// 超距报警解除
					log.debug("解除超距报警");
					alarm = dbService.findAlarm(token.getFxryId(), alarmtype,
							time, -30);
					if (alarm != null) {
						alarm.setHandleTime(new Date());
						alarm.setHandler("社区服刑人员");
						alarm.setRecord("自行解除");
						alarm.setStatus("1");
						dbService.saveAlarm(alarm);
						dbService.handleOutrangeByAlarm(alarm.getAlarmId());
						
						//创建完报警信息后生成XMPP格式的报警发送给移动执法终端
						String alarmId = alarm.getAlarmId();
						Element e = toJust.addElement("alarmId");
						if (alarmId != null) {
							e.setText(alarmId);
						}
						emMessageSendService.resendToJustice(toJust, token,
								"dzjg:iq:alert");
					} else {
						dbService.handleOutrangeByFxry(token.getFxryId());
					}
				} else {
					// 处理其他报警
					boolean alarmFlag = true;
					if (!CommonUtils.isNull(token.getFxryId())) {
						int destSecond = Config.getInt("xmpp.alarm.same.time",
								0);
						alarmFlag = dbService.hasSameAlarm(token.getFxryId(),
								alarmtype, time, -destSecond);
					}
					if (!alarmFlag) {
						//如果某段时间内有相同报警则不新产生报警
						String alarmId = createAlarm(X, Y, typeXmpp,
								adjustType, type, time, token);
						resultType = alarmtype;
						//创建完报警信息后生成XMPP格式的报警发送给移动执法终端
						Element e = toJust.addElement("alarmId");
						if (alarmId != null) {
							e.setText(alarmId);
						}
						emMessageSendService.resendToJustice(toJust, token,
								"dzjg:iq:alert");
					}
				}
			}
		} catch (Exception e) {
			log.error("", e);
		}
		return resultType;
	}

	/**
	 * Description:是否聚集报警排除
	 * 
	 * @author Shuzz
	 * @since 2014年7月22日下午2:35:47
	 * @param token
	 * @param token1
	 * @return
	 */
	private boolean isGatherIgnore(AuthToken token, AuthToken token1, Date time) {
		String subOrg = dbService.getSubOrgid(token.getOrgId());
		String subOrg1 = dbService.getSubOrgid(token1.getOrgId());
		if (token.getOrgId().equals(token1.getOrgId())) {
			// 同一个司法所的人员从司法所开始判断
			return jjszSevice.isGatherIgnore(token.getOrgId(),
					token.getFxryId(), token1.getFxryId(), time)
					|| jjszSevice.isGatherIgnore(subOrg, token.getOrgId(),
							token.getOrgId(), time)
					|| jjszSevice.isGatherIgnore(dbService.getRootOrgId(),
							subOrg, subOrg, time);
		} else if (subOrg.equals(subOrg1)) {
			// 同区县从区县局开始判断
			return jjszSevice.isGatherIgnore(subOrg, token.getOrgId(),
					token1.getOrgId(), time)
					|| jjszSevice.isGatherIgnore(dbService.getRootOrgId(),
							subOrg, subOrg1, time);
		} else {
			// 不同区县只能从市局层面判断
			return jjszSevice.isGatherIgnore(dbService.getRootOrgId(), subOrg,
					subOrg1, time);
		}
	}

	/**
	 * Description:处理聚集报警
	 * 
	 * @author Shuzz
	 * @since 2014年7月22日上午10:31:19
	 * @param alm
	 *            xml元素
	 * @param time
	 *            报警时间
	 * @param token
	 *            token信息
	 */
	@SuppressWarnings("unchecked")
	private String handleGather(String X, String Y, Element alm, Date time,
			AuthToken token) {
		String resultType = null;
		log.debug("开始处理聚集报警信息");
		List<Element> ids = alm.element("IDS").elements();
		AlarmTypeXmpp typeXmpp = dbService
				.findAlramTypeByType(XMPPConstants.ALARM_TYPE_GATHER);
		String status = typeXmpp.getStatus();
		Integer gTime = typeXmpp.getGatherTime();
		if (gTime == null) {
			gTime = Config.getInt("xmpp.alarm.gather.time");
		}
		String adjustType = dbService.findAdjystType(token.getFxryId());
		// 针对报警信息中的每个腕表进行处理
		for (Element id : ids) {
			String watchId = id.getTextTrim();
			watchId = watchId.toUpperCase();
			AuthToken otoken = dbService.findFxryByWbnumber(watchId);
			if (otoken != null) {
				try {
					// 首先记录聚集情况信息
					dbService.saveGatherInfo(otoken.getFxryId(),
							token.getFxryId(), time);
					if ("1".equals(status)) {
						// 聚集报警开启，下面计算排除
						if (!isGatherIgnore(token, otoken, time)) {
							// 不在排除范围内的进行计算聚集时间，该计算方法有缺陷
							long i = GatherPersonMap.getInstance()
									.addPerson(token.getFxryId(),
											otoken.getFxryId(), time);
							if (i >= (gTime * 60 * 1000)) {
								// 查询最近一个定位周期内的未处理报警信息，如果没有则新生成一个报警
								AlarmXmpp alarm = dbService.findAlarm(
										token.getFxryId(),
										XMPPConstants.ALARM_TYPE_GATHER, time,
										-12);
								if (alarm == null) {
									String alarmId = createAlarm(X, Y,
											typeXmpp, adjustType,
											XMPPConstants.XMPP_ALARM_TYPE_GATHER,
											time, otoken);
									resultType = XMPPConstants.ALARM_TYPE_GATHER;
									Element toJust = alm.createCopy();
									Element e = toJust.addElement("alarmId");
									if (alarmId != null) {
										e.setText(alarmId);
									}
									emMessageSendService.resendToJustice(
											toJust, token, "dzjg:iq:alert");
								}
							}
						}
					}
				} catch (Exception e) {
					log.error(null, e);
				}
			}
		}
		return resultType;
	}

	/**
	 * Description:处理越界信息
	 * 
	 * @author Shuzz
	 * @since 2014年7月23日上午10:46:16
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param time
	 *            定位时间
	 * @param token
	 *            服刑人员信息
	 */
	private void handleCrossBorder(String X, String Y, Date time,
			AuthToken token, String locId) {
		log.debug("确认越界，开始处理越界信息");
		AlarmTypeXmpp typeXmpp = dbService
				.findAlramTypeByType(XMPPConstants.ALARM_TYPE_AREA);
		String adjustType = dbService.findAdjystType(token.getFxryId());
		Integer limit = typeXmpp.getCrossBorderTime();
		if (limit == null) {
			limit = Config.getInt("xmpp.alarm.cross.time");
		}
		boolean outLimit = dbService.calCrossBorder(token.getFxryId(), limit,
				time);
		if (outLimit) {
			String alarmId = createAlarm(X, Y, typeXmpp, adjustType,
					XMPPConstants.XMPP_ALARM_TYPE_AREA, time, token);
			Element alarm = IQModelUtil.getAlarm(
					XMPPConstants.XMPP_ALARM_TYPE_AREA, time, X, Y);
			Element e = alarm.addElement("alarmId");
			if (alarmId != null) {
				e.setText(alarmId);
			}
			dbService.updateLoc(locId, XMPPConstants.ALARM_TYPE_AREA);
			emMessageSendService.resendToJustice(alarm, token, "dzjg:iq:alert");
		}
	}

	/**
	 * Description:调用系统设置处提供的接口获取该人员越界范围配置
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日上午9:59:09
	 * @param locxmpp
	 * @param token
	 */
	public void calCrossBorder(String x, String y, Date time, AuthToken token,
			String locId) {
		log.debug("开始计算越界信息");
		if (!CommonUtils.isNull(token.getFxryId())
				&& !CommonUtils.isNull(token.getOrgId())) {
			String fxryid = token.getFxryId();
			try {
				SysSetting qxjsys = sysSettingService.getQxjSet(token
						.getOrgId());
				SysSetting rysys = sysSettingService.getFxrySet(fxryid);
				String isEntryFxryid = "", isOutFxryid = "";
				boolean deflag = true;
				if (null != rysys) {
					if (rysys.getStatus().equals("2")) {
						return;
					}
					String dzwl = dzwlService.GetDZWL(fxryid);
					if (dzwl.endsWith("1")) {
						isEntryFxryid = fxryid;
					}
					if (rysys.getRailType().equals("2")) {
						isOutFxryid = sysSettingService.getXzqh(rysys
								.getSettingId());
					} else if (rysys.getRailType().equals("3")) {
						isOutFxryid = fxryid;
						deflag = false;
					}
				}
				if (CommonUtils.isNull(isEntryFxryid)) {
					if (null != qxjsys) {
						isEntryFxryid = dbService.getOrgName(qxjsys.getOrgId());
						String dzwl = dzwlService.GetDZWL(isEntryFxryid);
						if (!dzwl.endsWith("1")) {
							isEntryFxryid = "";
						}
					}
				}
				if (CommonUtils.isNull(isOutFxryid)) {
					if (null != qxjsys) {
						if (qxjsys.getRailType().equals("2")) {
							isOutFxryid = sysSettingService.getXzqh(qxjsys
									.getSettingId());
						} else if (qxjsys.getRailType().equals("3")) {
							isOutFxryid = dbService.getOrgName(qxjsys
									.getOrgId());
							deflag = false;
						} else if (qxjsys.getRailType().equals("1")) {
							isOutFxryid = "北京市";
						}
					} else {
						isOutFxryid = "北京市";
					}
				}
				/**
				 * 判断该人员是否离开限定区域或者是否进入禁入区域
				 * */
				AlarmTypeXmpp alarmtype = dbService
						.findAlramTypeByType(XMPPConstants.ALARM_TYPE_AREA);
				Integer range = alarmtype.getCrossBorderRange();
				if (range == null) {
					range = new Integer(0);
				}
				boolean flag;
				try {
					if (deflag) {
						// 禁止离开区县设置
						flag = dzwlService.IsOutOfRangeDefault(isOutFxryid, x,
								y, range * 1000);
					} else {
						// 禁止离开 自定义区域
						flag = dzwlService.IsOutOfRange(isOutFxryid, x, y,
								range * 1000);
					}
					if (!CommonUtils.isNull(isEntryFxryid)) {
						flag = flag
								|| dzwlService.IsEntryDisallowArea(
										isEntryFxryid, x, y, 0);
					}
					if (flag) {
						dbService.saveMsgCrossBorder(x, y, time, "1",
								token.getFxryId());
						handleCrossBorder(x, y, time, token, locId);
					} else {
						dbService.saveMsgCrossBorder(x, y, time, "2",
								token.getFxryId());
					}
				} catch (Exception e) {
					BusinessHandleScheduler businessHandle = (BusinessHandleScheduler) SpringContextUtil
							.getBean("businessHandleScheduler");
					businessHandle.addCrossBorder(x, y, time, token, locId);
				}
			} catch (Exception e) {
				log.error("越界计算出错", e);
			}
		}
	}

	/**
	 * Description:处理定位
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日上午10:17:48
	 * 
	 * @param X
	 *            经度
	 * @param Y
	 *            纬度
	 * @param Time
	 *            定位时间
	 * @param token
	 *            记录登录信息的token
	 */
	private void handleLocation(String X, String Y, String Time,
			AuthToken token, String alarmType, String locType) {
		double x = DateFormat.tranStrToDouble(X);
		double y = DateFormat.tranStrToDouble(Y);
		Date time = DateFormat.tranStrToDate(Time);
		if (!dbService.hasSameLocation(x, y, time)) {
			//如果该定位点不重复，则将该信息保存入库
			LocationXmpp locxmpp = new LocationXmpp();
			locxmpp.setLocTime(time);
			locxmpp.setLongitude(x);
			locxmpp.setLatitude(y);
			locxmpp.setDeviceId(token.getDeviceId());
			locxmpp.setAlarmType(alarmType);
			locxmpp.setPersonId(token.getFxryId());
			locxmpp.setLocType(locType);
			dbService.saveLoc(locxmpp, token);
			// 保存完之后，如果该定位信息有人员的情况且该人员在监控中
			// 则由其它线程进行越界判断
			if (!CommonUtils.isNull(token.getFxryId())
					&& !dbService.isPauseMonitor(token.getFxryId())) {
				BusinessHandleScheduler businessHandle = (BusinessHandleScheduler) SpringContextUtil
						.getBean("businessHandleScheduler");
				businessHandle.addCrossBorder(X, Y, time, token,
						locxmpp.getLocId());
			}
		}
	}

	/**
	 * Description:则根据主机电话号码发起主动定位请求
	 * 
	 * @author Shuzz
	 * @since 2014年9月5日上午11:21:32
	 * @param deviceNumber
	 *            主机编号
	 */
	private void sendLocationRequest(String deviceNumber, String Time) {
		// 如果主机发送的定位信息不准确则根据主机电话号码发起主动定位请求
		Date time = DateFormat.tranStrToDate(Time);
		if (dbService.needLbs(deviceNumber, time)) {
			CcDzjgsbDevice device = dbService
					.findSuperviseDeviceByNumber(deviceNumber);
			if (device != null) {
				String phone = device.getPhoneNumber();
				if (!CommonUtils.isNull(phone)) {
					LocationMessage message = new LocationMessage(
							LocationConstants.TYPE_DATA);
					message.setContent(phone);
					locationSenderService.addLocationRequest(message);
					dbService.saveLbsReq(deviceNumber, time);
				}
			}
		}
	}

	/**
	 * Description:处理报警消息
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日上午10:03:54
	 * @param alarm
	 * @param token
	 */
	private void handleAlarm(Element alm, AuthToken token) {
		String X = alm.elementText("X");
		String Y = alm.elementText("Y");
		String Time = alm.elementText("alertTime");
		String type = alm.elementText("alertType");
		Date time = DateFormat.tranStrToDate(Time);
		String alarmType = null;
		if (XMPPConstants.XMPP_ALARM_TYPE_GATHER.equals(type)
				&& token.getFxryId() != null) {
			alarmType = handleGather(X, Y, alm, time, token);
		} else {
			alarmType = handleAlarm(X, Y, alm, type, time, token);
		}
	
		if(!XMPPConstants.XMPP_ALARM_TYPE_SLIENT.equals(type) && !XMPPConstants.XMPP_ALARM_TYPE_LOWVOL_WB.equals(type) && !XMPPConstants.XMPP_ALARM_TYPE_LOWVOL_ZJ.equals(type)){
			if (!XMPPConstants.ERROR_LOCATION.equals(X)
					&& !XMPPConstants.ERROR_LOCATION.equals(Y)
					&& !XMPPConstants.ERROR_LOCATION_NULL.equals(X)
					&& !XMPPConstants.ERROR_LOCATION_NULL.equals(Y)) {
				log.debug("开始处理报警信息中携带的定位信息");
				//由于报警信息在处理时会转发给移动执法终端，故处理报警中的定位时不再转发定位
				handleLocation(X, Y, Time, token, alarmType, "GPS");
			} else if (Config.getBoolean("xmpp.LBS.null", true)) {
				//腕表获取不到GPS，且LBS补点开启，则发送LBS定位请求
				sendLocationRequest(token.getDeviceNumber(), Time);
			}
		}
	}

	/**
	 * Description:处理定位消息
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日上午10:03:45
	 * @param loc
	 * @param token 记录登录信息的token
	 */
	private void handleLocation(Element loc, AuthToken token) {
		String X = loc.elementText("X");
		String Y = loc.elementText("Y");
		String Time = loc.elementText("time");
		/*
		 * 腕表上报的定位信息中无该字段，有该字段则是LBS请求到的数据
		 * */
		String type = loc.elementText("type");
		if (CommonUtils.isNull(type)) {
			type = "GPS";
		}
		if (!XMPPConstants.ERROR_LOCATION.equals(X)
				&& !XMPPConstants.ERROR_LOCATION.equals(Y)
				&& !XMPPConstants.ERROR_LOCATION_NULL.equals(X)
				&& !XMPPConstants.ERROR_LOCATION_NULL.equals(Y)) {
			handleLocation(X, Y, Time, token, null, type);
			//处理完定位信息后转发至相应的移动执法终端
			emMessageSendService
					.resendToJustice(loc, token, "dzjg:iq:location");
		} else if (Config.getBoolean("xmpp.LBS.null", true)) {
			//腕表获取不到GPS，且LBS补点开启，则发送LBS定位请求
			sendLocationRequest(token.getDeviceNumber(), Time);
		}
	}

	/**
	 * Description:处理基站信息
	 * 
	 * @author Shuzz
	 * @since 2014年9月10日上午10:15:12
	 * @param loc
	 * @param token 记录登录信息的token
	 */
	private void handleBaseStation(Element basestation, AuthToken token) {
		String bsNum = basestation.elementText("bsNum");
		String bsLac = basestation.elementText("bsLas");
		String bsTime = basestation.elementText("bsTime");
		try {
			Long num = Long.parseLong(bsNum);
			Long lac = Long.parseLong(bsLac);
			BaseStation bs = dbService.findBaseStation(num, lac);
			if (bs != null && bs.getId() != null) {
				if (bs.getId().getLng() != null && bs.getId().getLat() != null) {
					//如果能查询出基站信息并有坐标，则按LBS定位进行处理
					String X = bs.getId().getLng().toString();
					String Y = bs.getId().getLat().toString();
					Element loc = IQModelUtil.newLocation(X, Y, "LBS", bsTime);
					handleLocation(loc, token);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Description:用于处理信息接口
	 * 
	 * @author Shuzz
	 * @since 2014年7月23日上午9:59:27
	 * @param i
	 * @param message
	 */
	@SuppressWarnings("rawtypes")
	public void handleBusiness(int i, Object message) {
		List l = (List) message;
		switch (i) {
		case 0:
			log.debug("开始处理报警信息");
			handleAlarm((Element) l.get(0), (AuthToken) l.get(1));
			break;
		case 1:
			log.debug("开始处理定位信息");
			handleLocation((Element) l.get(0), (AuthToken) l.get(1));
			break;
		case 2:
			log.debug("开始基站信息");
			handleBaseStation((Element) l.get(0), (AuthToken) l.get(1));
			break;
		default:
			break;
		}
	}

	/**
	 * Description:按照周期频率检测已用的设备是否在限定时间内收到定位信息，未收到则报警
	 * 
	 * @author Shuzz
	 * @since 2014年8月25日下午4:57:43
	 */
	public void checkDeviceLocationError() {
		AlarmTypeXmpp typeXmpp = dbService
				.findAlramTypeByType(XMPPConstants.ALARM_TYPE_EXCEPTION);
		//设备异常报警开启才进行检测并报警
		if ("1".equals(typeXmpp.getStatus())) {
			List<String> devices = dbService.findAllUsedDevice();
			// 数据库存放字段变化以前使用的locationError字段废弃了
			Integer error = typeXmpp.getLostContact();
			if (error == null) {
				error = new Integer(1);
			}
			Date d = new Date();
			for (String device : devices) {
				try {
					AuthToken token = dbService.findFxryByDevice(device);
					if (token != null && token.getFxryId() != null) {
						String adjustType = dbService.findAdjystType(token
								.getFxryId());
						// 首先获取使用中的设备的启用时间
						Date run = dbService.findRunning(device);
						// 然后获取定位信息的日志
						XmppLog location = dbService.findOneLogByType(device, "2");

						Date m = null;
						// 排除了启用之前的定位信息日志的影响,防止启用设备后立即报设备异常
						if (null != run) {
							if (location != null) {
								m = run.after(location.getMessageTime()) ? run
										: location.getMessageTime();
							} else {
								m = run;
							}
						}
						if (m != null
								&& (d.getTime() - m.getTime()) > (error * 10 * 60 * 1000)) {
							//确认设备定位异常后，查询24小时内是否有未处理的定位异常报警，有则不产生新的报警
							AlarmXmpp oldAlarm = dbService.findAlarm(
									token.getFxryId(),
									XMPPConstants.ALARM_TYPE_EXCEPTION, d, -1440);
							if (oldAlarm == null) {
								String alarmId = createAlarm(null, null, typeXmpp,
										adjustType,
										XMPPConstants.XMPP_ALARM_TYPE_EXCEPTION, d,
										token);
								//创建完报警信息后生成XMPP格式的报警发送给移动执法终端
								Element alarm = IQModelUtil.getAlarm(
										XMPPConstants.XMPP_ALARM_TYPE_EXCEPTION, d,
										null, null);
								//增加报警的主键，以便移动执法终端处理报警
								Element e = alarm.addElement("alarmId");
								if (alarmId != null) {
									e.setText(alarmId);
								}
								emMessageSendService.resendToJustice(alarm, token,
										"dzjg:iq:alert");
							}
						}
					}
				} catch (Exception e) {
					log.error(null, e);
				}
			}
		}
	}

	/**
	 * Description:发送主动定位请求
	 * 
	 * @author Shuzz
	 * @since 2014年11月26日上午10:22:17
	 */
	public void checkDeviceLBS() {
		try {
			if (Config.getBoolean("xmpp.LBS.auto", false)) {
				List<String> phoneNumbers = dbService
						.findAllUsedDevicePhoneNumber();
				for (String phoneNumber : phoneNumbers) {
					if (!CommonUtils.isNull(phoneNumber)) {
						LocationMessage message = new LocationMessage(
								LocationConstants.TYPE_DATA);
						message.setContent(phoneNumber);
						locationSenderService.addLocationRequest(message);
					}
				}
			}
		} catch (Exception e) {
			log.error(null, e);
		}
	}
}

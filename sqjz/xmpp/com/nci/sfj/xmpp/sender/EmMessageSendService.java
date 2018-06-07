package com.nci.sfj.xmpp.sender;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmpp.packet.IQ;
import org.xmpp.packet.IQ.Type;

import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.em.model.LocationPeriod;
import com.nci.dcs.jzgl.dagl.model.FXRYBaseinfo;
import com.nci.dcs.jzgl.dagl.model.FXRYReportOrg;
import com.nci.sfj.business.model.AlarmTypeXmpp;
import com.nci.sfj.business.model.FxryXmpp;
import com.nci.sfj.business.model.MsgWaitXmpp;
import com.nci.sfj.business.model.OrgXmpp;
import com.nci.sfj.business.service.DbService;
import com.nci.sfj.common.util.Config;
import com.nci.sfj.common.util.DateFormat;
import com.nci.sfj.common.util.XMPPConstants;
import com.nci.sfj.xmpp.auth.AuthToken;
import com.nci.sfj.xmpp.model.IQModelUtil;
import com.nci.sfj.xmpp.session.ClientSession;
import com.nci.sfj.xmpp.session.SessionManager;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * Description:提供向由web端对外进行消息发送
 * 
 * @author Shuzz
 * @since 2014年6月27日下午1:47:11
 */
@Service
public class EmMessageSendService {
	@Autowired
	private XmppSenderService xmppSenderService;
	@Autowired
	private DbService dbService;

	/**
	 * Description:根据定位主机编号发送消息至定位主机
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日下午4:03:31
	 * @param element
	 *            消息内容
	 * @param deviceNumber
	 *            主机编号
	 * @param i
	 *            优先级
	 */
	private String sendIQ(Element element, String deviceNumber, int i) {
		if (null != element) {
			IQ send = new IQ(Type.set);
			send.setChildElement(element.createCopy());
			String id = null;
			switch (i) {
			case 0:
				xmppSenderService.addAlarm(send, deviceNumber + "@"
						+ SessionManager.CLIENT_RESOURCE_NAME);
				id = send.getID();
				break;
			case 1:
				xmppSenderService.addSetting(send, deviceNumber + "@"
						+ SessionManager.CLIENT_RESOURCE_NAME);
				id = send.getID();
				break;
			default:
				break;
			}
			return id;
		}
		return null;
	}

	/**
	 * Description:根据机构发送消息至定位主机
	 * 
	 * @author Shuzz
	 * @since 2014年7月24日下午3:41:37
	 * @param type
	 * @param orgId
	 * @param content
	 */
	private void sendIQByOrg(String type, String orgId, String content,
			List<String> notOrgId) {
		List<String> clients = dbService.findSuperviseDeviceByOrg(orgId,
				notOrgId);
		dbService.saveEmMessageReport(clients, type, content);
	}

	/**
	 * Description:根据人员ID发送消息至定位主机
	 * 
	 * @author Shuzz
	 * @since 2014年7月24日下午3:41:09
	 * @param type
	 * @param orgId
	 * @param content
	 * @param notOrgId
	 */
	private void sendIQByPerson(String type, String fxryId, String content) {
		String deviceNumber = dbService.getFxryById(fxryId).getDeviceNumber();
		dbService.saveEmMessageReport(deviceNumber, type, content);
	}

	private void sendIQByDevice(String type, String deviceNumber, String content) {
		dbService.saveEmMessageReport(deviceNumber, type, content);
	}

	/**
	 * Description: 用于区县或市局采集频率设置
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日下午4:00:55
	 * @param sys
	 *            系统默认设置
	 * @param orgId
	 *            机构ID
	 */
	public void setFrequency(String settingId, String orgId,
			List<String> notOrgId) {
		List<String> clients = dbService.findFrequencyDevice(orgId, notOrgId);
		dbService.saveEmMessageReport(clients, XMPPConstants.FREQUENCY,
				settingId);
	}

	/**
	 * Description:针对个人设置采集频率
	 * 
	 * @author Shuzz
	 * @since 2014年7月24日下午3:47:14
	 * @param settingId
	 * @param fxryId
	 */
	public void setFrequency(String settingId, String fxryId) {
		sendIQByPerson(XMPPConstants.FREQUENCY, fxryId, settingId);
	}

	/**
	 * Description:针对自检时的设备设置采集频率
	 * 
	 * @author Shuzz
	 * @since 2014年8月4日下午4:53:37
	 * @param settingId
	 * @param deviceNumber
	 * @return
	 */
	public String setFrequencyByDevice(String settingId, String deviceNumber) {
		Element element = null;
		List<LocationPeriod> lps = dbService.getLocationPeriod(settingId);
		if (CommonUtils.isNotNull(lps)) {
			element = IQModelUtil.getFrequency(lps);
		}
		return sendIQ(element, deviceNumber, 1);
	}

	/**
	 * Description:根据设备所属的机构设置联系电话
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日下午3:12:56
	 * @param telPhones
	 *            电话号码列表
	 * @param orgId
	 *            机构ID
	 */
	public void setTelPhonesByOrg(List<String> telPhones, String orgId) {
		StringBuffer sb = new StringBuffer();
		for (String telPhone : telPhones) {
			sb.append(telPhone);
			sb.append(",");
		}
		if (sb.length() > 0) {
			sendIQByOrg(XMPPConstants.PHONES, orgId,
					sb.substring(0, sb.length() - 1), null);
		}
	}

	/**
	 * Description:根据设备编号设置联系电话
	 * 
	 * @author Shuzz
	 * @since 2014年8月13日上午11:42:15
	 * @param telPhones
	 * @param orgId
	 */
	public void setTelPhonesByDevice(List<String> telPhones, String deviceNumber) {
		Element element = IQModelUtil.getTelPhones(telPhones);
		sendIQ(element, deviceNumber, 1);
	}

	public String setTime(String deviceNumber) {
		// 时间信息
		Element time = IQModelUtil.getTime();
		return sendIQ(time, deviceNumber, 1);
	}

	/**
	 * Description:主机腕带配对
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日下午3:18:25
	 * @param deviceNumber
	 *            主机编号
	 * @param pairNumber
	 *            腕带编号
	 */
	public String pairDevice(String deviceNumber, String pairNumber) {
		Element frequency = IQModelUtil.getPair(pairNumber);
		return sendIQ(frequency, deviceNumber, 0);
	}

	/**
	 * Description:通知主机关机
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日下午3:37:57
	 * @param deviceNumber
	 *            主机编号
	 */
	public String powerOff(String deviceNumber) {
		Element frequency = IQModelUtil.getPowerOff("off");
		return sendIQ(frequency, deviceNumber, 1);
	}

	public void powerOffNotNow(String deviceNumber) {
		sendIQByDevice(XMPPConstants.POWEROFF, deviceNumber, null);
	}

	/**
	 * Description:服务器通知主机停止工作或开始工作
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日下午3:38:42
	 * @param run
	 *            true：开始工作；false：停止工作
	 * @param deviceNumber
	 *            主机编号
	 */
	public String running(boolean run, String deviceNumber) {
		Element frequency = IQModelUtil.getRunning(run);
		return sendIQ(frequency, deviceNumber, 1);
	}

	/**
	 * Description:主机低电报警阈值:设置主机电压低于该值(%)时，发送主机低电压报警
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日下午3:59:35
	 * @param voltage
	 *            电压值
	 * @param orgId
	 *            机构ID
	 */
	public void setVoltageValByOrg(String voltage, String orgId) {
		sendIQByOrg(XMPPConstants.VOLTAGE, orgId, voltage, null);
	}

	/**
	 * Description:主机低电报警阈值:设置主机电压低于该值(%)时，发送主机低电压报警
	 * 
	 * @author Shuzz
	 * @since 2014年8月4日下午2:07:06
	 * @param voltage
	 *            电压值
	 * @param deviceNumber
	 *            设备编号
	 * @return
	 */
	public String setVoltageValByDevice(String voltage, String deviceNumber) {
		Element frequency = IQModelUtil.getVoltageVal(voltage);
		return sendIQ(frequency, deviceNumber, 1);
	}

	/**
	 * Description:静默时间设置
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日下午3:59:12
	 * @param time
	 *            分钟
	 * @param orgId
	 *            机构ID
	 */
	public void setSilentListByOrg(String time, String orgId) {
		sendIQByOrg(XMPPConstants.SILENT, orgId, time, null);
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @since 2014年8月4日下午2:14:33
	 * @param time
	 * @param deviceNumber
	 * @return
	 */
	public String setSilentListByDevice(String time, String deviceNumber) {
		Element frequency = IQModelUtil.getSilent(time);
		return sendIQ(frequency, deviceNumber, 1);
	}

	/**
	 * Description:向移动端转发
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日下午1:42:27
	 * @param iq
	 * @param orgId
	 */
	public void resendToJustice(Element element, AuthToken token,
			String namespace) {
		String orgId = token.getOrgId();
		List<String> justices = SessionManager.getInstance().getJusticeByOrg(
				orgId);
		for (String justice : justices) {
			IQ resend = new IQ(Type.set);
			Element send = element.createCopy();
			Element deviceNumber = send.addElement("deviceNumber");
			deviceNumber.setText(token.getDeviceNumber());
			Element packetId = send.addElement("packetId");
			packetId.setText(resend.getID());
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat wsf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (namespace.contains("location")) {
				Element time = send.element("time");
				String locTime = time.getText();
				time.setText(DateFormat.changeDateFormat(sf, wsf, locTime));
				resend.setChildElement(send);
				xmppSenderService.addlocation(resend, justice);
			} else if (namespace.contains("alert")) {
				Element time = send.element("alertTime");
				String locTime = time.getText();
				time.setText(DateFormat.changeDateFormat(sf, wsf, locTime));
				resend.setChildElement(send);
				xmppSenderService.addAlarm(resend, justice);
			} else {
				resend.setChildElement(send);
				xmppSenderService.addSetting(resend, justice);
			}
		}
	}

	public boolean checkOnline(String deviceNumber) {
		String userJid = deviceNumber + "@"
				+ SessionManager.CLIENT_RESOURCE_NAME;
		return SessionManager.getInstance().checkOnline(userJid);
	}

	/**
	 * Description:查找对该主机的设置并发送出去
	 * 
	 * @author Shuzz
	 * @since 2014年7月3日下午3:23:32
	 * @param deviceNumber
	 */
	@SuppressWarnings("unchecked")
	public void resendToClient(String deviceNumber) {
		List<MsgWaitXmpp> msgs = dbService.findMsgWaits(deviceNumber);
		for (MsgWaitXmpp msg : msgs) {
			Element element = null;
			if (XMPPConstants.FREQUENCY.equals(msg.getType())) {
				List<LocationPeriod> lps = null;
				if (msg.getContent() != null) {
					lps = dbService.getLocationPeriod(msg.getContent());
				} else {
					lps = new ArrayList<LocationPeriod>();
					LocationPeriod lp = new LocationPeriod();
					lp.setStartTime("0:00");
					lp.setEndTiem("24:00");
					lp.setSpace((short) 10);
					lps.add(lp);
				}
				if (CommonUtils.isNotNull(lps)) {
					element = IQModelUtil.getFrequency(lps);
				}
			} else if (XMPPConstants.SILENT.equals(msg.getType())) {
				element = IQModelUtil.getSilent(msg.getContent());
			} else if (XMPPConstants.PHONES.equals(msg.getType())) {
				List<String> telPhones = new ArrayList<String>(
						Arrays.asList(msg.getContent().split(",")));
				element = IQModelUtil.getTelPhones(telPhones);
			} else if (XMPPConstants.VOLTAGE.equals(msg.getType())) {
				element = IQModelUtil.getVoltageVal(msg.getContent());
			} else if (XMPPConstants.RUN.equals(msg.getType())) {
				element = IQModelUtil.getRunning(Boolean.parseBoolean(msg
						.getContent()));
			} else if (XMPPConstants.POWEROFF.equals(msg.getType())) {
				element = IQModelUtil.getPowerOff("off");
				dbService.accomplishMsg(deviceNumber, XMPPConstants.POWEROFF);
			}
			sendIQ(element, deviceNumber, 1);
		}
	}

	/**
	 * Description:设备与人员绑定时使用，在内存中关联设备和人员，并且下发电话号码和开始工作信息
	 * 
	 * @author Shuzz
	 * @since 2014年8月11日上午10:56:46
	 * @param deviceNumber
	 * @param fxryId
	 */
	public void equip(FXRYBaseinfo fxry) {
		if (fxry != null) {
			String deviceNumber = fxry.getDeviceCode();
			String fxryId = fxry.getId();
			if (!CommonUtils.isNull(fxry.getDeviceCode())
					&& !CommonUtils.isNull(fxryId)) {
				String userName = deviceNumber + "@"
						+ SessionManager.CLIENT_RESOURCE_NAME;
				ClientSession session = SessionManager.getInstance()
						.getSessionByDeviceNumber(deviceNumber);
				if (session != null) {
					AuthToken token = session.getUserId(userName);
					if (token != null) {
						token.setFxryId(fxryId);
						session.addUser(userName, token);
					}
				}
				// 下发电话号码和主机开始工作
				OrgXmpp org = dbService.getOrg(fxry.getResponseOrg());
				List<String> telPhones = new ArrayList<String>();
				telPhones.add(fxry.getReport().getPhone());
				if (org != null && CommonUtils.isNull(org.getPhone())) {
					telPhones.add(org.getPhone());
				}
				// 短信平台号码
				String shortMessage = Config.getString("xmpp.set.shortmsg.num");
				if (!CommonUtils.isNull(shortMessage)) {
					telPhones.add(shortMessage);
				}
				StringBuffer sb = new StringBuffer();
				for (String telPhone : telPhones) {
					sb.append(telPhone);
					sb.append(",");
				}
				dbService.accomplishMsg(deviceNumber);
				running(true, deviceNumber);
				sendIQByDevice(XMPPConstants.PHONES, deviceNumber,
						sb.substring(0, sb.length() - 1));
			}
		}
	}

	/**
	 * Description:从内存中移除设备和人员的关联，并发送停止工作的命令
	 * 
	 * @author Shuzz
	 * @since 2014年8月11日上午10:56:43
	 * @param deviceNumber
	 */
	public void unequip(String deviceNumber) {
		if (!CommonUtils.isNull(deviceNumber)) {
			String userName = deviceNumber + "@"
					+ SessionManager.CLIENT_RESOURCE_NAME;
			ClientSession session = SessionManager.getInstance()
					.getSessionByDeviceNumber(deviceNumber);
			if (session != null) {
				AuthToken token = session.getUserId(userName);
				if (token != null) {
					token.setFxryId(null);
					session.addUser(userName, token);
				}
			}
			// 防止解除绑定时设备不在线，而存储到数据库等待主机上报信息时进行发送
			sendIQByDevice(XMPPConstants.RUN, deviceNumber, "false");
			sendIQByDevice(XMPPConstants.POWEROFF, deviceNumber, null);
		}
	}

	// ---------自检接口---------
	/**
	 * Description:获取主机硬盘容量
	 * 
	 * @author Shuzz
	 * @since 2014年8月12日上午9:48:24
	 * @param deviceNumber
	 *            主机编号
	 */
	public String getDiskInfo(String deviceNumber) {
		Element iq = IQModelUtil.getDisk();
		return sendIQ(iq, deviceNumber, 1);
	}

	/**
	 * Description:获取主机或腕表电量信息
	 * 
	 * @author Shuzz
	 * @since 2014年8月12日上午9:49:52
	 * @param deviceNumber
	 *            主机编号
	 * @param type
	 *            phone：主机，watch：腕表
	 */
	public String getEQuantityInfo(String deviceNumber, String type) {
		Element iq = IQModelUtil.getEQuantity(type);
		return sendIQ(iq, deviceNumber, 1);
	}

	/**
	 * Description:获取主机CPU信息
	 * 
	 * @author Shuzz
	 * @since 2014年8月12日上午9:50:44
	 * @param deviceNumber
	 *            主机编号
	 */
	public String getCPUInfo(String deviceNumber) {
		Element iq = IQModelUtil.getCPU();
		return sendIQ(iq, deviceNumber, 1);
	}

	/**
	 * Description:获取主机内存信息
	 * 
	 * @author Shuzz
	 * @since 2014年8月12日上午9:51:58
	 * @param deviceNumber
	 */
	public String getMemoryInfo(String deviceNumber) {
		Element iq = IQModelUtil.getMemory();
		return sendIQ(iq, deviceNumber, 1);
	}

	/**
	 * Description:获取主机手机号
	 * 
	 * @author Shuzz
	 * @since 2014年8月12日上午9:53:37
	 * @param deviceNumber
	 */
	public String getSIMInfo(String deviceNumber) {
		Element iq = IQModelUtil.getPhone();
		return sendIQ(iq, deviceNumber, 1);
	}

	/**
	 * Description:检测主机震动功能
	 * 
	 * @author Shuzz
	 * @since 2014年8月12日上午9:53:50
	 * @param deviceNumber
	 */
	public String shakeTest(String deviceNumber) {
		Element iq = IQModelUtil.getShake();
		return sendIQ(iq, deviceNumber, 1);
	}

	/**
	 * Description:检测腕表闭合状态
	 * 
	 * @author Shuzz
	 * @since 2014年8月12日上午9:53:59
	 * @param deviceNumber
	 */
	public String getWatchCloseState(String deviceNumber) {
		Element iq = IQModelUtil.getCloseState();
		return sendIQ(iq, deviceNumber, 1);
	}

	/**
	 * Description:检测设备工作情况
	 * 
	 * @author Shuzz
	 * @since 2014年8月12日上午9:54:40
	 * @param deviceNumber
	 * @param type
	 *            phone：主机，watch：腕表
	 */
	public String getWorkState(String deviceNumber, String type) {
		Element iq = IQModelUtil.getWorkState(type);
		return sendIQ(iq, deviceNumber, 1);
	}

	public String getLocation(String deviceNumber) {
		Element iq = IQModelUtil.getnLocation();
		return sendIQ(iq, deviceNumber, 1);
	}

	/**
	 * Description:为MQTT协议中主机重启新增
	 * 
	 * @author Shuzz
	 * @since 2015年1月30日上午11:25:35
	 * @param deviceNumber
	 * @return
	 */
	public String powerReset(String deviceNumber) {
		Element frequency = IQModelUtil.getPowerOff("reset");
		return sendIQ(frequency, deviceNumber, 1);
	}

	/**
	 * Description:用于登录后下发所有配置信息
	 * 
	 * @author Shuzz
	 * @since 2015年6月29日上午10:08:23
	 * @param deviceNumber
	 * @param fxryId
	 */
	public void setAllToClient(String deviceNumber, String fxryId) {
		FxryXmpp fxry = dbService.getFxryById(fxryId);
		if (fxry != null) {
			// 发送定位周期
			List<LocationPeriod> lps = null;
			String settingId = dbService.findSysSettingByDevice(deviceNumber);
			if (!CommonUtils.isNull(settingId)) {
				lps = dbService.getLocationPeriod(settingId);
			}
			if (CommonUtils.isNull(lps)) {
				lps = new ArrayList<LocationPeriod>();
				LocationPeriod lp = new LocationPeriod();
				lp.setStartTime("0:00");
				lp.setEndTiem("24:00");
				lp.setSpace((short) 10);
				lps.add(lp);
			}
			Element element = IQModelUtil.getFrequency(lps);
			sendIQ(element, deviceNumber, 1);
			// 发送静默时间设置
			AlarmTypeXmpp slient = dbService
					.findAlramTypeByType(XMPPConstants.ALARM_TYPE_SLIENT);
			Integer time = new Integer(1);
			if (null != slient && slient.getSilentTime() != null) {
				time = slient.getSilentTime();
			}
			time = time * 60;
			setSilentListByDevice(time.toString(), deviceNumber);
			// 发送低电阀值
			AlarmTypeXmpp lowvol = 
					dbService.findAlramTypeByType(XMPPConstants.ALARM_TYPE_LOWVOL);
			String lowVol="30";
			if(null!=lowvol&&null!=lowvol.getSparateRange()){
				lowVol=Long.toString(lowvol.getSparateRange());
			}
			setVoltageValByDevice(lowVol, deviceNumber);
			// 下发电话号码
			OrgXmpp org = dbService.getOrg(fxry.getOrg());
			List<String> telPhones = new ArrayList<String>();
			FXRYReportOrg reportOrg = dbService.findReportByFxryId(fxryId);
			if (reportOrg != null && !CommonUtils.isNull(reportOrg.getPhone())) {
				telPhones.add(reportOrg.getPhone());
			}
			if (org != null && !CommonUtils.isNull(org.getPhone())) {
				telPhones.add(org.getPhone());
			}
			// 短信平台号码
			String shortMessage = Config.getString("xmpp.set.shortmsg.num");
			if (!CommonUtils.isNull(shortMessage)) {
				telPhones.add(shortMessage);
			}
			setTelPhonesByDevice(telPhones, deviceNumber);
		} else {
			running(false, deviceNumber);
		}
	}
}

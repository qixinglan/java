package com.nci.sfj.xmpp.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.dom4j.Element;
import org.xmpp.packet.IQ;

import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.em.model.LocationPeriod;
import com.nci.sfj.common.util.DateFormat;

/**
 * Description:生成XMPP协议所有发送内容的工具类
 * 
 */
public class IQModelUtil extends IQ {

	/**
	 * Description:生成定位采集周期XML内容
	 * 
	 * @param lps
	 *            定位采集周期实体list
	 * @return
	 */
	public static Element getFrequency(List<LocationPeriod> lps) {
		Element frequency = docFactory.createElement("frequency");
		frequency.addNamespace("", "dzjg:iq:frequency");
		for (LocationPeriod lp : lps) {
			Element freq = docFactory.createElement("freq");
			Element value = docFactory.createElement("value");
			value.addText(lp.getSpace().toString());
			Element startTime = docFactory.createElement("startTime");
			startTime.addText(lp.getStartTime());
			Element endTime = docFactory.createElement("endTime");
			endTime.addText(lp.getEndTiem());
			freq.add(value);
			freq.add(startTime);
			freq.add(endTime);
			frequency.add(freq);
		}
		return frequency;
	}

	/**
	 * Description:生成电话号码XML内容
	 * 
	 * @param phones
	 *            电话号码list
	 * @return
	 */
	public static Element getTelPhones(List<String> phones) {
		Element telPhones = docFactory.createElement("telPhones");
		for (String phone : phones) {
			Element telephone = docFactory.createElement("telephone");
			telephone.addText(phone);
			telPhones.add(telephone);
		}
		telPhones.addNamespace("", "dzjg:iq:telPhones");
		return telPhones;
	}

	/**
	 * Description:生成腕表和主机配对XML内容
	 * 
	 * @param pairNumber
	 *            主机对应配对的腕表编号
	 * @return
	 */
	public static Element getPair(String pairNumber) {
		Element pair = docFactory.createElement("pair");
		pair.addNamespace("", "dzjg:iq:pair");
		Element ID = docFactory.createElement("ID");
		ID.addText(pairNumber);
		pair.add(ID);
		return pair;
	}

	/**
	 * Description:获取通知主机关机或是重启的XML内容
	 * 
	 * @param off
	 *            关机"off" 重启"reset"
	 * @return
	 */
	public static Element getPowerOff(String off) {
		Element power = docFactory.createElement("power");
		power.addNamespace("", "dzjg:iq:power");
		Element value = docFactory.createElement("value");
		value.addText(off);
		power.add(value);
		return power;
	}

	/**
	 * Description:获取通知主机开始或停止工作的XML内容
	 * 
	 * @param run
	 *            开机true 关机false
	 * @return
	 */
	public static Element getRunning(boolean run) {
		Element running = docFactory.createElement("running");
		running.addNamespace("", "dzjg:iq:running");
		Element value = docFactory.createElement("value");
		value.addText(run ? "on" : "off");
		running.add(value);
		return running;
	}

	/**
	 * Description:获取主机低电报警阈值设置的XML内容
	 * 
	 * @param voltageVal
	 *            电压值
	 * @return
	 */
	public static Element getVoltageVal(String voltageVal) {
		Element voltage = docFactory.createElement("voltageVal");
		voltage.addNamespace("", "dzjg:iq:voltageVal");
		Element value = docFactory.createElement("value");
		value.addText(voltageVal);
		voltage.add(value);
		return voltage;
	}

	/**
	 * Description: 设置静默周期的XML内容，应该扩展为定位周期的形式，现系统静默周期就一个，考虑扩展
	 * 
	 * @param time
	 * @return
	 */
	public static Element getSilent(String time) {
		Element silentList = docFactory.createElement("silentList");
		silentList.addNamespace("", "dzjg:iq:silentList");
		Element silent = docFactory.createElement("silent");
		Element value = docFactory.createElement("value");
		value.addText(time);
		Element startTime = docFactory.createElement("startTime");
		startTime.addText("0");
		Element endTime = docFactory.createElement("endTime");
		endTime.addText("24");
		silent.add(value);
		silent.add(startTime);
		silent.add(endTime);
		silentList.add(silent);
		return silentList;
	}

	/**
	 * Description:用于获取报警信息的XML内容，主要用于系统后台生成的报警类型，主要是设备异常和越界
	 * 
	 * @param type
	 *            报警类型
	 * @param time
	 *            报警生成时间
	 * @param x
	 *            经度
	 * @param y
	 *            纬度
	 * @return
	 */
	public static Element getAlarm(String type, Date time, String x, String y) {
		Element alert = docFactory.createElement("alert");
		alert.addNamespace("", "dzjg:iq:alert");
		Element alertType = docFactory.createElement("alertType");
		alertType.addText(type);
		Element alertTime = docFactory.createElement("alertTime");
		alertTime.addText(DateFormat.tranDateToStr(time));
		Element X = docFactory.createElement("X");
		if (!CommonUtils.isNull(x)) {
			X.addText(x);
		}
		Element Y = docFactory.createElement("Y");
		if (!CommonUtils.isNull(y)) {
			Y.addText(y);
		}
		alert.add(alertType);
		alert.add(alertTime);
		alert.add(X);
		alert.add(Y);
		return alert;
	}

	/**
	 * Description:生成设置主机时间的XML内容
	 * 
	 * @return
	 */
	public static Element getTime() {
		Element time = docFactory.createElement("time");
		time.addNamespace("", "dzjg:iq:time");
		Element value = docFactory.createElement("value");
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		value.addText(sf.format(new Date()));
		time.add(value);
		return time;
	}

	// ---------自检新增接口---------
	/**
	 * Description:设置XML的更节点以及其nameSpace
	 *
	 * @author Shuzz
	 * @param nameSpace
	 * @return
	 * @since 2015年4月10日上午9:01:51
	 */
	public static Element getRoot(String nameSpace) {
		Element root = docFactory.createElement(nameSpace);
		root.addNamespace("", "dzjg:iq:" + nameSpace);
		return root;
	}

	private static Element newTime() {
		Element time = docFactory.createElement("time");
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		time.addText(sf.format(new Date()));
		return time;
	}

	public static Element getDisk() {
		Element time = getRoot("disk");
		time.add(newTime());
		return time;
	}

	public static Element getEQuantity(String type) {
		Element time = getRoot("eQuantity");
		Element eType = docFactory.createElement("type");
		eType.addText(type);
		time.add(eType);
		return time;
	}

	public static Element getCPU() {
		Element time = getRoot("CPU");
		time.add(newTime());
		return time;
	}

	public static Element getMemory() {
		Element time = getRoot("memory");
		time.add(newTime());
		return time;
	}

	public static Element getPhone() {
		Element time = getRoot("phone");
		time.add(newTime());
		return time;
	}

	public static Element getShake() {
		Element time = getRoot("shake");
		time.add(newTime());
		return time;
	}

	public static Element getCloseState() {
		Element time = getRoot("closeState");
		time.add(newTime());
		return time;
	}

	public static Element getWorkState(String type) {
		Element time = getRoot("workState");
		Element eType = docFactory.createElement("type");
		eType.addText(type);
		time.add(eType);
		return time;
	}

	public static Element getnLocation() {
		Element time = getRoot("nLocation");
		time.add(newTime());
		return time;
	}

	public static Element newLocation(String x, String y, String tyep,
			String time) {
		Element loc = getRoot("location");
		Element X = docFactory.createElement("X");
		X.setText(x);
		Element Y = docFactory.createElement("Y");
		Y.setText(y);
		loc.add(X);
		loc.add(Y);
		Element type = docFactory.createElement("type");
		type.setText(tyep);
		loc.add(type);
		if (time == null || time.equals("")) {
			loc.add(newTime());
		} else {
			Element t = docFactory.createElement("time");
			t.addText(time);
			loc.add(t);
		}
		return loc;
	}
}

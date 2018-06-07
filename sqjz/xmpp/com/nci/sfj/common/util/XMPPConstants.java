package com.nci.sfj.common.util;

//different from online
//import com.nci.dcs.common.utils.EncodingUtil;

public class XMPPConstants {
	// ---------下发消息的数据类型---------
	/**
	 * Description:定位信息发送频率设置消息的数据类型
	 */
	public static final String FREQUENCY = "frequency";
	/**
	 * Description:静默时间设置消息的数据类型
	 */
	public static final String SILENT = "silentList";
	/**
	 * Description:电话号码设置消息的数据类型
	 */
	public static final String PHONES = "telPhones";
	/**
	 * Description:低电报警设置消息的数据类型
	 */
	public static final String VOLTAGE = "voltageVal";
	/**
	 * Description:停止工作消息的数据类型
	 */
	public static final String RUN = "running";
	/**
	 * Description:关机消息的数据类型
	 */
	public static final String POWEROFF = "poweroff";
	// ---------XMPP协议中的报警类型---------
	/**
	 * 破拆报警
	 * */
	public static final String XMPP_ALARM_TYPE_BREAK = "001";
	/**
	 * 静默报警
	 * */
	public static final String XMPP_ALARM_TYPE_SLIENT = "002";
	/**
	 * 超距报警
	 * */
	public static final String XMPP_ALARM_TYPE_OUTRANGE = "003";
	/**
	 * 超距报警解除
	 * */
	public static final String XMPP_ALARM_TYPE_NOTOUTRANGE = "004";
	/**
	 * 聚集报警
	 * */
	public static final String XMPP_ALARM_TYPE_GATHER = "005";
	/**
	 * 设备异常报警
	 * */
	public static final String XMPP_ALARM_TYPE_EXCEPTION = "006";
	/**
	 * 腕表低电压报警
	 * */
	public static final String XMPP_ALARM_TYPE_LOWVOL_WB = "007";
	/**
	 * 主机低电压报警
	 */
	public static final String XMPP_ALARM_TYPE_LOWVOL_ZJ = "008";
	/**
	 * 腕表消失报警
	 */
	public static final String XMPP_ALARM_TYPE_DISAPPEAR_WB = "009";
	/**
	 * 越界报警转发至移动端的类型
	 */
	public static final String XMPP_ALARM_TYPE_AREA = "999";
	// ---------电子监管系统中的报警类型---------
	/**
	 * 越界报警
	 */
	public static final String ALARM_TYPE_AREA = "1";
	/**
	 * 破拆报警
	 * */
	public static final String ALARM_TYPE_BREAK = "2";
	/**
	 * 聚集报警
	 * */
	public static final String ALARM_TYPE_GATHER = "3";
	/**
	 * 低电压报警
	 * */
	public static final String ALARM_TYPE_LOWVOL = "4";
	/**
	 * 人机分离报警
	 * */
	public static final String ALARM_TYPE_OUTRANGE = "5";
	/**
	 * 静默报警
	 * */
	public static final String ALARM_TYPE_SLIENT = "6";
	/**
	 * 设备异常报警
	 * */
	public static final String ALARM_TYPE_EXCEPTION = "7";

	/**
	 * Description:异常定位数据
	 */
	public static final String ERROR_LOCATION = "4.9E-324";
	/**
	 * Description:异常定位数据
	 */
	public static final String ERROR_LOCATION_NULL = "null";

	/**
	 * Description:加密后的有效标志位
	 */
	//different from online
	//public static final String VAILDATE_TRUE_MD5 = EncodingUtil.md5Base64("1");

}

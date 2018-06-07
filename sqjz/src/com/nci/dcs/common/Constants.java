package com.nci.dcs.common;

public interface Constants {
	public static final String TYPE_MACHINE = "1";
	public static final String TYPE_WATCH = "2";

	public static final String STATUS_UNUSED = "02";
	public static final String STATUS_USED = "03";
	public static final String STATUS_UNAVAILD = "04";
	public static final String STATUS_PAIRING = "05";
	// public static final String STATUS_BINDING_PERSON = "06";
	public static final String CONTENTTYPE_XLS = "";
	public static final String CONTENTTYPE_XLSX = "";
	public static final String CONTENTTYPE_XLT = "";
	public static final String CONTENTTYPE_XLTX = "";
	public static final String ORGANIZATION_SFSTYPE="3";//单位类型：司法所
	public static final String ORGANIZATION_SFJTYPE="2";//单位类型：司法局
	public static final String ORGANIZATION_SJTYPE="1";//单位类型：市局
	
	
	public static final String DEVICE_BROKENSTATUS="5";//设备状态：作废
	public static final String DEVICE_FIXSTATUS="3";//设备状态：维修
	public static final String DEVICE_USEDSTATUS="2";//设备状态：已用
	public static final String DEVICE_NOTUSEDSTATUS="1";//设备状态：未用
	public static final String DEVICE_RELEASESTATUS="4";//设备状态：已解除
	//sim卡状态
	public static final String DEVICE_NOTDREDGESTATUS="3";//设备状态：未开通
	public static final String DEVICE_LOGOUTSTATUS="4";//设备状态：注销
	
	public static final String RELATED_NOTBINDSTATUS="0";//关联状态：解绑
	public static final String RELATED_BINDSTATUS="1";//关联状态：绑定
	
	public static final String USEDEVICE_DZJGSBTYPE="1";//插上sim卡的设备类型：电子监管设备
	public static final String USEDEVICE_YDZFZDTYPE="2";//插上sim卡的设备类型：移动执法终端
	public static final String IS="1";//有，是
	public static final String FS="2";//无 ，否
	/*
	 * 设备生命周期操作名称
	 */
	public static final String DEVICELIFE_BUY="0";//购买
	public static final String DEVICELIFE_BUYANDOPEN="1";//购买并开通
	public static final String DEVICELIFE_OPEN="2";//开通
	public static final String DEVICELIFE_USE="3";//使用
	public static final String DEVICELIFE_REPLACESIM="4";//更换sim卡
	public static final String DEVICELIFE_REPLACEED="5";//被替换
	public static final String DEVICELIFE_WEAR="6";//佩戴
	public static final String DEVICELIFE_REMOVEWEAR="7";//解除佩戴
	public static final String DEVICELIFE_STOPUSE="8";//停用
	public static final String DEVICELIFE_FIX="9";//维修
	public static final String DEVICELIFE_ABANDON="10";//作废
	public static final String DEVICELIFE_LOGOFF="11";//注销
	public static final String DEVICELIFE_DELETE="12";//删除
	public static final String DEVICELIFE_RETURN="13";//返回
}
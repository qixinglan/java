package com.nci.dcs.jzgl.dagl.service.devicecheck;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHandle extends DeviceCheckHandleImpl {
	public TimeHandle(Date time) {
		this.time = time;
	}

	private Date time;

	public String success(String text, Object attachment) {
		String result = "true@";
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		result += "当前时间：" + sf.format(time);
		return result;
	}
}

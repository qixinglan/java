package com.nci.dcs.jzgl.dagl.service.devicecheck;

public class SilentHandle extends DeviceCheckHandleImpl {

	private Integer time;

	public SilentHandle(Integer time) {
		this.time = time;
	}

	@Override
	public String success(String text, Object attachment) {
		String result = "";
		result += "true@静默时间：" + time + "分钟";
		return result;
	}

}
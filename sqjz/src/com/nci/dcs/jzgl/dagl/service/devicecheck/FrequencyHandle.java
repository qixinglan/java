package com.nci.dcs.jzgl.dagl.service.devicecheck;


public class FrequencyHandle extends DeviceCheckHandleImpl {

	@Override
	public String success(String text, Object attachment) {
		String result = "true@ ";
		return result;
	}
}
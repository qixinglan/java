package com.nci.dcs.jzgl.dagl.service.devicecheck;

public class DeviceCheckHandleImpl implements DeviceCheckHandle {
	@Override
	public String error(String text, Object attachment) {
		return "false@设备返回错误消息";
	}

	@Override
	public String success(String text, Object attachment) {
		return "true@ ";
	}

	@Override
	public String timeout(String text, Object attachment) {
		return "false@在5秒内未收到设备回复消息";
	}
}

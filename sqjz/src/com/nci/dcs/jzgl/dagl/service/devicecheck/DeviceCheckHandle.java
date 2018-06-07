package com.nci.dcs.jzgl.dagl.service.devicecheck;

public interface DeviceCheckHandle {
	public String error(String text, Object attachment);
	/**
	 * 任务完成
	 * @param id 任务标识
	 * @param action 进行的操作
	 * @param state 成功或失败 
	 * @param text 成功失败的原因或其他信息
	 * @param attachment 传递一些额外的业务信息
	 */
	public String success(String text, Object attachment);
	/**
	 * 任务超时
	 * @param id 任务标识
	 * @param action 进行的操作
	 * @param state 成功或失败 
	 * @param text 成功失败的原因或其他信息
	 * @param attachment 传递一些额外的业务信息
	 */
	public String timeout(String text, Object attachment);
}

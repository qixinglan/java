package com.nci.dcs.base.xmpp;

public interface WorkProcess {
	/**
	 * 操作类型
	 * @return
	 */
	Integer getAction();
	/**
	 * 操作状态：成功、失败、进行中
	 * @return
	 */
	Integer getState();
	/**
	 * 可显示的文字
	 * @return
	 */
	String getText();
}

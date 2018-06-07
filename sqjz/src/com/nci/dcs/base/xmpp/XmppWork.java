package com.nci.dcs.base.xmpp;

import java.util.List;

public interface XmppWork<T> {
	/**
	 * 任务标识
	 * @return
	 */
	T getId();
	/**
	 * 获取当前的状态
	 * @return
	 */
	WorkProcess getCurrentProcess();
	/**
	 * 设置最新的状态
	 * @param process
	 */
	void setCurrentProcess(WorkProcess process);
	/**
	 * 获取并清空当前的所有处理历史
	 * @return
	 */
	List<WorkProcess> retireProcessHistory();
	/**
	 * 当前任务是否完成
	 */
	boolean finished();
	/**
	 * 设置已经完成
	 */
	XmppWork<T> finish();
}

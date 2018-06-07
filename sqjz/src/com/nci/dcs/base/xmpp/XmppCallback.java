package com.nci.dcs.base.xmpp;

public interface XmppCallback<T> {
	/**
	 * 任务中间的状态
	 * @param id 任务标识
	 * @param action 进行的操作
	 * @param state 成功或失败 
	 * @param text 成功失败的原因或其他信息
	 * @param attachment 传递一些额外的业务信息
	 */
	void process(T id, Integer action, Integer state, String text, Object attachment);
	/**
	 * 任务完成
	 * @param id 任务标识
	 * @param action 进行的操作
	 * @param state 成功或失败 
	 * @param text 成功失败的原因或其他信息
	 * @param attachment 传递一些额外的业务信息
	 */
	void finish(T id, Integer action, Integer state, String text, Object attachment);
	/**
	 * 任务超时
	 * @param id 任务标识
	 * @param action 进行的操作
	 * @param state 成功或失败 
	 * @param text 成功失败的原因或其他信息
	 * @param attachment 传递一些额外的业务信息
	 */
	void timeout(T id, Integer action, Integer state, String text, Object attachment);
}

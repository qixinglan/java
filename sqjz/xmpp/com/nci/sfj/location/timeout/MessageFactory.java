package com.nci.sfj.location.timeout;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;

/**
 * Description:用于处理超时
 * 
 * @author Shuzz
 * @since 2014-1-24上午10:20:06
 */
public abstract class MessageFactory {

	/**
	 * Description:判断该实体是否为需要进行超时重发的消息
	 * 
	 * @author Shuzz
	 * @param message
	 * @return
	 * @since 2014-2-11下午3:15:30
	 */
	public abstract boolean isRequest(Object message);

	/**
	 * Description:判断该实体是否为对应的回复
	 * 
	 * @author Shuzz
	 * @param message
	 * @return
	 * @since 2014-2-11下午3:15:34
	 */
	public abstract boolean isResponse(Object message);

	/**
	 * Description:获取需要进行超时重发消息的ID
	 * 
	 * @author Shuzz
	 * @param message
	 * @return
	 * @since 2014-2-11下午3:15:37
	 */
	public abstract Object getRequestId(Object message);

	/**
	 * Description:获取对应回复中携带的消息ID
	 * 
	 * @author Shuzz
	 * @param message
	 * @return
	 * @since 2014-2-11下午3:15:40
	 */
	public abstract Object getResponseId(Object message);

	/**
	 * Description:获取超时周期
	 * 
	 * @author Shuzz
	 * @return
	 * @since 2014-2-11下午3:15:43
	 */
	public abstract int getPeriod();

	/**
	 * Description:获取最大重发次数
	 * 
	 * @author Shuzz
	 * @return
	 * @since 2014-2-11下午3:15:45
	 */
	public abstract int getResendTime();

	/**
	 * Description:初始化消息存储
	 * 
	 * @author Shuzz
	 * @param session
	 * @return
	 * @since 2014-2-11下午3:15:48
	 */
	public synchronized Map<Object, Object> createRequestStore(IoSession session) {
		Map<Object, Object> result = new ConcurrentHashMap<Object, Object>();
		return result;
	}
}
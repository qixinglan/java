package com.nci.sfj.location.activetest;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

import com.nci.sfj.common.util.Config;

/**
 * Description:当心跳超时时的处理
 * 
 * @author Shuzz
 * @since 2014-1-17下午2:02:25
 */
public class LocationKeepAliveRequestTimeoutHandler implements
		KeepAliveRequestTimeoutHandler {
	private static final Logger log = Logger
			.getLogger(LocationKeepAliveRequestTimeoutHandler.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler#
	 * keepAliveRequestTimedOut
	 * (org.apache.mina.filter.keepalive.KeepAliveFilter,
	 * org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void keepAliveRequestTimedOut(KeepAliveFilter filter,
			IoSession session) throws Exception {
		int repeat_time = Config.getInt("xmpp.heart.repeat.time");
		Integer i = (Integer) session.getAttribute("repeat_time");
		if (i == null) {
			i = new Integer(0);
		}
		i++;
		session.setAttribute("repeat_time", i);
		if (i >= repeat_time) {
			log.error("心跳超时" + i + "次,关闭当前连接!");
			session.close(true);
		}
	}

}

package com.nci.sfj.xmpp.activetest;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

import com.nci.sfj.client.XmppClientManage;
import com.nci.sfj.common.util.Config;

/**
 * Description:当心跳超时时的处理
 * 
 * @author Shuzz
 * @since 2014年9月9日上午11:28:59
 */
public class XMPPKeepAliveRequestTimeoutHandler implements
		KeepAliveRequestTimeoutHandler {
	private static final Logger log = Logger
			.getLogger(XMPPKeepAliveRequestTimeoutHandler.class);

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
			XmppClientManage.getInstance().closeSession(session.getId());
		}
	}

}

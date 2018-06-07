package com.nci.sfj.client;

import java.net.InetSocketAddress;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nci.dcs.common.utils.SpringContextUtil;
import com.nci.sfj.common.util.Config;

/**
 * Description:负责创建与前置机的链接，单例模式
 * 
 * @author Shuzz
 * @since 2014年6月10日上午9:44:43
 */
public class XmppClient {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private static XmppClient instance;

	public static XmppClient getInstance() {
		if (instance == null) {
			synchronized (XmppClient.class) {
				instance = new XmppClient();
			}
		}
		return instance;
	}

	/**
	 * Constructor. Creates a server and starts it.
	 */
	private XmppClient() {
		instance = this;
	}

	/**
	 * Description:创建新的session
	 * 
	 * @author Shuzz
	 * @since 2014年6月10日上午10:21:04
	 * @param key
	 * @return
	 */
	public IoSession createNewSession() {
		try {
			IoConnector connector = (IoConnector) SpringContextUtil
					.getBean("ioConnector");
			IoSession session = connector
					.connect(
							new InetSocketAddress(Config
									.getString("xmpp.client.ip"), Config
									.getInt("xmpp.client.port")))
					.awaitUninterruptibly().getSession();
			return session;
		} catch (RuntimeIoException ioe) {
			logger.error("无法连接上前置机");
		} catch (NullPointerException nulle) {

		} catch (Exception e) {
			logger.error(null, e);
		}
		return null;
	}

	public IoSession createWebServieNewSession() {
		try {
			IoConnector connector = (IoConnector) SpringContextUtil
					.getBean("ioConnector");
			IoSession session = connector
					.connect(
							new InetSocketAddress(Config
									.getString("xmpp.webservice.ip"), Config
									.getInt("xmpp.webservice.port")))
					.awaitUninterruptibly().getSession();
			return session;
		} catch (RuntimeIoException ioe) {

		} catch (NullPointerException nulle) {

		} catch (Exception e) {
			logger.error(null, e);
		}
		return null;
	}
}
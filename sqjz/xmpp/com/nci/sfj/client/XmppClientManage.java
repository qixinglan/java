package com.nci.sfj.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;

import com.nci.sfj.common.util.Config;

/**
 * Description:管理与前置机链接的情况，单例模式
 * 
 * @author Shuzz
 * 
 */
public class XmppClientManage {
	private static XmppClientManage instance;
	/**
	 * Description:保存所有链接
	 */
	private Map<Long, XmppClientSession> clientSessions = new ConcurrentHashMap<Long, XmppClientSession>(
			0);

	public XmppClientManage() {
		CheckAliveThread checkAliveThread = new CheckAliveThread();
		checkAliveThread.start();
		instance = this;
	}

	public static XmppClientManage getInstance() {
		if (instance == null) {
			synchronized (XmppClientManage.class) {
				instance = new XmppClientManage();
			}
		}
		return instance;
	}

	/**
	 * Description:检测链接的线程
	 * 
	 * @author Shuzz
	 * @since 2014年9月9日上午11:34:13
	 * 
	 */
	private class CheckAliveThread extends Thread {
		public void run() {
			while (true) {
				try {
					// 每隔5秒一次
					Thread.sleep(5000);
					// 判断是否需要建立链接
					if (Config.getBoolean("xmpp.client")) {
						if (checkSession()) {
							IoSession iosession = XmppClient.getInstance()
									.createNewSession();
							if (iosession != null) {
								XmppClientSession cs = new XmppClientSession(
										iosession);
								clientSessions.put(iosession.getId(), cs);
							}
						}
					}
				} catch (InterruptedException e) {
					// Ignore
				}
			}
		}
	}

	/**
	 * Description:客户端关闭链接时的从内存里移除该session
	 * 
	 * @author Shuzz
	 * @since 2014年9月9日上午11:29:20
	 * @param sessionId
	 */
	public void closeSession(long sessionId) {
		if (clientSessions.get(sessionId) != null) {
			clientSessions.get(sessionId).getSession().close(true);
			clientSessions.remove(sessionId);
		}
	}

	/**
	 * Description:建立新链接
	 * 
	 * @author Shuzz
	 * @since 2014年9月9日上午11:31:08
	 */
	public void createConnection() {
		IoSession iosession = XmppClient.getInstance().createNewSession();
		if (iosession != null) {
			XmppClientSession cs = new XmppClientSession(iosession);
			clientSessions.put(iosession.getId(), cs);
		}
	}

	/**
	 * Description:检测所有的链接是否处于可用状态
	 * 
	 * @author Shuzz
	 * @since 2014年9月9日上午11:33:52
	 * @return true 无可用链接；false 有可用链接
	 */
	private boolean checkSession() {
		if (clientSessions.isEmpty()) {
			return true;
		} else {
			for (Long key : clientSessions.keySet()) {
				IoSession iosession = clientSessions.get(key).getSession();
				if (iosession.isClosing()) {
					clientSessions.remove(key);
				}
			}
			if (clientSessions.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public Map<Long, XmppClientSession> getClientSessions() {
		return clientSessions;
	}
}

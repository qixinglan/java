/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.nci.sfj.xmpp.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmpp.packet.JID;

import com.nci.sfj.server.SfjtxServer;
import com.nci.sfj.xmpp.auth.AuthToken;
import com.nci.sfj.xmpp.handler.Connection;
import com.nci.sfj.xmpp.handler.ConnectionCloseListener;

/**
 * This class manages the sessions connected to the server.
 * 
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class SessionManager {

	private static final Log log = LogFactory.getLog(SessionManager.class);
	/**
	 * 定位主机方资源名称
	 */
	public static final String CLIENT_RESOURCE_NAME = "AndroidpnClient";

	/**
	 * 移动终端资源名称
	 */
	public static final String JUSTICE_RESOURCE_NAME = "justice";

	private static SessionManager instance;

	private String serverName;

	private Map<String, ClientSession> preAuthSessions = new ConcurrentHashMap<String, ClientSession>();

	private Map<String, ClientSession> clientSessions = new ConcurrentHashMap<String, ClientSession>();

	private Map<String, JID> userSessions = new ConcurrentHashMap<String, JID>();
	private Map<String, Map<String, String>> orgUsers = new ConcurrentHashMap<String, Map<String, String>>(
			0);
	private final AtomicInteger connectionsCounter = new AtomicInteger(0);

	private ClientSessionListener clientSessionListener = new ClientSessionListener();

	private SessionManager() {
		serverName = SfjtxServer.getInstance().getServerName();
	}

	/**
	 * Returns the singleton instance of SessionManager.
	 * 
	 * @return the instance
	 */
	public static SessionManager getInstance() {
		if (instance == null) {
			synchronized (SessionManager.class) {
				instance = new SessionManager();
			}
		}
		return instance;
	}

	/**
	 * Creates a new ClientSession and returns it.
	 * 
	 * @param conn
	 *            the connection
	 * @return a newly created session
	 */
	public ClientSession createClientSession(Connection conn) {
		if (serverName == null) {
			throw new IllegalStateException("Server not initialized");
		}

		Random random = new Random();
		String streamId = Integer.toHexString(random.nextInt());

		ClientSession session = new ClientSession(serverName, conn, streamId);
		conn.init(session);
		conn.registerCloseListener(clientSessionListener);

		// Add to pre-authenticated sessions
		preAuthSessions.put(session.getAddress().getResource(), session);

		// Increment the counter of user sessions
		connectionsCounter.incrementAndGet();

		log.debug("ClientSession created.");
		return session;
	}

	/**
	 * Adds a new session that has been authenticated.
	 * 
	 * @param session
	 *            the session
	 */
	public void addSession(ClientSession session) {
		preAuthSessions.remove(session.getStreamID().toString());
		clientSessions.put(session.getAddress().toString(), session);
	}

	/**
	 * Returns the session associated with the JID.
	 * 
	 * @param from
	 *            the client address
	 * @return the session associated with the JID
	 */
	public ClientSession getSession(JID from) {
		if (from == null || serverName == null
				|| !serverName.equals(from.getDomain())) {
			return null;
		}
		// Check pre-authenticated sessions
		if (from.getResource() != null) {
			ClientSession session = preAuthSessions.get(from.getResource());
			if (session != null) {
				return session;
			}
		}
		if (from.getResource() == null || from.getNode() == null) {
			return null;
		}
		return clientSessions.get(from.toString());
	}

	/**
	 * Returns a list that contains all authenticated client sessions.
	 * 
	 * @return a list that contains all client sessions
	 */
	public Collection<ClientSession> getSessions() {
		return clientSessions.values();
	}

	/**
	 * Removes a client session.
	 * 
	 * @param session
	 *            the session to be removed
	 * @return true if the session was successfully removed
	 */
	public boolean removeSession(ClientSession session) {
		if (session == null || serverName == null) {
			return false;
		}
		JID fullJID = session.getAddress();
		for (String userName : session.getUsers().keySet()) {
			AuthToken token = session.getUsers().remove(userName);
			if (token != null && token.getUserId() != null) {
				Map<String, String> users = orgUsers.get(token.getOrgId());
				users.remove(userName);
			}
		}
		// Remove the session from list
		boolean clientRemoved = clientSessions.remove(fullJID.toString()) != null;
		boolean preAuthRemoved = (preAuthSessions.remove(fullJID.getResource()) != null);

		// Decrement the counter of user sessions
		if (clientRemoved || preAuthRemoved) {
			connectionsCounter.decrementAndGet();
			return true;
		}
		return false;
	}

	/**
	 * Closes the all sessions.
	 */
	public void closeAllSessions() {
		try {
			// Send the close stream header to all connections
			Set<ClientSession> sessions = new HashSet<ClientSession>();
			sessions.addAll(preAuthSessions.values());
			sessions.addAll(clientSessions.values());

			for (ClientSession session : sessions) {
				try {
					session.getConnection().systemShutdown();
				} catch (Throwable t) {
				}
			}
		} catch (Exception e) {
		}
	}

	/**
	 * A listner to handle a session that has been closed.
	 */
	private class ClientSessionListener implements ConnectionCloseListener {

		public void onConnectionClose(Object handback) {
			try {
				ClientSession session = (ClientSession) handback;
				removeSession(session);
			} catch (Exception e) {
				log.error("Could not close socket", e);
			}
		}
	}

	public void addUser(String userName, JID jid, AuthToken token) {
		userSessions.put(userName, jid);
		if (token.getUserId() != null) {
			Map<String, String> users = orgUsers.get(token.getOrgId());
			if (users == null) {
				users = new ConcurrentHashMap<String, String>();
			}
			users.put(userName, "");
			orgUsers.put(token.getOrgId(), users);
		}
		ClientSession clientSession = getSession(jid);
		clientSession.addUser(userName, token);
	}

	public void removeUser(String userName) {
		JID jid = userSessions.remove(userName);
		ClientSession clientSession = getSession(jid);
		if (null != clientSession) {
			AuthToken token = clientSession.removeUser(userName);
			if (token != null && token.getUserId() != null) {
				Map<String, String> users = orgUsers.get(token.getOrgId());
				users.remove(userName);
			}
		}
	}

	public boolean checkOnline(String userName) {
		JID jid = userSessions.get(userName);
		if (jid != null) {
			if (null != getSession(jid)) {
				return true;
			}
		}
		return false;

	}

	public void clearUser(String userName) {
		userSessions.remove(userName);
	}

	public JID getUserJID(String userName) {
		return userSessions.get(userName);
	}

	/**
	 * Description:获取指定机构下的所有已登录用户
	 * 
	 * @author Shuzz
	 * @since 2014年6月26日下午2:46:46
	 * @param orgId
	 * @return
	 */
	public List<String> getJusticeByOrg(String orgId) {
		List<String> justices = new ArrayList<String>();
		if (orgId != null) {
			Map<String, String> users = orgUsers.get(orgId);

			if (users != null) {
				for (String justice : users.keySet()) {
					justices.add(justice);
				}
			}
		}
		return justices;
	}

	/**
	 * Description:根据主机编号获取其session
	 * 
	 * @author Shuzz
	 * @since 2014年8月11日上午10:26:32
	 * @param deviceNumber
	 * @return
	 */
	public ClientSession getSessionByDeviceNumber(String deviceNumber) {
		String userJid = deviceNumber + "@" + CLIENT_RESOURCE_NAME;
		JID jid = getUserJID(userJid);
		if (jid != null) {
			return getSession(jid);
		}
		return null;
	}

	public List<AuthToken> getOnlineUserToken() {
		List<AuthToken> token = new ArrayList<AuthToken>(0);
		for (String userName : userSessions.keySet()) {
			token.add(getSession(userSessions.get(userName))
					.getUserId(userName));
		}
		return token;
	}

	public Map<String, ClientSession> getPreAuthSessions() {
		return preAuthSessions;
	}

}
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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmpp.packet.Presence;

import com.nci.sfj.xmpp.auth.AuthToken;
import com.nci.sfj.xmpp.handler.Connection;

/**
 * This class represents a session between the server and a client.
 * 
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class ClientSession extends Session {

	private static final Log log = LogFactory.getLog(ClientSession.class);

	private static final String ETHERX_NAMESPACE = "http://etherx.jabber.org/streams";

	private boolean initialized;

	private boolean wasAvailable = false;

	private Presence presence = null;

	private Map<String, AuthToken> users = new ConcurrentHashMap<String, AuthToken>();

	/**
	 * Constructor.
	 * 
	 * @param serverName
	 *            the server name
	 * @param connection
	 *            the connection
	 * @param streamID
	 *            the stream ID
	 */
	public ClientSession(String serverName, Connection connection,
			String streamID) {
		super(serverName, connection, streamID);
		presence = new Presence();
		presence.setType(Presence.Type.unavailable);
	}

	/**
	 * Creates a new session between the server and a client, and returns it.
	 * 
	 * @param serverName
	 *            the server name
	 * @param connection
	 *            the connection
	 * @param xpp
	 *            the XML parser to handle incoming data
	 * @return a newly created session
	 * @throws XmlPullParserException
	 *             if an error occurs while parsing incoming data
	 */
	public static ClientSession createSession(String serverName,
			Connection connection, XmlPullParser xpp)
			throws XmlPullParserException {
		log.debug("createSession()...");

		if (!xpp.getName().equals("stream")) {
			throw new XmlPullParserException("Bad opening tag (not stream)");
		}

		if (!xpp.getNamespace(xpp.getPrefix()).equals(ETHERX_NAMESPACE)) {
			throw new XmlPullParserException("Stream not in correct namespace");
		}

		String language = "en";
		for (int i = 0; i < xpp.getAttributeCount(); i++) {
			if ("lang".equals(xpp.getAttributeName(i))) {
				language = xpp.getAttributeValue(i);
			}
		}

		// Store language and version information
		connection.setLanaguage(language);
		connection.setXMPPVersion(MAJOR_VERSION, MINOR_VERSION);

		// Create a ClientSession
		ClientSession session = SessionManager.getInstance()
				.createClientSession(connection);

		// Build the start packet response
		StringBuilder sb = new StringBuilder(200);
		sb.append("<proceed xmlns=\"urn:ietf:params:xml:ns:xmpp-tls\"/>");

		connection.deliverRawText(sb.toString());
		return session;
	}

	/**
	 * Indicates if the session has been initialized.
	 * 
	 * @return true if the session has been initialized, false otherwise.
	 */
	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * Sets the initialization state of the session.
	 * 
	 * @param initialized
	 *            true if the session has been initialized
	 */
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	/**
	 * Indicates if the session was available ever.
	 * 
	 * @return true if the session was available ever, false otherwise.
	 */
	public boolean wasAvailable() {
		return wasAvailable;
	}

	/**
	 * Returns the presence of this session.
	 * 
	 * @return the presence
	 */
	public Presence getPresence() {
		return presence;
	}

	/**
	 * Sets the presence of this session.
	 * 
	 * @param presence
	 *            the presence
	 */
	public void setPresence(Presence presence) {
		Presence oldPresence = this.presence;
		this.presence = presence;
		if (oldPresence.isAvailable() && !this.presence.isAvailable()) {
			setInitialized(false);
		} else if (!oldPresence.isAvailable() && this.presence.isAvailable()) {
			wasAvailable = true;
		}
	}

	/**
	 * Returns a text with the available stream features.
	 */
	public String getAvailableStreamFeatures() {
		StringBuilder sb = new StringBuilder();
		// If the session has been authenticated
		sb.append("<bind xmlns=\"urn:ietf:params:xml:ns:xmpp-bind\"/>");
		sb.append("<session xmlns=\"urn:ietf:params:xml:ns:xmpp-session\"/>");
		return sb.toString();
	}

	@Override
	public String toString() {
		return super.toString() + " presence: " + presence;
	}

	public void addUser(String userName, AuthToken token) {
		users.put(userName, token);
	}

	public AuthToken removeUser(String userName) {
		return users.remove(userName);
	}

	public void clearAllUser() {
		for (String key : users.keySet()) {
			sessionManager.clearUser(key);
		}
	}

	public AuthToken getUserId(String userName) {
		return users.get(userName);
	}

	public Map<String, AuthToken> getUsers() {
		return users;
	}
}

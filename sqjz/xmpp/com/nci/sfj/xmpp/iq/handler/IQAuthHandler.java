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
package com.nci.sfj.xmpp.iq.handler;

import java.util.Date;

import gnu.inet.encoding.Stringprep;
import gnu.inet.encoding.StringprepException;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.xmpp.packet.IQ;
import org.xmpp.packet.JID;

import com.nci.dcs.common.utils.CommonUtils;
import com.nci.sfj.xmpp.auth.AuthManager;
import com.nci.sfj.xmpp.auth.AuthToken;
import com.nci.sfj.xmpp.exception.UnauthenticatedException;
import com.nci.sfj.xmpp.exception.UnauthorizedException;
import com.nci.sfj.xmpp.session.ClientSession;
import com.nci.sfj.xmpp.session.SessionManager;

/**
 * This class is to handle the TYPE_IQ jabber:iq:auth protocol.
 * 
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class IQAuthHandler extends IQHandler {

	public static final String NAMESPACE = "jabber:iq:auth";

	private Element probeResponse;

	/**
	 * Constructor.
	 */
	public IQAuthHandler() {
		probeResponse = DocumentHelper.createElement(QName.get("query",
				NAMESPACE));
		probeResponse.addElement("username");
		if (AuthManager.isPlainSupported()) {
			probeResponse.addElement("password");
		}
		if (AuthManager.isDigestSupported()) {
			probeResponse.addElement("digest");
		}
		probeResponse.addElement("resource");
	}

	/**
	 * Handles the received IQ packet.
	 * 
	 * @param packet
	 *            the packet
	 * @return the response to send back
	 * @throws UnauthorizedException
	 *             if the user is not authorized
	 */
	public IQ handleIQ(IQ packet) throws UnauthorizedException {
		IQ reply = null;

		ClientSession session = sessionManager.getSession(packet.getFrom());
		String user = null;
		AuthToken token = null;
		Date loginTime = new Date();
		String username = "";
		if (session == null) {
			reply = handleNoSession(packet);
			//different from online
			//dbService.createXmppLog(username, "5", new Date(), reply);
			return reply;
		}
		try {
			Element iq = packet.getElement();
			String from = iq.attributeValue("from");
			Element query = iq.element("query");

			if (IQ.Type.set == packet.getType()) { // get query
				String resource = query.elementText("resource");
				username = query.elementText("username");
				dbService.createXmppLog(username, "1", loginTime, packet);
				String digest = null;
				if (query.element("digest") != null) {
					digest = query.elementText("digest").toLowerCase();
				}
				
				//different from online
				/*
				String password = null;
				if (query.element("password") != null) {
					password = query.elementText("password").toLowerCase();
				}
				*/
				
				// Verify the resource
				if (resource != null) {
					try {
						resource = JID.resourceprep(resource);
					} catch (StringprepException e) {
						throw new UnauthorizedException("Invalid resource: "
								+ resource, e);
					}
				} else {
					throw new IllegalArgumentException(
							"Invalid resource (empty or null).");
				}
				// Verify the username
				if (username == null || username.trim().length() == 0) {
					throw new UnauthorizedException(
							"Invalid username (empty or null).");
				}
				try {
					Stringprep.nodeprep(username);
				} catch (StringprepException e) {
					throw new UnauthorizedException("Invalid username: "
							+ username, e);
				}
				// username = username.toLowerCase();

				// Verify that username and password are correct
				boolean flag = false;
				String userJid = username + "@" + resource;
				if (SessionManager.CLIENT_RESOURCE_NAME.equals(resource)) {
					// 腕表端接入
					if (digest != null && AuthManager.isDigestSupported()) {
						//different from online
						//flag = AuthManager.authenticateClinet(username, session
						flag = AuthManager.authenticate(username, session
								.getStreamID().toString(), digest, resource);
						if (!flag) {
							throw new UnauthenticatedException("用户名" + username
									+ "非法或密码不正确");
						} else {
							token = dbService.findFxryByDevice(username);
							sessionManager.addUser(userJid, packet.getFrom(),
									token);
							user = username;
							dbService.login(username, "1", "1", loginTime);
						}
					}
				} else if (SessionManager.JUSTICE_RESOURCE_NAME
						.equals(resource)) {
					// 移动执法终端接入
					//different from online
					/*
					String imei = query.elementText("imei");
					flag = AuthManager.authenticateJustice(username, session
							.getStreamID().toString(), digest, resource, imei);
					if (!flag) {
						throw new UnauthenticatedException("用户名" + username
								+ "非法或密码不正确");
					} else {*/
						dbService.login(username, "2", "1", loginTime);
						token = dbService.findUserByUserName(username);
						sessionManager
								.addUser(userJid, packet.getFrom(), token);
					//different from online
					//}
				}
				//different from online
				/*
				else {
					// 其他类型的资源为非法资源
					throw new UnauthenticatedException("无该类型设备");
				}
				*/
				reply = IQ.createResultIQ(packet);
				reply.setFrom(new JID(from));
				reply.setChildElement(packet.getChildElement().createCopy());
				reply.getElement().addAttribute("oldFrom",
						packet.getElement().attributeValue("oldFrom"));
			}
		} catch (Exception ex) {
			reply = handleException(ex, packet);
		}
		// Send the response directly to the session
		if (reply != null) {
			session.process(reply);
			//different from online
			//dbService.createXmppLog(username, "5", new Date(), reply);
		}
		if (user != null && token != null) {
			emMessageSendService.setTime(user);
			if (!CommonUtils.isNull(token.getFxryId())) {
				emMessageSendService.setAllToClient(user, token.getFxryId());
			} else {
				emMessageSendService.running(false, user);
			}
		}
		return null;
	}

	/**
	 * Returns the namespace of the handler.
	 * 
	 * @return the namespace
	 */
	public String getNamespace() {
		return NAMESPACE;
	}

}

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

import org.dom4j.Element;
import org.xmpp.packet.IQ;
import org.xmpp.packet.IQ.Type;
import org.xmpp.packet.JID;

import com.nci.dcs.common.utils.CommonUtils;
import com.nci.sfj.xmpp.auth.AuthToken;
import com.nci.sfj.xmpp.exception.UnauthorizedException;
import com.nci.sfj.xmpp.model.IQModelUtil;
import com.nci.sfj.xmpp.session.ClientSession;

/**
 * This class is to handle the TYPE_IQ jabber:iq:auth protocol.
 * 
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class IQBaseStationHandler extends IQHandler {

	private static final String NAMESPACE = "dzjg:iq:basestation";

	/**
	 * Constructor.
	 */
	public IQBaseStationHandler() {
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
		String userName = packet.getElement().attributeValue("token");
		AuthToken token = dbService.findFxryByDevice(userName.split("@")[0]);
		dbService
				.createXmppLog(userName.split("@")[0], "3", new Date(), packet);
		if (Type.get == packet.getType() || Type.set == packet.getType()) {
			if (session == null) {
				reply = handleNoSession(packet);
				return reply;
			}
			try {
				Element iq = packet.getElement();
				String from = iq.attributeValue("from");
				if (token != null) {
					sessionManager.addUser(userName, packet.getFrom(), token);
					if (!CommonUtils.isNull(token.getDeviceId())) {
						businessHandle.addBaseStationMessage(iq.element("basestation"),
								token);
					}
				}
				reply = IQ.createResultIQ(packet);
				reply.setFrom(new JID(from));
				reply.setChildElement(IQModelUtil.getRoot("basestation"));
				reply.getElement().addAttribute("oldFrom",
						packet.getElement().attributeValue("oldFrom"));
			} catch (Exception ex) {
				reply = handleException(ex, packet);
			}
		} else {
		}
		// Send the response directly to the session
		if (reply != null) {
			session.process(reply);
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

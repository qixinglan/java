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

import org.xmpp.packet.IQ;

import com.nci.sfj.client.XmppClientManage;
import com.nci.sfj.xmpp.exception.UnauthorizedException;

/**
 * This class is to handle the TYPE_IQ jabber:iq:auth protocol.
 * 
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class IQConnectHandler extends IQHandler {

	private static final String NAMESPACE = "dzjg:iq:connect";

	/**
	 * Constructor.
	 */
	public IQConnectHandler() {
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
		XmppClientManage.getInstance().createConnection();
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

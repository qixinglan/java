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

import com.nci.sfj.xmpp.session.ClientSession;
import com.nci.sfj.xmpp.session.SessionManager;

/**
 * Description:手机与中间机断开连接时应用服务器端的处理
 * 
 * @author Shuzz
 * @since 2014年6月11日下午4:52:25
 */
public class IQExitHandler extends IQHandler {

	private static final String NAMESPACE = "dzjg:iq:exit";

	/**
	 * Constructor.
	 */
	public IQExitHandler() {
	}

	/**
	 * Handles the received IQ packet.
	 * 
	 * @param packet
	 *            the packet
	 * @return the response to send back
	 */
	public IQ handleIQ(IQ packet) {
		IQ reply = null;
		ClientSession session = sessionManager.getSession(packet.getFrom());

		if (session == null) {
			reply = handleNoSession(packet);
			return reply;
		}
		String userJID = packet.getChildElement().getText();
		String idle = packet.getChildElement().elementText("idle");
		String username = userJID.split("@")[0];
		String resource = userJID.split("@")[1];
		if (SessionManager.CLIENT_RESOURCE_NAME.equals(resource)) {
			dbService.login(username, "1", "2", null);
		} else if (SessionManager.JUSTICE_RESOURCE_NAME.equals(resource)) {
			dbService.login(username, "2", "2", null);
		}
		if("true".equals(idle)){
			//这时可以报设备异常的警
		}
		sessionManager.removeUser(userJID);
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

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

import org.xmpp.packet.IQ;
import org.xmpp.packet.PacketError;
import org.xmpp.packet.IQ.Type;

import com.nci.sfj.xmpp.exception.UnauthorizedException;
import com.nci.sfj.xmpp.sender.EmMessageReceive;
import com.nci.sfj.xmpp.session.ClientSession;

/**
 * Description:处理主机停止/开始工作的回执信息
 * 
 * @author Shuzz
 * @since 2014年7月3日下午3:36:45
 */
public class IQWorkStateHandler extends IQHandler {

	private static final String NAMESPACE = "dzjg:iq:workState";

	/**
	 * Constructor.
	 */
	public IQWorkStateHandler() {
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
		dbService.createXmppLog(userName.split("@")[0],"5",new Date(),packet);
		if (Type.error == packet.getType() || Type.result == packet.getType()) {
			String id = packet.getID();
			if (Type.result == packet.getType()) {
				deviceCheckService.receiveResult(id,
						EmMessageReceive.Type.SUCCESS,packet.getChildElement());
			} else {
				if (packet.getError().getCondition()
						.equals(PacketError.Condition.remote_server_timeout)) {
					deviceCheckService.receiveResult(id,
							EmMessageReceive.Type.TIMEOUT,packet.getChildElement());
				} else {
					deviceCheckService.receiveResult(id,
							EmMessageReceive.Type.ERROR,packet.getChildElement());
				}
			}
		} else {
			// 设置信息不处理忽略掉
			if (session == null) {
				reply=handleNoSession(packet);
				return reply;
			}
		}
		// Send the response directly to the session
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

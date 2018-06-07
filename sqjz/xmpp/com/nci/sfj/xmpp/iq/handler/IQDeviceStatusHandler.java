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

import com.nci.dcs.common.utils.CommonUtils;
import com.nci.sfj.xmpp.auth.AuthToken;
import com.nci.sfj.xmpp.exception.UnauthorizedException;

/**
 * Description:处理主机MQTT方式定位协议中携带的附加信息
 * 
 * @author Shuzz
 * @since 2015年6月4日下午1:52:16
 * 
 */
public class IQDeviceStatusHandler extends IQHandler {

	private static final String NAMESPACE = "dzjg:iq:mqtt:status";

	/**
	 * Constructor.
	 */
	public IQDeviceStatusHandler() {
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
		String userName = packet.getElement().attributeValue("token");
		if (Type.get == packet.getType() || Type.set == packet.getType()) {
			try {
				Element iq = packet.getElement();
				AuthToken token = dbService.findFxryByDevice(userName
						.split("@")[0]);
				if (token != null) {
					dbService.createXmppLog(userName.split("@")[0], "5",
							new Date(), packet);
					dbService.saveDeviceStatus(token, iq.element("mqttStatus"));
				}
			} catch (Exception ex) {
			}
		} else {
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

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
import org.xmpp.packet.PacketError;

import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.SpringContextUtil;
import com.nci.sfj.business.service.SettingService;
import com.nci.sfj.xmpp.auth.AuthToken;
import com.nci.sfj.xmpp.exception.PacketMessageException;
import com.nci.sfj.xmpp.exception.UnauthorizedException;
import com.nci.sfj.xmpp.session.ClientSession;

/**
 * Description:处理主机停止/开始工作的回执信息
 * 
 * @author Shuzz
 * @since 2014年7月3日下午3:36:45
 */
public class IQSettingHandler extends IQHandler {

	private static final String NAMESPACE = "dzjg:iq:setting";

	/**
	 * Constructor.
	 */
	public IQSettingHandler() {
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

		if (Type.get == packet.getType()) {
			reply = IQ.createResultIQ(packet);
			Element root = packet.getChildElement();
			AuthToken token = dbService
					.findFxryByDevice(userName.split("@")[0]);
			if (!CommonUtils.isNull(token.getDeviceId())) {
				dbService.createXmppLog(userName.split("@")[0], "4",
						new Date(), packet);
				SettingService settingService = (SettingService) SpringContextUtil
						.getBean("settingService");
				try {
					Element e = settingService
							.getSetting(userName.split("@")[0]);
					if (e != null) {
						root.add(e);
					}
				} catch (PacketMessageException e) {
					reply.setError(PacketError.Condition.item_not_found);
				}
			}
			reply.setChildElement(root.createCopy());
			reply.getElement().addAttribute("oldFrom",
					packet.getElement().attributeValue("oldFrom"));
		} else {
			// 设置信息不处理忽略掉
			if (session == null) {
				reply = handleNoSession(packet);
				return reply;
			}
		}
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

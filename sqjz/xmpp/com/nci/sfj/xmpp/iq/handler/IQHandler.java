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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmpp.packet.IQ;
import org.xmpp.packet.Packet;
import org.xmpp.packet.PacketError;

import com.nci.dcs.common.utils.SpringContextUtil;
import com.nci.dcs.jzgl.dagl.service.DeviceCheckService;
import com.nci.sfj.business.service.BusinessHandleScheduler;
import com.nci.sfj.business.service.DbService;
import com.nci.sfj.xmpp.auth.AuthToken;
//different from online
//import com.nci.sfj.xmpp.exception.ForbiddenException;
import com.nci.sfj.xmpp.exception.UnauthenticatedException;
import com.nci.sfj.xmpp.exception.UnauthorizedException;
import com.nci.sfj.xmpp.router.PacketDeliverer;
import com.nci.sfj.xmpp.sender.EmMessageSendService;
import com.nci.sfj.xmpp.session.SessionManager;

/**
 * This is an abstract class to handle routed IQ packets.
 * 
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public abstract class IQHandler {

	protected EmMessageSendService emMessageSendService = (EmMessageSendService) SpringContextUtil
			.getBean("emMessageSendService");
	protected BusinessHandleScheduler businessHandle = (BusinessHandleScheduler) SpringContextUtil
			.getBean("businessHandleScheduler");
	protected DbService dbService = (DbService) SpringContextUtil
			.getBean("dbService");
	protected DeviceCheckService deviceCheckService = (DeviceCheckService) SpringContextUtil
			.getBean("deviceCheckService");

	protected final Log log = LogFactory.getLog(getClass());

	protected SessionManager sessionManager;

	/**
	 * Constructor.
	 */
	public IQHandler() {
		sessionManager = SessionManager.getInstance();
	}

	/**
	 * Processes the received IQ packet.
	 * 
	 * @param packet
	 *            the packet
	 */
	public void process(Packet packet) {
		IQ iq = (IQ) packet;
		try {
			IQ reply = handleIQ(iq);
			if (reply != null) {
				PacketDeliverer.deliver(reply);
			}
		} catch (UnauthorizedException e) {
			if (iq != null) {
				try {
					IQ response = IQ.createResultIQ(iq);
					response.getElement().addAttribute("oldFrom",
							packet.getElement().attributeValue("oldFrom"));
					response.setChildElement(iq.getChildElement().createCopy());
					response.setError(PacketError.Condition.not_authorized);
					sessionManager.getSession(iq.getFrom()).process(response);
				} catch (Exception de) {
					log.error("处理消息时发生异常：", de);
					sessionManager.getSession(iq.getFrom()).close();
				}
			}
		} catch (Exception e) {
			log.error("处理消息时发生异常：", e);
			try {
				IQ response = IQ.createResultIQ(iq);
				response.getElement().addAttribute("oldFrom",
						packet.getElement().attributeValue("oldFrom"));
				response.setChildElement(iq.getChildElement().createCopy());
				response.setError(PacketError.Condition.internal_server_error);
				sessionManager.getSession(iq.getFrom()).process(response);
			} catch (Exception ex) {
				// Ignore
			}
		}
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
	public abstract IQ handleIQ(IQ packet) throws UnauthorizedException;

	/**
	 * Returns the namespace of the handler.
	 * 
	 * @return the namespace
	 */
	public abstract String getNamespace();

	/**
	 * Description:转发至移动端
	 * 
	 * @author Shuzz
	 * @since 2014年7月3日下午3:22:36
	 * @param token
	 * @param packet
	 */
	protected void resendToJustice(AuthToken token, IQ packet) {
		if (token.getOrgId() != null) {
			EmMessageSendService emMessageSendService = (EmMessageSendService) SpringContextUtil
					.getBean("emMessageSendService");
			String namespace = packet.getChildElement().getNamespaceURI();
			emMessageSendService.resendToJustice(packet.getChildElement(),
					token, namespace);
		}
	}

	protected IQ handleException(Exception ex, IQ packet) {
		log.error("处理消息时发生异常：", ex);
		IQ reply = IQ.createResultIQ(packet);
		reply.getElement().addAttribute("oldFrom",
				packet.getElement().attributeValue("oldFrom"));
		reply.setChildElement(packet.getChildElement().createCopy());
		if (ex instanceof IllegalArgumentException) {
			reply.setError(PacketError.Condition.not_acceptable);
		} else if (ex instanceof UnauthorizedException) {
			reply.setError(PacketError.Condition.not_authorized);
		} else if (ex instanceof UnauthenticatedException) {
			reply.setError(PacketError.Condition.not_authorized);
		} 
		//different from online
		/*
		else if (ex instanceof ForbiddenException) {
			reply.setError(PacketError.Condition.forbidden);
		} 
		*/
		else {
			reply.setError(PacketError.Condition.internal_server_error);
		}
		return reply;
	}

	protected IQ handleNoSession(IQ packet) {
		log.error("未找到可用的链接给该用户：" + packet.getFrom());
		IQ reply = IQ.createResultIQ(packet);
		reply.setChildElement(packet.getChildElement().createCopy());
		reply.setError(PacketError.Condition.internal_server_error);
		return reply;
	}

}

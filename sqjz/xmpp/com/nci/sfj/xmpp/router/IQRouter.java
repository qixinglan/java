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
package com.nci.sfj.xmpp.router;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;
import org.xmpp.packet.IQ;
import org.xmpp.packet.JID;
import org.xmpp.packet.PacketError;

import com.nci.sfj.transmit.iq.handler.IQTransmitNewHandler;
import com.nci.sfj.xmpp.iq.handler.IQAlertHandler;
import com.nci.sfj.xmpp.iq.handler.IQAuthHandler;
import com.nci.sfj.xmpp.iq.handler.IQBaseStationHandler;
import com.nci.sfj.xmpp.iq.handler.IQCPUHandler;
import com.nci.sfj.xmpp.iq.handler.IQCloseStateHandler;
import com.nci.sfj.xmpp.iq.handler.IQConnectHandler;
import com.nci.sfj.xmpp.iq.handler.IQDeviceStatusHandler;
import com.nci.sfj.xmpp.iq.handler.IQDiskHandler;
import com.nci.sfj.xmpp.iq.handler.IQEQuantityHandler;
import com.nci.sfj.xmpp.iq.handler.IQExitHandler;
import com.nci.sfj.xmpp.iq.handler.IQFrequencyHandler;
import com.nci.sfj.xmpp.iq.handler.IQHandler;
import com.nci.sfj.xmpp.iq.handler.IQLocationHandler;
import com.nci.sfj.xmpp.iq.handler.IQMemoryHandler;
import com.nci.sfj.xmpp.iq.handler.IQNlocationHandler;
import com.nci.sfj.xmpp.iq.handler.IQPairHandler;
import com.nci.sfj.xmpp.iq.handler.IQPhoneHandler;
import com.nci.sfj.xmpp.iq.handler.IQPhonesHandler;
import com.nci.sfj.xmpp.iq.handler.IQPowerHandler;
import com.nci.sfj.xmpp.iq.handler.IQRunningHandler;
import com.nci.sfj.xmpp.iq.handler.IQSettingHandler;
import com.nci.sfj.xmpp.iq.handler.IQShakeHandler;
import com.nci.sfj.xmpp.iq.handler.IQSilentHandler;
import com.nci.sfj.xmpp.iq.handler.IQTimeHandler;
import com.nci.sfj.xmpp.iq.handler.IQVoltageHandler;
import com.nci.sfj.xmpp.iq.handler.IQWebServiceHandler;
import com.nci.sfj.xmpp.iq.handler.IQWorkStateHandler;
import com.nci.sfj.xmpp.session.ClientSession;
import com.nci.sfj.xmpp.session.Session;
import com.nci.sfj.xmpp.session.SessionManager;

/**
 * This class is to route IQ packets to their corresponding handler.
 * 
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class IQRouter {

	private final Log log = LogFactory.getLog(getClass());

	private SessionManager sessionManager;

	private List<IQHandler> iqHandlers = new ArrayList<IQHandler>();

	private Map<String, IQHandler> namespace2Handlers = new ConcurrentHashMap<String, IQHandler>();

	/**
	 * Constucts a packet router registering new IQ handlers.
	 */
	public IQRouter() {
		sessionManager = SessionManager.getInstance();
		iqHandlers.add(new IQAuthHandler());
		iqHandlers.add(new IQAlertHandler());
		iqHandlers.add(new IQExitHandler());
		iqHandlers.add(new IQFrequencyHandler());
		iqHandlers.add(new IQLocationHandler());
		iqHandlers.add(new IQPairHandler());
		iqHandlers.add(new IQPhonesHandler());
		iqHandlers.add(new IQPowerHandler());
		iqHandlers.add(new IQRunningHandler());
		iqHandlers.add(new IQSilentHandler());
		iqHandlers.add(new IQTimeHandler());
		iqHandlers.add(new IQVoltageHandler());
		iqHandlers.add(new IQDiskHandler());
		iqHandlers.add(new IQEQuantityHandler());
		iqHandlers.add(new IQCPUHandler());
		iqHandlers.add(new IQMemoryHandler());
		iqHandlers.add(new IQPhoneHandler());
		iqHandlers.add(new IQShakeHandler());
		iqHandlers.add(new IQCloseStateHandler());
		iqHandlers.add(new IQWorkStateHandler());
		iqHandlers.add(new IQConnectHandler());
		iqHandlers.add(new IQWebServiceHandler());
		iqHandlers.add(new IQNlocationHandler());
		iqHandlers.add(new IQSettingHandler());
		iqHandlers.add(new IQBaseStationHandler());
		iqHandlers.add(new IQDeviceStatusHandler());
		iqHandlers.add(new IQTransmitNewHandler());
	}

	/**
	 * Routes the IQ packet based on its namespace.
	 * 
	 * @param packet
	 *            the packet to route
	 */
	public void route(IQ packet) {
		if (packet == null) {
			throw new NullPointerException();
		}
		JID sender = packet.getFrom();
		ClientSession session = sessionManager.getSession(sender);

		if (session == null
				|| session.getStatus() == Session.STATUS_AUTHENTICATED
				|| ("jabber:iq:auth".equals(packet.getChildElement()
						.getNamespaceURI()))) {
			handle(packet);
		} else {
			IQ reply = IQ.createResultIQ(packet);
			reply.setChildElement(packet.getChildElement().createCopy());
			reply.setError(PacketError.Condition.not_authorized);
			session.process(reply);
		}
	}

	private void handle(IQ packet) {
		try {
			Element childElement = packet.getChildElement();
			String namespace = null;
			if (childElement != null) {
				namespace = childElement.getNamespaceURI();
			}
			if (namespace == null) {
				if (packet.getType() != IQ.Type.result
						&& packet.getType() != IQ.Type.error) {
					log.warn("Unknown packet " + packet);
				}
			} else {
				IQHandler handler = getHandler(namespace);
				if (handler == null) {
					sendErrorPacket(packet,
							PacketError.Condition.service_unavailable);
				} else {
					handler.process(packet);
				}
			}

		} catch (Exception e) {
			log.error("Could not route packet", e);
			Session session = sessionManager.getSession(packet.getFrom());
			if (session != null) {
				IQ reply = IQ.createResultIQ(packet);
				reply.setError(PacketError.Condition.internal_server_error);
				session.process(reply);
			}
		}
	}

	/**
	 * Senda the error packet to the original sender
	 */
	private void sendErrorPacket(IQ originalPacket,
			PacketError.Condition condition) {
		if (IQ.Type.error == originalPacket.getType()) {
			log.error("Cannot reply an IQ error to another IQ error: "
					+ originalPacket);
			return;
		}
		IQ reply = IQ.createResultIQ(originalPacket);
		reply.getElement().addAttribute("oldFrom",
				originalPacket.getElement().attributeValue("oldFrom"));
		reply.setChildElement(originalPacket.getChildElement().createCopy());
		reply.setError(condition);
		try {
			PacketDeliverer.deliver(reply);
		} catch (Exception e) {
			// Ignore
		}
	}

	/**
	 * Adds a new IQHandler to the list of registered handler.
	 * 
	 * @param handler
	 *            the IQHandler
	 */
	public void addHandler(IQHandler handler) {
		if (iqHandlers.contains(handler)) {
			throw new IllegalArgumentException(
					"IQHandler already provided by the server");
		}
		namespace2Handlers.put(handler.getNamespace(), handler);
	}

	/**
	 * Removes an IQHandler from the list of registered handler.
	 * 
	 * @param handler
	 *            the IQHandler
	 */
	public void removeHandler(IQHandler handler) {
		if (iqHandlers.contains(handler)) {
			throw new IllegalArgumentException(
					"Cannot remove an IQHandler provided by the server");
		}
		namespace2Handlers.remove(handler.getNamespace());
	}

	/**
	 * Returns an IQHandler with the given namespace.
	 */
	private IQHandler getHandler(String namespace) {
		IQHandler handler = namespace2Handlers.get(namespace);
		if (handler == null) {
			for (IQHandler handlerCandidate : iqHandlers) {
				if (namespace.equalsIgnoreCase(handlerCandidate.getNamespace())) {
					handler = handlerCandidate;
					namespace2Handlers.put(namespace, handler);
					break;
				}
			}
		}
		return handler;
	}
}
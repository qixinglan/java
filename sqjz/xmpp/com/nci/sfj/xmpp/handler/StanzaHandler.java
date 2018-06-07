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
package com.nci.sfj.xmpp.handler;

import java.io.IOException;
import java.io.StringReader;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;
import org.dom4j.io.XMPPPacketReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmpp.packet.IQ;
import org.xmpp.packet.PacketError;
import org.xmpp.packet.StreamError;

import com.nci.sfj.xmpp.codec.MXParser;
import com.nci.sfj.xmpp.router.PacketRouter;
import com.nci.sfj.xmpp.session.ClientSession;
import com.nci.sfj.xmpp.session.Session;

/**
 * This class is to handle incoming XML stanzas.
 * 
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class StanzaHandler {

	private static final Log log = LogFactory.getLog(StanzaHandler.class);
	protected Connection connection;
	protected Session session;
	protected String serverName;

	private boolean sessionCreated = false;

	private PacketRouter router;

	/**
	 * Constructor.
	 * 
	 * @param serverName
	 *            the server name
	 * @param connection
	 *            the connection
	 */
	public StanzaHandler(String serverName, Connection connection) {
		this.serverName = serverName;
		this.connection = connection;
		this.router = new PacketRouter();
	}

	//
	/**
	 * Process the received stanza using the given XMPP packet reader.
	 * 
	 * @param stanza
	 *            the received statza
	 * @param reader
	 *            the XMPP packet reader
	 * @throws Exception
	 *             if the XML stream is not valid.
	 */
	public void process(String stanza, XMPPPacketReader reader)
			throws Exception {
		boolean initialStream = stanza.startsWith("<stream:stream");
		if (!sessionCreated || initialStream) {
			if (!initialStream) {
				// Ignore <?xml version="1.0"?>
				return;
			}
			if (!sessionCreated) {
				// 初次连接之后建立session
				sessionCreated = true;
				MXParser parser = reader.getXPPParser();
				parser.setInput(new StringReader(stanza));
				createSession(parser);
			}
			return;
		}

		// If end of stream was requested
		if (stanza.equals("</stream:stream>")) {
			session.close();
			return;
		}
		// Ignore <?xml version="1.0"?>
		if (stanza.startsWith("<?xml")) {
			return;
		}
		// Create DOM object
		Element doc = reader.read(new StringReader(stanza)).getRootElement();
		if (doc == null) {
			return;
		}

		String tag = doc.getName();
		// 连接建立完成之后根据不同的消息进行不同的处理
		if ("iq".equals(tag)) {
			log.debug("iq...");
			processIQ(doc);
		} else {
			log.warn("Unexpected packet tag (not message, iq, presence)"
					+ doc.asXML());
			session.close();
		}

	}

	/**
	 * Description:处理请求-响应机制XML节
	 * 
	 * @author shuzz
	 * @since 2014年5月19日上午9:46:15
	 * @param doc
	 */
	private void processIQ(Element doc) {
		log.debug("processIQ()...");
		IQ packet;
		try {
			doc.addAttribute("oldFrom", doc.attributeValue("from"));
			packet = new IQ(doc, false);
		} catch (IllegalArgumentException e) {
			log.debug("Rejecting packet. JID malformed", e);
			IQ reply = new IQ();
			if (!doc.elements().isEmpty()) {
				reply.setChildElement(((Element) doc.elements().get(0))
						.createCopy());
			}
			reply.setID(doc.attributeValue("id"));
			reply.setTo(session.getAddress());
			String to = doc.attributeValue("to");
			if (to != null) {
				reply.getElement().addAttribute("from", to);
			}
			reply.setError(PacketError.Condition.jid_malformed);
			session.process(reply);
			return;
		}

		// if (packet.getID() == null) {
		// // IQ packets MUST have an 'id' attribute
		// StreamError error = new StreamError(
		// StreamError.Condition.invalid_xml);
		// session.deliverRawText(error.toXML());
		// session.close();
		// return;
		// }

		packet.setFrom(session.getAddress());
		router.route(packet);
		session.incrementClientPacketCount();
	}

	/**
	 * Description：基于命名空间创建发送用的session
	 * 
	 * @param xpp
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void createSession(XmlPullParser xpp)
			throws XmlPullParserException, IOException {
		for (int eventType = xpp.getEventType(); eventType != XmlPullParser.START_TAG;) {
			eventType = xpp.next();
		}
		// Create the correct session based on the sent namespace
		String namespace = xpp.getNamespace(null);
		if ("jabber:client".equals(namespace)) {
			session = ClientSession.createSession(serverName, connection, xpp);
			if (session == null) {
				StringBuilder sb = new StringBuilder(250);
				sb.append("<?xml version='1.0' encoding='UTF-8'?>");
				sb.append("<stream:stream from=\"").append(serverName);
				sb.append("\" id=\"").append(randomString(5));
				sb.append("\" xmlns=\"").append(xpp.getNamespace(null));
				sb.append("\" xmlns:stream=\"").append(
						xpp.getNamespace("stream"));
				sb.append("\" version=\"1.0\">");

				StreamError error = new StreamError(
						StreamError.Condition.bad_namespace_prefix);
				sb.append(error.toXML());
				connection.deliverRawText(sb.toString());
				connection.close();
				log.warn("Closing session due to bad_namespace_prefix in stream header: "
						+ namespace);
			} else {
				session.setStatus(Session.STATUS_AUTHENTICATED);
			}
		}
	}

	//
	// private boolean negotiateTLS() {
	// if (connection.getTlsPolicy() == Connection.TLSPolicy.disabled) {
	// // Set the not_authorized error
	// StreamError error = new StreamError(
	// StreamError.Condition.not_authorized);
	// connection.deliverRawText(error.toXML());
	// connection.close();
	// log.warn("TLS requested by initiator when TLS was never offered"
	// + " by server. Closing connection : " + connection);
	// return false;
	// }
	// // Client requested to secure the connection using TLS.
	// try {
	// startTLS();
	// } catch (Exception e) {
	// log.error("Error while negotiating TLS", e);
	// connection
	// .deliverRawText("<failure xmlns=\"urn:ietf:params:xml:ns:xmpp-tls\">");
	// connection.close();
	// return false;
	// }
	// return true;
	// }
	//
	// /**
	// * Description:开始TLS
	// *
	// * @author Shuzz
	// * @since 2014年5月19日下午4:37:57
	// * @throws Exception
	// */
	// private void startTLS() throws Exception {
	// Connection.ClientAuth policy;
	// try {
	// policy = Connection.ClientAuth.valueOf(Config.getString(
	// "xmpp.client.cert.policy", "disabled"));
	// } catch (IllegalArgumentException e) {
	// policy = Connection.ClientAuth.disabled;
	// }
	// connection.startTLS(policy);
	// }
	//
	// private void tlsNegotiated() {
	// // Offer stream features including SASL Mechanisms
	// StringBuilder sb = new StringBuilder(620);
	// sb.append("<?xml version='1.0' encoding='UTF-8'?>");
	// sb.append("<stream:stream ");
	// sb.append("xmlns:stream=\"http://etherx.jabber.org/streams\" ");
	// sb.append("xmlns=\"jabber:client\" from=\"");
	// sb.append(serverName);
	// sb.append("\" id=\"");
	// sb.append(session.getStreamID());
	// sb.append("\" xml:lang=\"");
	// sb.append(connection.getLanguage());
	// sb.append("\" version=\"");
	// sb.append(Session.MAJOR_VERSION).append(".")
	// .append(Session.MINOR_VERSION);
	// sb.append("\">");
	// sb.append("<stream:features>");
	// // Include specific features such as auth and register for client
	// // sessions
	// String specificFeatures = session.getAvailableStreamFeatures();
	// if (specificFeatures != null) {
	// sb.append(specificFeatures);
	// }
	// sb.append("</stream:features>");
	// connection.deliverRawText(sb.toString());
	// }
	//
	private String randomString(int length) {
		if (length < 1) {
			return null;
		}
		char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
				+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[new Random().nextInt(71)];
		}
		return new String(randBuffer);
	}

	// // public String getNamespace() {
	// // return "jabber:client";
	// // }

}

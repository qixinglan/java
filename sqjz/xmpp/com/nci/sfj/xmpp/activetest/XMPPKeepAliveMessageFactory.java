package com.nci.sfj.xmpp.activetest;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.xmpp.packet.IQ;
import org.xmpp.packet.IQ.Type;

/**
 * Description:实现心跳工厂
 * 
 * @author Shuzz
 * @since 2014-1-17下午2:02:49
 */
public class XMPPKeepAliveMessageFactory implements KeepAliveMessageFactory {

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger
			.getLogger(XMPPKeepAliveMessageFactory.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.filter.keepalive.KeepAliveMessageFactory#getRequest(org
	 * .apache.mina.core.session.IoSession)
	 */
	@Override
	public Object getRequest(IoSession session) {
		/** 返回预设语句 */
		IQ requset = new IQ(Type.get);
		DocumentFactory factory = DocumentFactory.getInstance();
		Element e = factory.createElement("heart");
		e.addNamespace("", "dzjg:iq:heart");
		requset.setChildElement(e);
		return requset.toXML();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.filter.keepalive.KeepAliveMessageFactory#getResponse(
	 * org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public Object getResponse(IoSession session, Object request) {
		String message = (String) request;
		String resp = message.replace("type=\"get\"", "type=\"result\"");
		resp = resp.replace("type=\"set\"", "type=\"result\"");
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.filter.keepalive.KeepAliveMessageFactory#isRequest(org
	 * .apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public boolean isRequest(IoSession session, Object message) {
		if (!(message instanceof String)) {
			return false;
		} else {
			String iq = (String) message;
			if (iq.startsWith("<iq")) {
				if (iq.contains("dzjg:iq:heart")) {
					if (iq.split("type=").length == 2) {
						String type = iq.split("type=")[1];
						type = type.replaceAll("\"", "");
						if (type.startsWith("set") || type.startsWith("get")) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.filter.keepalive.KeepAliveMessageFactory#isResponse(org
	 * .apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public boolean isResponse(IoSession session, Object message) {
		if (!(message instanceof String)) {
			return false;
		} else {
			String iq = (String) message;
			if (iq.startsWith("<iq")) {
				if (iq.contains("dzjg:iq:heart")) {
					if (iq.split("type=").length == 2) {
						String type = iq.split("type=")[1];
						type = type.replaceAll("\"", "");
						if (type.startsWith("error")
								|| type.startsWith("result")) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}

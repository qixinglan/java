package com.nci.sfj.location.activetest;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

import com.nci.sfj.location.LocationConstants;
import com.nci.sfj.location.LocationMessage;

/**
 * Description:实现心跳工厂
 * 
 * @author Shuzz
 * @since 2014-1-17下午2:02:49
 */
public class LocationKeepAliveMessageFactory implements KeepAliveMessageFactory {

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
		LocationMessage message = new LocationMessage(
				LocationConstants.TYPE_HEART);
		return message;
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
		LocationMessage message = (LocationMessage) request;
		LocationMessage resp = new LocationMessage(
				LocationConstants.TYPE_HEART_ACK, message.getSequenceId());
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
		if (!(message instanceof LocationMessage)) {
			return false;
		} else {
			LocationMessage loc = (LocationMessage) message;
			if (LocationConstants.TYPE_HEART == loc.getType()) {
				return true;
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
		if (!(message instanceof LocationMessage)) {
			return false;
		} else {
			LocationMessage loc = (LocationMessage) message;
			if (LocationConstants.TYPE_HEART_ACK == loc.getType()) {
				return true;
			}
		}
		return false;
	}
}

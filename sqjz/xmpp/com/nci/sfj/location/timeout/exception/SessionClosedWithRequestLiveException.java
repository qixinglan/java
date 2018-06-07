package com.nci.sfj.location.timeout.exception;

import java.util.List;

public class SessionClosedWithRequestLiveException extends RuntimeException {

	private static final long serialVersionUID = 2825968392048213002L;

	private final List<Object> sendMessage;

	public SessionClosedWithRequestLiveException(List<Object> sendMessage) {
		super();
		this.sendMessage = sendMessage;
	}

	public List<Object> getSendMessage() {
		return sendMessage;
	}

}
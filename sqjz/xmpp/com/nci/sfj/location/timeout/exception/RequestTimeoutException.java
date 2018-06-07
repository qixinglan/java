package com.nci.sfj.location.timeout.exception;

public class RequestTimeoutException extends RuntimeException {
	private static final long serialVersionUID = 5546784978950631652L;

	private final Object message;

	/**
	 * Creates a new exception.
	 */
	public RequestTimeoutException(Object message) {
		if (message == null) {
			throw new IllegalArgumentException("message");
		}
		this.message = message;
	}

	public Object getMessageFromException() {
		return message;
	}

}
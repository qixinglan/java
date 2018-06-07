package com.nci.sfj.location.timeout.exception;

/**
 * Description:重发次数过多
 * 
 * @author Shuzz
 * @since 2014-2-11下午2:31:31
 */
public class ResendTimeTooMuchException extends RuntimeException {

	private static final long serialVersionUID = 3216515372350959726L;
	private final Object message;

	/**
	 * Creates a new exception.
	 */
	public ResendTimeTooMuchException(Object message) {
		if (message == null) {
			throw new IllegalArgumentException("message");
		}
		this.message = message;
	}

	public Object getMessageFromException() {
		return message;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "消息重发次数过多";
	}

}
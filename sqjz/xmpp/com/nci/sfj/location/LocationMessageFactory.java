package com.nci.sfj.location;

import com.nci.sfj.location.connect.MessageWrapper;
import com.nci.sfj.location.connect.QueuableMessageInspector;
import com.nci.sfj.location.timeout.MessageFactory;

/**
 * Description:用以小灵通超时重发机制获取命令和应答实体
 * 
 * @author Shuzz
 * @since 2014-1-24上午10:26:12
 */
public class LocationMessageFactory extends MessageFactory implements
		MessageWrapper, QueuableMessageInspector {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nci.dxbjdl.mina.filter.MessageFactory#isRequest(java.lang.Object)
	 */
	@Override
	public boolean isRequest(Object message) {
		// TODO Auto-generated method stub
		if (message instanceof LocationMessage) {
			if (LocationConstants.TYPE_DATA == ((LocationMessage) message)
					.getType()) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nci.dxbjdl.mina.filter.MessageFactory#isResponse(java.lang.Object)
	 */
	@Override
	public boolean isResponse(Object message) {
		if (message instanceof LocationMessage) {
			if (LocationConstants.TYPE_DATA_ACK == ((LocationMessage) message)
					.getType()) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nci.dxbjdl.mina.filter.MessageFactory#getRequestId(java.lang.Object)
	 */
	@Override
	public Object getRequestId(Object message) {
		if (isRequest(message)) {
			LocationMessage locMessage = (LocationMessage) message;
			return locMessage.getSequenceId();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nci.dxbjdl.mina.filter.MessageFactory#getResponseId(java.lang.Object)
	 */
	@Override
	public Object getResponseId(Object message) {
		if (isResponse(message)) {
			LocationMessage locMessage = (LocationMessage) message;
			return locMessage.getSequenceId();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nci.dxbjdl.mina.filter.MessageFactory#getPeriod()
	 */
	@Override
	public int getPeriod() {
		return period;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nci.dxbjdl.mina.filter.MessageFactory#getResendTime()
	 */
	@Override
	public int getResendTime() {
		return resendTime;
	}

	private int period;

	private int resendTime;

	public void setPeriod(int period) {
		this.period = period;
	}

	public void setResendTime(int resendTime) {
		this.resendTime = resendTime;
	}

	@Override
	public int getQPriority(Object message) {
		return -1;
	}

	@Override
	public String getMsgUnicode(Object message) {
		return null;
	}

	@Override
	public Object wrapSubmit(Object submit) {
		// TODO Auto-generated method stub
		return null;
	}

}

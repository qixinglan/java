package com.nci.sfj.location.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nci.sfj.location.LocationMessage;
import com.nci.sfj.xmpp.exception.ConnectionException;

/**
 * Description:LBS请求发送的接口
 * 
 * @author Shuzz
 * 
 */
@Service
public class LocationSenderService {

	@Autowired
	private LocationSender locationSender;

	/**
	 * Description:通过发送队列发送LBS请求
	 * 
	 * @author Shuzz
	 * @param message
	 */
	public void addLocationRequest(LocationMessage message) {
		locationSender.addAlarmMessage(message);
		locationSender.notifySend();
	}

	/**
	 * Description:不通过队列，马上发送信息
	 * 
	 * @author Shuzz
	 * @param message
	 * @throws ConnectionException
	 */
	public void sendWithoutQueuing(LocationMessage message)
			throws ConnectionException {
		locationSender.sendWithoutQueuing(message);
	}
}
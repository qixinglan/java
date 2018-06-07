package com.nci.sfj.business.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nci.sfj.business.service.XmppBusinessService;

/**
 * Description:主动请求LBS定位的定时任务
 * 
 * @author Shuzz
 * @since 2014年8月25日上午11:04:15
 * 
 */
@Component
public class DeviceLBSJob {
	@Autowired
	private XmppBusinessService xmppBusinessService;

	public void startTimer() {
		try {
			xmppBusinessService.checkDeviceLBS();
		} catch (Throwable t) {
		}
	}

}

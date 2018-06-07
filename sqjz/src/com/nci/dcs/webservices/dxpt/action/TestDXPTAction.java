package com.nci.dcs.webservices.dxpt.action;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.webservices.dxpt.DXPTSendException;
import com.nci.dcs.webservices.dxpt.service.DXPTService;
import com.opensymphony.xwork2.ActionSupport;

public class TestDXPTAction extends ActionSupport {
	@Autowired
	private DXPTService service;
	public String send() throws Throwable {
		try {
			service.sendPlatformMessage("18600364860", "夏先智", "电子监管测试短信");
		} catch (DXPTSendException e) {
			e.printStackTrace();
		}
		return "none";
	}
	public String presend() throws Throwable {
		try {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.HOUR, 1);
			service.sendPlatformMessage("18600364860", "夏先智", "电子监管测试定时短信", cal.getTime());
		} catch (DXPTSendException e) {
			e.printStackTrace();
		}
		return "none";
	}
}

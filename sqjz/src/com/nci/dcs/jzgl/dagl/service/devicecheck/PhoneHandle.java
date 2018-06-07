package com.nci.dcs.jzgl.dagl.service.devicecheck;

import org.dom4j.Element;

public class PhoneHandle extends DeviceCheckHandleImpl {

	@Override
	public String success(String text, Object attachment) {
		String result = "true@";
		if (attachment instanceof Element) {
			Element e = (Element) attachment;
			String value = e.elementText("number");
			result += "手机号：" + value;
		}
		return result;
	}

}
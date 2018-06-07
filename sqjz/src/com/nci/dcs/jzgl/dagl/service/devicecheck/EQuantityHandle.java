package com.nci.dcs.jzgl.dagl.service.devicecheck;

import org.dom4j.Element;

public class EQuantityHandle extends DeviceCheckHandleImpl {

	@Override
	public String success(String text, Object attachment) {
		String result = "true@";
		if (attachment instanceof Element) {
			Element e = (Element) attachment;
			String value = e.element("value").getText();
			result += "剩余电量：" + value;
		}
		return result;
	}

}
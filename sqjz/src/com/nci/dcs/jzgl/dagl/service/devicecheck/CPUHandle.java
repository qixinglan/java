package com.nci.dcs.jzgl.dagl.service.devicecheck;

import org.dom4j.Element;

public class CPUHandle extends DeviceCheckHandleImpl {

	@Override
	public String success(String text, Object attachment) {
		String result = "true@";
		if (attachment instanceof Element) {
			Element e = (Element) attachment;
			String total = e.elementText("total");
			String percent = e.elementText("percent");
			result += "CPU：" + total+" ，使用： "+percent;
		}
		return result;
	}

}
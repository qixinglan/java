package com.nci.dcs.jzgl.dagl.service.devicecheck;

import org.dom4j.Element;

public class WorkStateHandle extends DeviceCheckHandleImpl {

	@Override
	public String success(String text, Object attachment) {
		String result = "";
		if (attachment instanceof Element) {
			Element e = (Element) attachment;
			String total = e.elementText("result");
			if ("true".equals(total)) {
				result = "true@硬件工作正常";
			} else {
				result = "false@硬件可能存在异常";
			}
		}
		return result;
	}

}
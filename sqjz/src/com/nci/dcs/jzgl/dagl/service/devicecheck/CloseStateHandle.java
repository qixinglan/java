package com.nci.dcs.jzgl.dagl.service.devicecheck;

import org.dom4j.Element;

public class CloseStateHandle extends DeviceCheckHandleImpl {

	@Override
	public String success(String text, Object attachment) {
		String result = "";
		if (attachment instanceof Element) {
			Element e = (Element) attachment;
			String total = e.elementText("result");
			if ("true".equals(total)) {
				result = "true@腕表闭合正常";
			} else {
				result = "false@腕表可能未正常闭合";
			}
		}
		return result;
	}
}
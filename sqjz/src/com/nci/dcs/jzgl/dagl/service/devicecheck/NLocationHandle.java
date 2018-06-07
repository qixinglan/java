package com.nci.dcs.jzgl.dagl.service.devicecheck;

import org.dom4j.Element;

import com.nci.dcs.common.utils.CommonUtils;

public class NLocationHandle extends DeviceCheckHandleImpl {

	@Override
	public String success(String text, Object attachment) {
		String result = "";
		if (attachment instanceof Element) {
			Element e = (Element) attachment;
			String x = e.elementText("X");
			String y = e.elementText("Y");
			if (CommonUtils.isNull(x)||CommonUtils.isNull(y)) {
				result = "false@坐标有误";
			} else {
				result = "true@坐标为("+x+","+y+")";
			}
		}
		return result;
	}
}
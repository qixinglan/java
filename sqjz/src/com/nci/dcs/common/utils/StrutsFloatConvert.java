package com.nci.dcs.common.utils;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class StrutsFloatConvert extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		if(Float.class == arg2){
			String str = arg1[0];
			return Float.parseFloat(str);
		}
		return 0;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		return arg1.toString();
	}

}

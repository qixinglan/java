package com.nci.dcs.common.utils;

import java.text.ParseException;
import java.util.Date;

import com.opensymphony.xwork2.conversion.TypeConverter;
import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

@SuppressWarnings("rawtypes")
public class DateTypeConerter extends DefaultTypeConverter implements
		TypeConverter {
	@Override
	public Object convertValue(Object arg0, Class arg1) {
		Date result = null;
		try {
			String[] values = (String[]) arg0;
			result = DateTimeFmtSpec.getMinutesFormat().parse(values[0]);
		} catch (ParseException e) {
			return super.convertValue(arg0, arg1);
		}
		return result;
	}

}

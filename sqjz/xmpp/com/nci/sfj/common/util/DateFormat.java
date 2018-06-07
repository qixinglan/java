package com.nci.sfj.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.nci.dcs.common.utils.CommonUtils;

public class DateFormat {

	public static double tranStrToDouble(String value) {
		double result = new Double(0);
		try {
			result = Double.valueOf(value);
		} catch (Exception e) {

		}
		return result;
	}

	public static Double tranStrToDoubleNull(String value) {
		Double result = null;
		try {
			result = Double.valueOf(value);
		} catch (Exception e) {

		}
		return result;
	}

	public static Date tranStrToDate(String value) {
		Date date = new Date();
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
			date = sf.parse(value);
		} catch (Exception e) {

		}
		return date;
	}

	public static String tranDateToStr(Date value) {
		String date = "";
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
			date = sf.format(value);
		} catch (Exception e) {

		}
		return date;
	}

	public static String changeDateFormat(SimpleDateFormat source,
			SimpleDateFormat target, String time) {
		String date = "";
		if (!CommonUtils.isNull(time)) {
			try {
				Date temp = source.parse(time);
				date = target.format(temp);
			} catch (Exception e) {

			}
		}
		return date;
	}
}

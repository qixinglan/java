package com.nci.dcs.common.utils;

import java.text.SimpleDateFormat;

public class DateTimeFmtSpec {
	public static final String TIMEZONE = "GMT-8";
	public static final String DATE = "yyyy-MM-dd";
	public static final String TIME = "HH:mm:ss";
	public static final String MINUTES = "yyyy-MM-dd HH:mm";
	public static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss.S";
	public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
	public static final String MONTH = "yyyy-MM";
	
	public static SimpleDateFormat getTimeFormat(){
		return new SimpleDateFormat(TIME);
	}
	
	public static SimpleDateFormat getDateFormat(){
		return new SimpleDateFormat(DATE);
	}
	
	public static SimpleDateFormat getDateTimeFormat(){
		return new SimpleDateFormat(DATETIME);
	}
	
	public static SimpleDateFormat getTimestampFormat(){
		return new SimpleDateFormat(TIMESTAMP);
	}
	
	public static SimpleDateFormat getMinutesFormat(){
		return new SimpleDateFormat(MINUTES);
	}
	
	public static SimpleDateFormat getMonthFormat(){
		return new SimpleDateFormat(MONTH);
	}
}

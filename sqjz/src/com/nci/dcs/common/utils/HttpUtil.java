package com.nci.dcs.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HttpUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss.S z", Locale.US);
	public static String date2LastModified(Date date){
		return sdf.format(date);
	}
	public static Date ifModified2Date(String ifModified){
		try{
			return sdf.parse(ifModified);
		}
		catch(Exception e){
			return new Date(70, 0, 1);
		}
	}
}

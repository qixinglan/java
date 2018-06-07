package com.nci.dcs.jzgl.sync.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.xwork.StringUtils;

import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * Description:同步接口数据格式化类
 *
 */
public class FormatUtil {
	private static SimpleDateFormat dateFmt = DateTimeFmtSpec.getDateFormat();
	
	public static String safeFormat(Date date, SimpleDateFormat fmt){
		if (date != null){
			return fmt.format(date);
		}
		return "";
	}
	
	public static String safeFormat(Date date, String fmt){
		return safeFormat(date, new SimpleDateFormat(fmt));
	}
	
	public static String date(Date date){
		return safeFormat(date, dateFmt);
	}
	
	public static String yesOrNo(String value){
		return StringUtils.isBlank(value) ? "2" : "1";
	}
	
	public static String mutipleDict(String value){
		if (!StringUtils.isBlank(value)){
			return value.replace(",", "|");
		}
		else{
			return "other";
		}
	}
	public static String mutipleDictForZuiMing(String value){
		if (!StringUtils.isBlank(value)){
			return value.replace(",", "、");
		}
		else{
			return "other";
		}
	}
}

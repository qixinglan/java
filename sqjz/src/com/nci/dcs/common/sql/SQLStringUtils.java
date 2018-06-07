package com.nci.dcs.common.sql;

import org.apache.commons.lang.StringUtils;

public class SQLStringUtils {
	
	public static String escape(String value){
		if (value != null){
			value = StringUtils.replace(value, "'", "''");
			value = StringUtils.replace(value, "%", "\\%");
			value = StringUtils.replace(value, "_", "||_");
		}
		return value;
	}
}

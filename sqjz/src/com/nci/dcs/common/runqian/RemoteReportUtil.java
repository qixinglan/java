package com.nci.dcs.common.runqian;

import javax.servlet.http.HttpServletRequest;

import com.nci.dcs.common.utils.PropertyUtil;

public class RemoteReportUtil {
	public static final boolean useRemote;
	/**
	 * should ends with '/'
	 */
	public static final String remoteURLPrefix;
	
	static {
		boolean _useRemote = false;
		try {
			_useRemote = Boolean.parseBoolean(PropertyUtil.getConfigInfomations("remoteReport", "report.remote"));
		} catch (Exception e){}
		useRemote = _useRemote;
		
		String prefix = PropertyUtil.getConfigInfomations("remoteReport", "report.remote.url");
		remoteURLPrefix = prefix == null ? "" : prefix;
	}
	
	public static String getReportUrl(HttpServletRequest request){
		return useRemote ? remoteURLPrefix : request.getContextPath() + "/";
	}
	public static String getReportUrl(HttpServletRequest request, String path){
		return (useRemote ? remoteURLPrefix : request.getContextPath() + "/") + path;
	}
}

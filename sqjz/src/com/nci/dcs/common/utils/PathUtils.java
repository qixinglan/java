package com.nci.dcs.common.utils;

import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class PathUtils extends HttpServlet {
	private static String ROOTPATH;

	public void init() {
		String webAppRootKey = getServletContext().getRealPath("/");
		PathUtils.setPath(webAppRootKey);
	}
	/*
	 * 获取应用物理路径
	 */
	public static String getRealPath() {
		return ROOTPATH;
	}

	private static void setPath(String path) {
		ROOTPATH = path;
	}

}

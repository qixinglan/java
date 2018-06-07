package com.nci.dcs.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;


//该类提供struts静态获取当前session的方法，仅struts的action和service中使用
public class StrutsSessionManager {
	public static HttpSession getSession(){
		HttpServletRequest request = ServletActionContext.getRequest();
		return request.getSession();
	}
}

package com.nci.dcs.common.utils;

import javax.servlet.http.HttpSession;

import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.User;

public class LoginInfoUtils {
	public static String getCASUsername(HttpSession session){
		return (String)session.getAttribute("edu.yale.its.tp.cas.client.filter.user");
	}
	
	public static User getUser(HttpSession session){
		User user = (User) session.getAttribute("user");
		return user;
	}
	
	public static void setUser(HttpSession session, User user){
		session.setAttribute("user", user);
	}
	
	public static boolean isLogin(HttpSession session){
		return getUser(session) != null;
	}
	
	public static Bmb getCurOrg(HttpSession session){
		//获取当前登录用户的工作单位
		User user = getUser(session);
		if (user != null){
			return user.getWunit();
		}
		return null;
	}
	
	public static String getUserId(HttpSession session){
		User user = getUser(session);
		return user == null ? "" : user.getId();
	}
	
	public static String getCurOrgId(HttpSession session){
		Bmb org = getCurOrg(session);
		return org == null ? "" : org.getBm();
	}
	public static String getOrgType(HttpSession session){
		Bmb org = getCurOrg(session);
		if (org.isQxsfj()){
			return "qxsfj";
		}
		else if (org.isSfs()){
			return "sfs";
		}else if (org.isSj()){
			return "sj";
		}
		return "others";
	}
	
	public static boolean isCAS(HttpSession session){
		String useCAS = session.getServletContext().getInitParameter("useCAS");
		return "true".equals(useCAS);
	}
	
	public static String getLoginUrl(HttpSession session){
		if (isCAS(session)){
			return "/SSOLogin.action";
		}
		else{
			return "/system/login.jsp";
		}
	}
	
	//因为退出登录URL可能是外部URL，因此此处返回绝对URL
	public static String getLogoutUrl(HttpSession session){
		if (isCAS(session)){
			String logoutUrl = session.getServletContext().getInitParameter("logoutUrl");
			return logoutUrl;
		}
		return session.getServletContext().getContextPath() + "/sys/user/logout.action";
	}
	public static Integer getRoleId(HttpSession session){
		return  (Integer) session.getAttribute("roleId");
	}
}

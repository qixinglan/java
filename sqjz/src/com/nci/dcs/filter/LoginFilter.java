package com.nci.dcs.filter;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.util.UrlPathHelper;

import com.nci.dcs.common.utils.LoginInfoUtils;

//different from online
//import com.nci.dcs.system.utils.SystemLicenseiUtil;   

public class LoginFilter implements Filter {
	private static HashSet<String> anonymous;
	
	//different from online
	//private static HashSet<String> authormous;
	
	// 不需登录的URL
	static {
		anonymous = new HashSet<String>();
		anonymous.add("/system/login.jsp");
		anonymous.add("/sys/user/login.action");
		
		//different from online
		/*
		authormous = new HashSet<String>();
		authormous.add("/system/authorise.jsp");
		authormous.add("/sys/authorise/sysInfo.action");
		authormous.add("/sys/authorise/authorise.action");
		*/
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		//different from online
		/*
		try {
		*/
			String currentUrl = new UrlPathHelper()
					.getLookupPathForRequest(req);

		//different from online
		/*
			SystemLicenseiUtil.init();
			if ((SystemLicenseiUtil.hasLicense())
					&& (!SystemLicenseiUtil.licenseTimeOut())
					&& (!SystemLicenseiUtil.dataBaseTimeOut())) {
		*/
				String loginUrl = LoginInfoUtils.getLoginUrl(session);
				// WebRoot根目录下的URL不拦截，通用页面放入此处
				// anonymous列表中的URL不拦截
				if (currentUrl.indexOf('/', 1) >= 0
						&& !anonymous.contains(currentUrl)
						&& !LoginInfoUtils.isLogin(session)) {
					res.sendRedirect(req.getContextPath() + loginUrl);// 放弃请求，重定向
				} else {
					chain.doFilter(request, response);
				}
		//different from online
		/*
			} else if (!authormous.contains(currentUrl)) {
				// 服务器未授权，且访问非授权页面，跳转至授权页面
				res.sendRedirect(req.getContextPath() + "/system/authorise.jsp");
			} else {
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		*/
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

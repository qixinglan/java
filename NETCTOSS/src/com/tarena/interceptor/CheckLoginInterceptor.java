package com.tarena.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tarena.dao.Admin;

public class CheckLoginInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		String url=request.getRequestURL().toString();
		int currentModule=0;
		if(url.contains("role")){
			currentModule=1;
		}else if(url.contains("admin")){
			currentModule=2;
		}else if(url.contains("cost")){
			currentModule=3;
		}else if(url.contains("account")){
			currentModule=4;
		}else if(url.contains("service")){
			currentModule=5;
		}
		HttpSession session=request.getSession();
		session.setAttribute("currentModule", currentModule);
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		// TODO Auto-generated method stub
		String url=request.getRequestURL().toString();
		int currentModule=0;
		if(url.contains("role")){
			currentModule=1;
		}else if(url.contains("admin")){
			currentModule=2;
		}else if(url.contains("cost")){
			currentModule=3;
		}else if(url.contains("account")){
			currentModule=4;
		}else if(url.contains("service")){
			currentModule=5;
		}
		HttpSession session=request.getSession();
		session.setAttribute("currentModule", currentModule);
		Admin admin=(Admin)session.getAttribute("admin");
		if(admin==null){
			response.sendRedirect(request.getContextPath()+"/login/login.do");
			return false;
		}
		return true;
	}

}

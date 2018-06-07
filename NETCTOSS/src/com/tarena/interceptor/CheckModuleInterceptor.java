package com.tarena.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tarena.dao.Module;

public class CheckModuleInterceptor implements  HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		List<Module> list=(List<Module>)session.getAttribute("adminModules");
		Integer currentModule=(Integer)session.getAttribute("currentModule");
		for(Module i:list){
			if(i.getModule_id()==currentModule){
				return true;
			}
		}
		response.sendRedirect(request.getContextPath()+"/login/nopower.do");
		return false;
		
	}

}

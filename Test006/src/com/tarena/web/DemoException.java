package com.tarena.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class DemoException implements HandlerExceptionResolver{

	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3) {
		// TODO Auto-generated method stub
		System.out.println("DemoException");
		ModelMap m=new ModelMap();
		if(arg3 instanceof RuntimeException){
			m.put("error","自定义异常RuntimeException");
		}
		return new ModelAndView("error",m);
	}

}

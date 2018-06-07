package com.tarena.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
//@Resource
//@RequestMapping
public class HelloController implements Controller{
	//处理请求的方法
//	@RequestMapping("/a.form")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("hello");
		//一般调用M业务层如userService
		request.setAttribute("message","Hello World!");
		//转发到hello.jsp
		return new ModelAndView("hello");
		//返回值发给   视图处理器
	}

}

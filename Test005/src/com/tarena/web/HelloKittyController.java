package com.tarena.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("demo/")
public class HelloKittyController {
	@RequestMapping("hello.form")
	public String helloKitty(HttpServletRequest request){
		request.setAttribute("message","hello kitty");
		System.out.println("hello kitty");
		return "hello";
	}
	@RequestMapping("hello1.form")
	public ModelAndView login(HttpServletRequest request){
		request.setAttribute("message","hello kitty1");
		return new ModelAndView("hello");
	}
}

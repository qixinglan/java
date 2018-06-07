package com.tarena.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoController {
	@RequestMapping("/hello.form")
	public String hello(ModelMap map){
		map.put("msg", "hello");
		System.out.println("hello Controller");
		return "success";
	}
	@RequestMapping("/demo.form")
	
	public  String  testException(){
		String a[]=new String[3];
		System.out.println(a[4]);
		return "success";
	}
	@ExceptionHandler
	public String dealException(Exception e,HttpServletRequest request){
		if(e instanceof RuntimeException){
			request.setAttribute("error","RuntimeException");
		}
		return "error";
	}
}

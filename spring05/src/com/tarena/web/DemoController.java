package com.tarena.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	public String demoException() throws DemoException{
		String s=null;
		if(s==null){
			throw new DemoException("≤‚ ‘“Ï≥£");	
		}
		return "success";
	}
}

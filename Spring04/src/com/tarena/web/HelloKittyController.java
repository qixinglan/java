package com.tarena.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")//父路径
public class HelloKittyController {
	//请求路径为 /Spring/demo/hello.form
	@RequestMapping("/hello.form")//子路径
	public String execte(HttpServletRequest req){
		System.out.println("执行HelloKitty");
		req.setAttribute("message", "Hello Kitty!");
		return "hello";//转发到hello.jsp
	}
}

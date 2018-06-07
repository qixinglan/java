package com.tarena.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/*
 * web.xml配置了*.form
 * 后缀可以省略(.form)
 */
@Controller
public class helloController3 {
	@RequestMapping("/hello3")//子路径
	public void execte(HttpServletRequest req){
		System.out.println("执行HelloKitty");
		req.setAttribute("message", "Hello Kitty3333!");
	}
}

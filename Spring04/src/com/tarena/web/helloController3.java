package com.tarena.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/*
 * web.xml������*.form
 * ��׺����ʡ��(.form)
 */
@Controller
public class helloController3 {
	@RequestMapping("/hello3")//��·��
	public void execte(HttpServletRequest req){
		System.out.println("ִ��HelloKitty");
		req.setAttribute("message", "Hello Kitty3333!");
	}
}

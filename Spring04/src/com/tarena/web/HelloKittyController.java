package com.tarena.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")//��·��
public class HelloKittyController {
	//����·��Ϊ /Spring/demo/hello.form
	@RequestMapping("/hello.form")//��·��
	public String execte(HttpServletRequest req){
		System.out.println("ִ��HelloKitty");
		req.setAttribute("message", "Hello Kitty!");
		return "hello";//ת����hello.jsp
	}
}

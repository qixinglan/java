package com.tarena.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/login")
public class LoginController {
	@RequestMapping("/login.form")
	public String loginForm(){
		return "login";
	}
	@RequestMapping("/login-action1.form")
	public String loginAction1(HttpServletRequest request){
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		System.out.println(name);
		System.out.println(pwd);
		return "login";
	}
	@RequestMapping("/login-action2.form")
	public String loginAction2(String name,String pwd){
		System.out.println(name);
		System.out.println(pwd);
		return "login";
	}
	@RequestMapping("/login-action3.form")
	public String loginAction2(@RequestParam("pwd")String password){
		System.out.println(password);
		return "login";
	}
	@RequestMapping("/login-action4.form")
	public String loginAction4(FormBean formBean){
		System.out.println(formBean.getName());
		System.out.println(formBean.getPwd());
		return "login";
	}
}

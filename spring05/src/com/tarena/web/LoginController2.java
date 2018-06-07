package com.tarena.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tarena.entity.Worker;
import com.tarena.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController2 {
	@Resource
	private LoginService  loginService;
	@RequestMapping("/login-action4.form")
	public String loginAction4(@ModelAttribute("name")String name,String pwd,HttpServletRequest req){
			Worker worker=loginService.login(name, pwd);
			if(worker==null){
				throw new NullPointerException();
			}
			req.getSession().setAttribute(Consts.LOGIN_WORKER,worker);
			return "success";
	}
	@ExceptionHandler
	public String Execute(HttpServletRequest req,Exception e) throws Exception{
		if(e instanceof NullPointerException){
			req.setAttribute("error9", "用户名或密码为空");
			return "login";
		}
		return "success";
	}
}

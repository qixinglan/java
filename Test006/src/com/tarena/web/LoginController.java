package com.tarena.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tarena.entity.Worker;
import com.tarena.service.LoginService;
@Controller
@RequestMapping("/login")
public class LoginController {
	@Resource
	LoginService loginService;
	@RequestMapping("/login.form")
	public String login(){
		return "login";
	}
	@RequestMapping("/login-action1.form")
	public String action1(String name,String pwd,HttpServletRequest request){
		name=name.trim();
		pwd=pwd.trim();
		Boolean b=true;
		
		if(name.equals("")){
			request.setAttribute("error1", "用户名不能为空");
			b=false;
		}
		if(pwd.equals("")){
			request.setAttribute("error2", "密码不能为空");
			b=false;
		}
		
		if(!b){
			return "login";
		}
		Worker worker=loginService.login(name, pwd);
		if(worker==null){
			request.setAttribute("error", "用户名或密码错误");
			return "login";
		}
		else{
			request.getSession().setAttribute(Consts.LOGIN_WORKER,worker);
			return "success";
		}
		
	}
}

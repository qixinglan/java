package com.tarena.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tarena.entity.Worker;
import com.tarena.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Resource
	private LoginService loginService;
	@RequestMapping("/login.do")
	public String loginAction(String name,String pwd){
		name=name.trim();
		pwd=pwd.trim();
		Worker worker=loginService.login(name, pwd);
		if(worker==null){
			
		}
		return "success";
	}
}
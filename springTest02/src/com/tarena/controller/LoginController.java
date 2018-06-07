package com.tarena.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tarena.entity.Worker;
import com.tarena.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Resource
	LoginService loginService;
	@RequestMapping("/login.do")
	public String l(){
		return "login";
	}
	@RequestMapping("/action.do")
	public String login(String name,String pwd,
			ModelMap model,HttpServletRequest req){
		System.out.println(name);
		name=name.trim();
		pwd=pwd.trim();
		boolean b=false;
		System.out.println("csc");
		if(name.equals("")){
			b=true;
			model.addAttribute("error1","用户名为空");
		}
		if(pwd.equals("")){
			b=true;
			model.addAttribute("error2","密码为空");
		}
		if(b){
			return "login";
		}
		else{
			Worker worker=loginService.login(name, pwd);
			System.out.println(worker);
			if(worker==null){
				throw new NullPointerException();
			}
			
			else{
				req.getSession().setAttribute("worker", worker);
				return "success";
			}
		}
	}
	
	@ModelAttribute("message")
	public String getMessage(){
		return "天气预报";
	}
	@ExceptionHandler
	public String Execute(HttpServletRequest req,Exception e) throws Exception{
		if(e instanceof NullPointerException){
			req.setAttribute("error", "用户名或密码错误");
			return "login";
		}
		return "success";
	}
}

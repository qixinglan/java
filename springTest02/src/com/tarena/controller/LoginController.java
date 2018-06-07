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
			model.addAttribute("error1","�û���Ϊ��");
		}
		if(pwd.equals("")){
			b=true;
			model.addAttribute("error2","����Ϊ��");
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
		return "����Ԥ��";
	}
	@ExceptionHandler
	public String Execute(HttpServletRequest req,Exception e) throws Exception{
		if(e instanceof NullPointerException){
			req.setAttribute("error", "�û������������");
			return "login";
		}
		return "success";
	}
}

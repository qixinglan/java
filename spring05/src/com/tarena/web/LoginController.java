package com.tarena.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tarena.entity.Worker;
import com.tarena.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Resource
	LoginService loginService;
	
	@RequestMapping("/login.form")
	public String a(){
		return "login";
	}
	@RequestMapping("/login-action1.form")
	public String b(HttpServletRequest  request){
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		name=name.trim();
		pwd=pwd.trim();
		Boolean b=true;
		if(name.equals("")){
			b=false;
			request.setAttribute("error1", "�û�������Ϊ��");
		}
		if(pwd.equals("")){
			b=false;
			request.setAttribute("error2", "���벻��Ϊ��");
		}
		if(!b){
			return "login";
		}
		Worker worker=null;
		worker = loginService.login(name, pwd);
		if(worker==null){
			request.setAttribute("error", "�û������������");
			return "login";
		}
		else{
			request.getSession().setAttribute(Consts.LOGIN_WORKER, worker);
			return "success";
		}
	}
	@RequestMapping("/login-action2.form")
	public String loginAction2(@ModelAttribute("name")String name,String pwd,ModelMap model,HttpServletRequest request){
		name=name.trim();
		pwd=pwd.trim();
		Boolean b=true;
		if(name.equals("")){
			b=false;
			model.addAttribute("error3", "�û�������Ϊ��");
		}
		if(pwd.equals("")){
			b=false;
			model.addAttribute("error4", "���벻��Ϊ��");
		}
		if(!b){
			return "login";
		}
		Worker worker=null;
		worker = loginService.login(name, pwd);
		if(worker==null){
			model.addAttribute("error5", "�û������������");
			return "login";
		}
		else{
			request.getSession().setAttribute(Consts.LOGIN_WORKER, worker);
			return "success";
		}
	}
	@RequestMapping("/login-action3.form")
	public ModelAndView loginAction3(@ModelAttribute("name")String name,String pwd,HttpServletRequest request){
		name=name.trim();
		pwd=pwd.trim();
		Map<String,String> map=new HashMap();
		Boolean b=true;
		if(name.equals("")){
			b=false;
			map.put("error6", "�û�������Ϊ��");
		}
		if(pwd.equals("")){
			b=false;
			map.put("error7", "���벻��Ϊ��");
		}
		if(!b){
			return new ModelAndView("login",map);
		}
		Worker worker=null;
		worker = loginService.login(name, pwd);
		if(worker==null){
			map.put("error8", "�û������������");
			return new ModelAndView("login",map);
		}
		else{
			request.getSession().setAttribute(Consts.LOGIN_WORKER, worker);
			return new ModelAndView("success");
		}
	}
	//ȫ�ֵ�Attribute
	@ModelAttribute("message")
	public String getMessge(){
		return "֪ͨ�������������أ�";
	}
	public LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
}

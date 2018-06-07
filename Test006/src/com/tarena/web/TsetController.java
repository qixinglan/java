package com.tarena.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.tarena.util.*;
@Controller

public class TsetController {
	@Resource
	Conn con;
	@RequestMapping("/test.form")
	public String f1(Model m,ModelMap m1){
		m.addAttribute("inf","inf");
		m1.put("inf1","inf1");
		m1.addAttribute("inf2", "inf2");
		return "test";
	}
	@RequestMapping("/test1.form")
	public  ModelAndView f2(Model m,ModelMap m1){
		m1.put("inf1","inf1");
		return new ModelAndView("test",m1);
	}
	@ModelAttribute("message")
	public String getMessage(){
		return "È«¾Ömessage";
	}
	
}

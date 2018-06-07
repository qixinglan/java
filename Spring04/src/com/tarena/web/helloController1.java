package com.tarena.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/*
 * 返回为空的类型,返回请求路径（去掉.form)。jsp文件
 */
@Controller
public class helloController1 {
		@RequestMapping("/hello1.form")//子路径
		public void execte(HttpServletRequest req){
			System.out.println("执行HelloKitty");
			req.setAttribute("message", "Hello Kitty1111!");
			//返回/Spring04/web-inf/jsp/hello1.jsp
		}
}

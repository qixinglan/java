package com.tarena.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/*
 * ����Ϊ�յ�����,��������·����ȥ��.form)��jsp�ļ�
 */
@Controller
public class helloController1 {
		@RequestMapping("/hello1.form")//��·��
		public void execte(HttpServletRequest req){
			System.out.println("ִ��HelloKitty");
			req.setAttribute("message", "Hello Kitty1111!");
			//����/Spring04/web-inf/jsp/hello1.jsp
		}
}

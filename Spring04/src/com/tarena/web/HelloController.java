package com.tarena.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
//@Resource
//@RequestMapping
public class HelloController implements Controller{
	//��������ķ���
//	@RequestMapping("/a.form")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("hello");
		//һ�����Mҵ�����userService
		request.setAttribute("message","Hello World!");
		//ת����hello.jsp
		return new ModelAndView("hello");
		//����ֵ����   ��ͼ������
	}

}

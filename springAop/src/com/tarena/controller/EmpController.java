package com.tarena.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tarena.dao.EmpMapper;
@Controller
@RequestMapping("/emp")
public class EmpController{
	@Resource
	private EmpMapper empMapper;
	@RequestMapping("/find.form")
	public String find(){
		// 模拟查询员工数据
		System.out.println("查询员工数据，发送至列表页面.");
		// 制造一个异常，便于测试异常通知
		//Integer.valueOf("abc");
		return "index";
	}
}
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
		// ģ���ѯԱ������
		System.out.println("��ѯԱ�����ݣ��������б�ҳ��.");
		// ����һ���쳣�����ڲ����쳣֪ͨ
		//Integer.valueOf("abc");
		return "index";
	}
}
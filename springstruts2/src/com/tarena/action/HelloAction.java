package com.tarena.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
//Ĭ���ǵ����ģ���Ϊ���ص��ǳ�Ա����������Ҫ��ɱ��ԭ�͵ģ�ÿ�ζ���newһ���¶���
@Scope("prototype")
public class HelloAction {
	public String execute(){
		System.out.println("hello action");
		return "success";
	}
}

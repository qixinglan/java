package com.tarena.dao;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Scope("prototype")
@Component("bean2")
public class DemoBean2 {
	public DemoBean2(){
		System.out.println("ע���Bean�����췽��");
	}
	@PostConstruct
	public void cc(){
		System.out.println("��ʼ������");
	}
}

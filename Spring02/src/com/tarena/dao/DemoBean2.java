package com.tarena.dao;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Scope("prototype")
@Component("bean2")
public class DemoBean2 {
	public DemoBean2(){
		System.out.println("注解的Bean，构造方法");
	}
	@PostConstruct
	public void cc(){
		System.out.println("初始化方法");
	}
}

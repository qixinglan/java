package com.tarena.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
//默认是单例的，因为返回的是成员变量。所以要变成变成原型的，每次都是new一个新对象
@Scope("prototype")
public class HelloAction {
	public String execute(){
		System.out.println("hello action");
		return "success";
	}
}

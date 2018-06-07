package com.tarena.text;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;

import com.tarena.web.HelloController;

public class TextCase {
	@Test
	public void testHandlerMapping(){
		String conf="spring-mvc.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		HandlerMapping bean=ac.getBean("handlerMapping",HandlerMapping.class);
		System.out.println(bean);
	}
	@Test
	public void testView(){
		String conf="spring-mvc.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		HelloController hc=ac.getBean("helloController",HelloController.class);
		System.out.println(hc);
		ViewResolver vr=ac.getBean("view",ViewResolver.class);
		System.out.println(vr);
	}
}

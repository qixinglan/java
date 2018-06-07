package com.tarena.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class OperateLogger{
	@Before("within(com.tarena.controller..*)")
//	@Before("execution(* find(..))")
	public void log1(){
		System.out.println("-->@Before");
	}
	@Around("within(com.tarena.controller..*)")
	public Object log2(ProceedingJoinPoint p) throws Throwable {
		// 目标组件的类名
		String className = p.getTarget().getClass().getName();
		// 调用的方法名
		String method = p.getSignature().getName();
		// 当前系统时间
		String date = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss").format(new Date());
		// 拼日志信息
		String msg = "-->用户在" + date + "，执行了" 
				+ className + "." + method + "()";
		// 记录日志
		System.out.println("-->@Around before");
		System.out.println(msg);
		
		// 执行目标组件的方法
		Object obj = p.proceed();
//		Object obj = null;
		
		// 在调用目标组件业务方法后也可以做一些业务处理
		System.out.println("-->@Around after");
		
		return obj;
	}
	
	/**
	 * 异常通知使用的方法
	 */
	@AfterThrowing(pointcut="within(com.tarena.controller..*)",throwing="e")
	public void log3(Exception e) {
		StackTraceElement[] elems = e.getStackTrace();
		// 将异常信息记录
		System.out.println("-->@AfterThrowing");
		System.out.println("-->" + elems[0].toString());
	}
	
	@After("within(com.tarena.controller..*)")
	public void log4() {
		// 记录日志
		System.out.println("-->@After");
	}

	@AfterReturning("within(com.tarena.controller..*)")
	public void log5() {
		// 记录日志
		System.out.println("-->@AfterReturning");
	}
}
package com.tarena.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.tarena.dao.Admin;

@Component
@Aspect
public class Aop {
	@Resource
	HttpServletRequest request;
	
	// 前置通知
//	@Before("within(com.tarena.controller..*)")
//	public void log1() {
//		System.out.println("log1做记录");
//	}
//
//	// 后置通知
//	@AfterReturning("within(com.tarena.controller..*)")
//	public void log2() {
//		System.out.println("log2做记录");
//	}
//
//	// 环绕通知3
//	// 环绕通知可以代替其他任何通知，用try{p.proceed()}catch{}finally{}
//	@Around("within(com.tarena.controller..*)")
//	public Object log3(ProceedingJoinPoint p) throws Throwable {
//		// 目标组件的类名
//		String className = p.getTarget().getClass().getName();
//		// 调用的方法名
//		String method = p.getSignature().getName();
//		// 当前系统时间
//		String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
//				.format(new Date());
//		System.out.println("log3做记录");
//		Object object = null;
//		// object=p.proceed();
//		// 执行目标组件切入点方法
//		object = p.proceed();
//		// 目标组件切入点方法执不执行，环绕通知都将执行。其实环绕通知就是一个方法，某些代码写在p.proceed()
//		// 前面就在目标组件切入点前面执行。。。。。。。。
//		// 目标组件切入点方法执不执行，前后置通知也会执行
//		System.out.println("log3做记录");
//		return object;
//	}
//
//	// 异常通知
//	@AfterThrowing(pointcut = "within(com.tarena.controller..*)", throwing = "e")
//	public void log4(Exception e) {
//		System.out.println("log4做记录" + e.getMessage());
//	}
	@Around("within(com.tarena.controller..*)")
	public Object log3(ProceedingJoinPoint p) throws Throwable {
		Object object = null;
		try{
			object = p.proceed();
		}catch(Exception e){
			// 目标组件的类名
			String className = p.getTarget().getClass().getName();
			// 调用的方法名
			String method = p.getSignature().getName();
			// 当前系统时间
			String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
					.format(new Date());
			Admin admin=(Admin)request.getSession().getAttribute("admin");
			String admin_code=admin.getAdmin_code();
			String ip=request.getRemoteHost();
			StringBuffer sb=new StringBuffer();
		
			Logger logger=Logger.getLogger("Aop.class");
			if(admin!=null){
				logger.error("\n"+sb.append(admin_code).append(ip).append(className)
						.append(method).append(date).toString());
			}
			StackTraceElement []errors=e.getStackTrace();
			for(StackTraceElement error:errors){
				logger.error("\n"+error.toString());
			}
			throw e;
		}
		return object;
		
	}
}

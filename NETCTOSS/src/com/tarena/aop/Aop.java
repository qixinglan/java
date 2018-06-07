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
	
	// ǰ��֪ͨ
//	@Before("within(com.tarena.controller..*)")
//	public void log1() {
//		System.out.println("log1����¼");
//	}
//
//	// ����֪ͨ
//	@AfterReturning("within(com.tarena.controller..*)")
//	public void log2() {
//		System.out.println("log2����¼");
//	}
//
//	// ����֪ͨ3
//	// ����֪ͨ���Դ��������κ�֪ͨ����try{p.proceed()}catch{}finally{}
//	@Around("within(com.tarena.controller..*)")
//	public Object log3(ProceedingJoinPoint p) throws Throwable {
//		// Ŀ�����������
//		String className = p.getTarget().getClass().getName();
//		// ���õķ�����
//		String method = p.getSignature().getName();
//		// ��ǰϵͳʱ��
//		String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
//				.format(new Date());
//		System.out.println("log3����¼");
//		Object object = null;
//		// object=p.proceed();
//		// ִ��Ŀ���������㷽��
//		object = p.proceed();
//		// Ŀ���������㷽��ִ��ִ�У�����֪ͨ����ִ�С���ʵ����֪ͨ����һ��������ĳЩ����д��p.proceed()
//		// ǰ�����Ŀ����������ǰ��ִ�С���������������
//		// Ŀ���������㷽��ִ��ִ�У�ǰ����֪ͨҲ��ִ��
//		System.out.println("log3����¼");
//		return object;
//	}
//
//	// �쳣֪ͨ
//	@AfterThrowing(pointcut = "within(com.tarena.controller..*)", throwing = "e")
//	public void log4(Exception e) {
//		System.out.println("log4����¼" + e.getMessage());
//	}
	@Around("within(com.tarena.controller..*)")
	public Object log3(ProceedingJoinPoint p) throws Throwable {
		Object object = null;
		try{
			object = p.proceed();
		}catch(Exception e){
			// Ŀ�����������
			String className = p.getTarget().getClass().getName();
			// ���õķ�����
			String method = p.getSignature().getName();
			// ��ǰϵͳʱ��
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

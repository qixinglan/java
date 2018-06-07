package com.tarena.aspect;

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

/**
 *	���ڼ�¼��־�ķ����������ʾSpring AOP�ĸ���֪ͨ���͡�
 */
@Component
@Aspect
public class OperateLogger {

	/**
	 * ǰ��֪ͨ������֪ͨ������֪ͨʹ�õķ���
	 */
//	@Before("within(com.tarena.controller..*)")
	@Before("execution(* find(..))")
	public void log1() {
		// ��¼��־
		System.out.println("-->@Before");
	}

	/**
	 * ����֪ͨʹ�õķ���
//����֪ͨ���Դ��������κ�֪ͨ����try{p.proceed()}catch{}finally{}
//ִ��Ŀ���������㷽��
//		object=p.proceed();
		//Ŀ���������㷽��ִ��ִ�У�����֪ͨ����ִ�С���ʵ����֪ͨ����һ��������ĳЩ����д��p.proceed()
		//ǰ�����Ŀ����������ǰ��ִ�С���������������
		//�ڻ���֪ͨ�в�ִ��p.proceed()��Ŀ���������㷽����ִ�У���ǰ����֪ͨҲ��ִ��
	 */
	@Around("within(com.tarena.controller..*)")
	public Object log2(ProceedingJoinPoint p) throws Throwable {
		// Ŀ�����������
		String className = p.getTarget().getClass().getName();
		// ���õķ�����
		String method = p.getSignature().getName();
		// ��ǰϵͳʱ��
		String date = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss").format(new Date());
		// ƴ��־��Ϣ
		String msg = "-->�û���" + date + "��ִ����" 
				+ className + "." + method + "()";
		// ��¼��־
		System.out.println("-->@Around before");
		System.out.println(msg);
		
		// ִ��Ŀ������ķ���
		Object obj = p.proceed();
//		Object obj = null;
		
		// �ڵ���Ŀ�����ҵ�񷽷���Ҳ������һЩҵ����
		System.out.println("-->@Around after");
		
		return obj;
	}
	
	/**
	 * �쳣֪ͨʹ�õķ���
	 */
	@AfterThrowing(pointcut="within(com.tarena.controller..*)",throwing="e")
	public void log3(Exception e) {
		StackTraceElement[] elems = e.getStackTrace();
		// ���쳣��Ϣ��¼
		System.out.println("-->@AfterThrowing");
		System.out.println("-->" + elems[0].toString());
	}
	
	@After("within(com.tarena.controller..*)")
	public void log4() {
		// ��¼��־
		System.out.println("-->@After");
	}

	@AfterReturning("within(com.tarena.controller..*)")
	public void log5() {
		// ��¼��־
		System.out.println("-->@AfterReturning");
	}
	
}

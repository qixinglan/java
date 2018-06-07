package com.nci.dcs.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * 
 * @author xuhua 
 *   实现编程方式访问spring bean
 */
public class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext ctx;

	@SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.ctx = ctx;
	}

	public static Object getBean(String beanName) {
		return ctx.getBean(beanName);
	}
}

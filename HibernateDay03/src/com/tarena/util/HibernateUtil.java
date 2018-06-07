package com.tarena.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory af;
	static{
		//创建Configuration
		Configuration conf=new Configuration();
		//加载配置文件 
		conf.configure("hibernate.cfg.xml");
		//创建SessionFactory
		af=conf.buildSessionFactory();
	}
	//获得Session
	public static Session getSession() {
		return af.openSession();
	}
	public static SessionFactory getAf() {
		return af;
	}
}

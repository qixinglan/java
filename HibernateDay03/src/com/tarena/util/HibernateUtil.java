package com.tarena.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory af;
	static{
		//����Configuration
		Configuration conf=new Configuration();
		//���������ļ� 
		conf.configure("hibernate.cfg.xml");
		//����SessionFactory
		af=conf.buildSessionFactory();
	}
	//���Session
	public static Session getSession() {
		return af.openSession();
	}
	public static SessionFactory getAf() {
		return af;
	}
}

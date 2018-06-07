package com.tarena.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.tarena.entity.Emp;

public class HibernateUtil {
	private static SessionFactory af;
	static{
		Configuration conf=new Configuration();
		conf.configure("hibernate.cfg.xml");
		af=conf.buildSessionFactory();
	}
	@Test
	public static Session getSession(){
		return af.openSession();
	}
}

package com.tarena.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.dao.VehicleDao;
import com.tarena.entity.Vehicle;

public class TestVehicle {

	private String conf = "applicationContext.xml";

	@Test
	public void test1() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		VehicleDao dao = ctx.getBean(VehicleDao.class);
		List<Vehicle> list = dao.findAll();
		for (Vehicle v : list) {
			System.out.println(v);
		}
	}
}

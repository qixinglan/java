package com.tarena.dao;

import java.sql.Timestamp;
import java.util.Date;

import com.tarena.entity.Admin;

public class AdminDaoImp implements AdminDao{
	public Admin findByAdmin_code(String admin_code){
		if(admin_code.equals("caocao")){
			Admin admin=new Admin();
			admin.setAdmin_code("caocao");
			admin.setAdmin_id(100);
			admin.setEmail("3333111111@qq.com");
			admin.setEnrolldate(new Timestamp(new Date().getTime()));
			admin.setName("²Ü²Ù");
			admin.setPassword("123456");
			admin.setTelephone("18231088827");
			return admin;
		}else{
			return null;
		}
	}
}

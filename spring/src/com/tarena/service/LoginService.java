package com.tarena.service;

import javax.annotation.Resource;

import com.tarena.dao.OracleWorkerDao;
import com.tarena.entity.Worker;

public class LoginService {
	@Resource
	private OracleWorkerDao oracleWorkerDao;
	public Worker login(String name,String pwd) {
	Worker worker=oracleWorkerDao.findByName(name);
	if(worker!=null){
		if(pwd.equals(worker.getPwd())){
			return worker;
		}
		else{
			return null;
		}
	}
	return null;
	}
}

package com.tarena.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tarena.dao.OracleWorkerDao;
import com.tarena.entity.Worker;
@Service
public class LoginService {
	@Resource
	OracleWorkerDao oracleWorkerDao;

	public OracleWorkerDao getOracleWorkerDao() {
		return oracleWorkerDao;
	}
	public void setOracleWorkerDao(OracleWorkerDao oracleWorkerDao) {
		this.oracleWorkerDao = oracleWorkerDao;
	}
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

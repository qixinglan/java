package com.tarena.service;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tarena.dao.*;
import com.tarena.entity.Worker;
@Service
public class WorkerService implements Serializable {
	@Resource(name="oracleWorkerDao")
	private OracleWorkerDao oracleWorkerDao;

	
	public OracleWorkerDao getOracleWorkerDao() {
		return oracleWorkerDao;
	}


	public void setOracleWorkerDao(OracleWorkerDao oracleWorkerDao) {
		this.oracleWorkerDao = oracleWorkerDao;
	}


	/**
	 * µÇÂ½¹¦ÄÜ
	 */
	public Worker login(String name,String pwd){
		Worker worker=oracleWorkerDao.findByName(name);
		if(worker==null){
			return null;
		}
		if(worker.getPwd().equals(pwd)){
			return worker;
		}
		return null;
	}
}

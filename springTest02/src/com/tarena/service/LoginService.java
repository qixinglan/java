package com.tarena.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tarena.dao.WorkerMapper;
import com.tarena.entity.Worker;

@Service
public class LoginService {
	@Resource
	WorkerMapper workerMapper;
	public Worker login(String name,String pwd){
		Worker worker=null;
		worker=workerMapper.findByName(name);
		if(worker.getPwd().equals(pwd)){
			return worker;
		}
		else{
			return null;
		}
	}
}

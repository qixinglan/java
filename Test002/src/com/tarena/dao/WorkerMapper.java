package com.tarena.dao;

import java.util.List;
import java.util.Map;

import com.tarena.entity.Worker;

public interface WorkerMapper {
	List<Worker> findAll();
	void addWorker(Worker worker);
	void deleteWorker(Worker worker);
	void updateWorker(Worker worker);
	Worker findByName(String name);
	List<Worker> findByPwd(String pwd);
	List<Map<String,Object>> findAllName();
	
}

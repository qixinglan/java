package com.tarena.dao;

import java.util.List;
import java.util.Map;

import com.tarena.entity.Worker;

@MapperBean
public interface WorkerMapper {
	void addWorker(Worker worker);
	void deleteWorker(Worker worker);
	void updateWorker(Worker Worker);
	Worker findByName(String name);
	Worker findById(int id);
	List<Worker> findAll();
	List<Worker> findByPwd(String pwd);
	List<Map<String,Object>>  findAllName();
}

package com.tarena.dao;

import java.util.List;

import com.tarena.entity.Worker;

interface WorkerDao {
	Worker findByName(String name);
	Integer add(Worker worker);
	void delete(Worker worker);
	void update(Worker worker);
	List<Worker> findAll();
	Worker findById(Integer id);
	
}

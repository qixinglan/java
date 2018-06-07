package com.tarena.dao;

import java.util.List;

import com.tarena.entity.Worker;

public interface WorkerDao {
	Worker findByName(String name);
	Integer addWorker(Worker worker);
	void deleteWorker(String name);
	void updateWorker(String name,Worker worker);
	List<Worker>  findAll();
}

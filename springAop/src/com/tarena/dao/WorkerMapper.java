package com.tarena.dao;

import com.tarena.entity.MyBatisRepository;
import com.tarena.entity.Worker;

@MyBatisRepository
public interface WorkerMapper {
	void insert(Worker worker);
}

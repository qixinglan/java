package com.tarena.dao;

import com.tarena.entity.Worker;

public interface WorkerDao {
	Worker findByName(String name);
}

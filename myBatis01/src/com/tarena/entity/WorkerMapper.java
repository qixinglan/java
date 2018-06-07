package com.tarena.entity;

import java.util.List;
import java.util.Map;

/*
 * Mapper接口类似于Dao，但是语法有限定
 * 不能有重载
 * Mapper接口用来定义实体对象的CUUD(数据库操作)操作
 * 接口会自动实现
 */
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

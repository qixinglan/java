package com.tarena.dao;

import java.util.List;
import java.util.Map;

import com.tarena.entity.Worker;

public interface WorkerDao {
	void add(Worker worker);
	Worker add(String name,String pwd,String phone);
	Worker findByName(String name);
	void update(Worker worker);
	Worker delete(String name);
	List<Worker> findByPwd(String pwd);
	List<Worker> findAll();
	List<MainInfo> findNameAndPhone();
	List<Map<String,Object>> findPhoneAndName();
	List<Worker> findByNameAndPhone(String name,String phone);
	void add(String name,String pwd);
}

package com.tarena.dao;

import java.util.List;
import java.util.Map;

import com.tarena.entity.Worker;

public interface WorkerDao{
	public  void add(Worker worker);
	Worker add(String name,String pwd,String phone);
	Worker delete(String name);
	void update(Worker Worker);
	Worker fingByName(String name);
	List<Worker> findAll();
	List<Worker> findByPwd(String pwd);
	List<Map<String,Object>> findPhoneAndName();
	List<MainInf> findNameAndPhone();
}

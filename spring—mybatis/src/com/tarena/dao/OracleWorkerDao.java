package com.tarena.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.tarena.entity.Worker;
@Repository
public class OracleWorkerDao implements WorkerDao,Serializable{
	@Resource
	private SqlSessionTemplate sst;
	public Worker findByName(String name) {
		// TODO Auto-generated method stub
		Worker worker=sst.selectOne("findByName");
		return worker;
	}
	public Integer addWorker(Worker worker) {
		sst.insert("addWorker",worker);
		// TODO Auto-generated method stub
		return worker.getId();
	}
	public void deleteWorker(String name) {
		// TODO Auto-generated method stub
		sst.delete("deleteWorker",name);
	}
	public void updateWorker(String name, Worker worker) {
		// TODO Auto-generated method stub
		sst.update("updateWorker", worker);
	}
	public List<Worker> findAll() {
		// TODO Auto-generated method stub
		List<Worker> list=sst.selectList("findAll");
		return list;
	}

}

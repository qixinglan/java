package com.tarena.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.tarena.entity.Worker;
@Repository
public class OracleWorkerDao implements WorkerDao{
@Resource
SqlSessionTemplate sst;
	public Worker findByName(String name) {
		// TODO Auto-generated method stub
		Worker worker=sst.selectOne("findByName");
		return worker;
	}

}

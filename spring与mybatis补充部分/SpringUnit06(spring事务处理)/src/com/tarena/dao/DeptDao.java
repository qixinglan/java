package com.tarena.dao;

import com.tarena.annotation.MyBatisRepository;
import com.tarena.entity.Dept;

@MyBatisRepository
public interface DeptDao {

	Dept findById(int id);
	
	Dept findById2(int id);

}

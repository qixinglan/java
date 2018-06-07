package com.tarena.dao;

import com.tarena.entity.Dept;
import com.tarena.entity.MyBatisRepository;

@MyBatisRepository
public interface DeptMapper {
	Dept findById(int id);
	
	Dept findById2(int id);
}

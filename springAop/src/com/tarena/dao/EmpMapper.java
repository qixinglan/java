package com.tarena.dao;

import org.springframework.stereotype.Repository;

import com.tarena.entity.Emp;
import com.tarena.entity.MyBatisRepository;
@Repository
@MyBatisRepository
public interface EmpMapper {
	void save(Emp emp);

	Emp findById(int id);
	
	Emp findById2(int id);
}

package com.tarena.dao;

import com.tarena.annotation.MyBatisRepository;
import com.tarena.entity.Emp;

/**
 * Ա�����DAO���
 */
@MyBatisRepository
public interface EmpDao {

	void save(Emp emp);

	Emp findById(int id);
	
	Emp findById2(int id);

}

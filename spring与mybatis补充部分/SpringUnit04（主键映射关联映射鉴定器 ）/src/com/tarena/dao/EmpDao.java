package com.tarena.dao;

import com.tarena.annotation.MyBatisRepository;
import com.tarena.entity.Emp;

/**
 * 员工表的DAO组件
 */
@MyBatisRepository
public interface EmpDao {

	void save(Emp emp);

	Emp findById(int id);
	
	Emp findById2(int id);

}

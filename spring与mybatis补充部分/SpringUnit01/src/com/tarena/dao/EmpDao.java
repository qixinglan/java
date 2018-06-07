package com.tarena.dao;

import java.util.List;
import com.tarena.annotation.MyBatisRepository;
import com.tarena.entity.Emp;

/**
 * 员工表的DAO组件
 */
@MyBatisRepository
public interface EmpDao {

	List<Emp> findAll();

	void save(Emp emp);

	Emp findById(int id);

	void update(Emp emp);

	void delete(int id);

}

package com.tarena.dao;

import java.util.List;

import com.tarena.annotation.MyBatisRepository;
import com.tarena.entity.Condition;
import com.tarena.entity.Emp;

/**
 * 员工表的DAO组件
 */
@MyBatisRepository
public interface EmpDao {

	List<Emp> findAll();

	List<Emp> findByDept(Condition cond);

	List<Emp> findBySalary(Condition cond);

	List<Emp> findByDeptAndSalary(Condition cond);

	void update(Emp emp);

	List<Emp> findByDeptAndSalary2(Condition cond);

	void update2(Emp emp);

	List<Emp> findByIds(Condition cond);
	
}

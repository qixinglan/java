package idao;

import java.util.List;

import entity.Dept;


public interface IDept {
	List<Dept>findAll() throws Exception;
	Dept findByDeptno(int id) throws Exception;
	Dept add(Dept newEmp) throws Exception;
	Dept modify(Dept oldEmp) throws Exception;
	void delete(int deptno) throws Exception;
}

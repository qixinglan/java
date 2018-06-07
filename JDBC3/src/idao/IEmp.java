package idao;

import java.util.List;

import entity.Emp;

public interface IEmp {
	List<Emp>findAll() throws Exception;
	Emp findById(int id) throws Exception;
	int add(Emp newEmp) throws Exception;
	int modify(Emp oldEmp) throws Exception;
	int delete(int id) throws Exception;
}

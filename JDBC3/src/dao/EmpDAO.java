package dao;

import idao.IEmp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dbutils.DBUtils;

import entity.Emp;

public class EmpDAO extends BaseDAO<Emp> implements IEmp{
	private static final String FINDALL=
			"select id,name,salary,deptno from emp";
	private static final String FIND_BY_ID=
			FINDALL+" where id=?";
	private static final String ADD=
			"insert into emp(id,name,salary,deptno) values (?,?,?,?)";
					
	@Override
	
	public List<Emp> findAll() throws Exception {
		// TODO Auto-generated method stub
		return find(FINDALL, null);
	}

	@Override
	public Emp findById(int id) throws Exception {
		// TODO Auto-generated method stub
		List<Emp> list=find(FIND_BY_ID, new Object[]{id});
		return list.get(0);
	}

	@Override
	public int add(Emp newEmp) throws Exception {
		// TODO Auto-generated method stub
		return update(ADD, new Object[]{newEmp.getId(),newEmp.getName(),newEmp.getSalary(),newEmp.getDeptno()});
	}

	@Override
	public int modify(Emp oldEmp) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Emp toEntity(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		Emp e=new Emp(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4));
		return e;
	}
	
}

package dao;

import itt.Emp;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;

import org.junit.Test;

public class EmpDAO extends BaseDAO{
	private static String findAll="select empno,ename,job,sal,hiredate from emp";
	private static String findByJob="select empno,ename,job,sal,hiredate from emp where job=?";
	public List<Emp> findAll() throws Exception{
		return query(findAll, null);
	}
	@Override
	public Object toEntity(ResultSet rs) throws Exception {
		Emp emp=new Emp();
		emp.setNo(rs.getInt("empno"));
		emp.setHireDate(rs.getDate("hiredate"));
		emp.setJob(rs.getString("job"));
		emp.setName(rs.getString("ename"));
		emp.setSal(rs.getBigDecimal("sal"));
		return emp;
	}
	public List<Emp> findByJob() throws Exception{
		return query(findByJob, new Object[]{"CLERK"});
	}
	
	
}

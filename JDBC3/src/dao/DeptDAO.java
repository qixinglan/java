package dao;

import java.sql.ResultSet;
import java.util.List;

import entity.Dept;
import idao.IDept;

public class DeptDAO extends BaseDAO<Dept>implements IDept{
	private static final String FINDALL=
			"select deptno,dname,loc from dept";
	private static final String FIND_BY_ID=
			FINDALL+" where id=?";
	@Override
	public List<Dept> findAll() throws Exception {
		// TODO Auto-generated method stub
		return find(FINDALL, null);
	}
	@Override
	public Dept findByDeptno(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Dept add(Dept newEmp) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Dept modify(Dept oldEmp) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(int deptno) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Dept toEntity(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		Dept dept=new Dept(rs.getInt(1),rs.getString(2),rs.getString(3));
		return dept;
	}
}

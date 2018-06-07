package test03;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmpDao extends BaseDao{
	String selectByid="select * from emp where id=?";
	
	List<Emp> selectByid() throws SQLException{
		Object[] o={1};
		List<Emp> list=select(selectByid, o);
		return list;
	}
	@Override
	List toEntity(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List list=new ArrayList();
		while(rs.next()){
			Emp emp=new Emp();
			emp.setAge(rs.getInt("age"));
			emp.setName(rs.getString("name"));
			emp.setSalary(rs.getInt("salary"));
			list.add(emp);
		}
		return list;
	}
	public static void main(String[] args) throws SQLException {
		EmpDao empDao=new EmpDao();
		List<Emp> list=empDao.selectByid();
		System.out.println(list.get(0).getName());
	}
}

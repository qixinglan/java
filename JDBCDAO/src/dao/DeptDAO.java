package dao;

import itt.Dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeptDAO extends BaseDAO{
	private static final String findAll="Select deptno,dname,loc from dept";
	private static final String findByLocation="Select deptno,dname,loc from dept where loc=?";
	private static final String add="insert into dept(deptno,dname,loc) values(?,?,?)";
	private static final String update="update dept set dname=?,loc=? where deptno=?";
	private static final String findById="select deptno,dname,loc from dept where deptno=?";
//	/**
//	 * �ӽ������ȡ�����ݷ�װ��ʵ�����----��ʦд��һ����װ����
//	 */
//	private Dept toDept(ResultSet rs) throws SQLException{
//		Dept dept=new Dept();
//		dept.setLocation(rs.getString("loc"));
//		dept.setName(rs.getString("danme"));
//		dept.setNo(rs.getInt("deptno"));
//		return dept;
//	}
//	//�Լ�д��һ����װ����
//	private List<Dept> hh(PreparedStatement stmt) throws Exception{
//		ResultSet rs=stmt.executeQuery();
//		List<Dept>list=new ArrayList<Dept>();
//		while(rs.next()){
//			Dept dept=new Dept();
//			dept.setLocation(rs.getString("loc"));
//			dept.setName(rs.getString("dname"));
//			dept.setNo(rs.getInt("deptno"));
//			list.add(dept);
//		}
//		return list;
//	}
	/**
	 * �������еĲ�����Ϣ
	 * @throws Exception 
	 */
	public List<Dept>findAll() throws Exception{
		return query(findAll, null);
	}
	/**
	 * ����ָ�����ŵ�������Ϣ
	 * @throws Exception 
	 */
	public List<Dept> findByLocation(String location) throws Exception{
		return query(findByLocation, new Object[]{location});
	}
	/**
	 * ����һ������
	 */
	public void add(Dept dept) throws Exception{
		
		update(add,new Object[]{dept.getNo(),dept.getName(),dept.getLocation()});
	}
	/**
	 * ����Dept�������ҵ�����
	 * ����һ������
	 * @throws Exception 
	 */
	public void update(Dept dept) throws Exception{
		update(update,new Object[]{dept.getName(),dept.getLocation(),dept.getNo()});
	}
	/**
	 * ���������ҵ�һ������
	 * @throws Exception 
	 */
	public Dept findById(int deptno) throws Exception{
		List<Dept> list=query(findById, new Object[]{deptno});
		if(list.size()!=0){
			return list.get(0);
		}else{
			return null;
		}
	}
	@Override
	public Object toEntity(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		Dept dept=new Dept();
		dept.setLocation(rs.getString("loc"));
		dept.setName(rs.getString("dname"));
		dept.setNo(rs.getInt("deptno"));
		return dept;
	}
}

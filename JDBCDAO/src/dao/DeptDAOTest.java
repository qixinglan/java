package dao;

import itt.Dept;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class DeptDAOTest {
	@Test
	public void testFindAll() throws Exception{
		DeptDAO daptdao=new DeptDAO();
		List<Dept> list=new ArrayList<Dept>();
		list=daptdao.findAll();
		for(Dept dept :list){
			System.out.println(dept.getNo()+"\t"+dept.getName()+"\t"+dept.getLocation());
		}
		
	}
	@Test
	public void testfindByLocation() throws Exception{
		DeptDAO daptdao=new DeptDAO();
		List<Dept> list=new ArrayList<Dept>();
		list=daptdao.findByLocation("NEW YORK");
		for(Dept dept :list){
			System.out.println(dept.getNo()+"\t"+dept.getName()+"\t"+dept.getLocation());
		}
	}
	@Test
	public void testadd() throws Exception{
		DeptDAO daptdao=new DeptDAO();
		Dept dept=new Dept();
		dept.setLocation("北京");
		dept.setName("谷亮");
		dept.setNo(70);
		daptdao.add(dept);
	}
	@Test
	public void TestfindById() throws Exception{
		DeptDAO deptdao=new DeptDAO();
		Dept dept=deptdao.findById(10);
		System.out.println(dept.getNo()+"\t"+dept.getName()+"\t"+dept.getLocation());
	}
	@Test
	public void testUpdate() throws Exception{
		DeptDAO deptdao=new DeptDAO();
		Dept dept=new Dept();
		dept.setNo(30);
		dept.setName("啊哈哈");
		dept.setLocation("上海");
		deptdao.update(dept);
	}
}

package dao;

import itt.Emp;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class EmpDAOTest {
	@Test
	public void testEmpDAO() throws Exception{
		EmpDAO empdao=new EmpDAO();
		List<Emp> list=new ArrayList();
		list=empdao.findAll();
		for(Emp e:list){
			System.out.println(e.getNo()+"\t"+e.getName()+"\t"+e.getJob());
		}
	}
	@Test
	public void testfindByJob() throws Exception{
		EmpDAO empdao=new EmpDAO();
		List<Emp> list=new ArrayList();
		list=empdao.findByJob();
		for(Emp e:list){
			System.out.println(e.getNo()+"\t"+e.getName()+"\t"+e.getJob());
		}
	}
	
}

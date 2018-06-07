package bll;

import idao.IDept;
import idao.IEmp;

import java.util.List;

import dao.DeptDAO;
import dao.EmpDAO;
import entity.Dept;
import entity.Emp;

public class EmpBll {
	public static void main(String[] args) {
		IEmp e=new EmpDAO();
		IDept d=new DeptDAO();
		try {
			List<Emp> L=e.findAll();
			//三种遍历方式
			for(Emp emp :L){
				System.out.println(emp.toString());
			}
			
			
			Emp emp=e.findById(1001);
			System.out.println("\n"+emp.toString()+"\n");
			
			Emp emp2=new Emp(1015,"谷亮",5500,20);
			System.out.println(emp2.toString()+e.add(emp2)+"行已插入");
			
			List<Dept> L2=d.findAll();
			for(Dept d2: L2){
				System.out.println(d2.toString());
			}
//			for(int i=0;i<L.size();i++){
//				System.out.println(L.get(i).toString());
//			}
//			Iterator i=L.listIterator();
//			while(i.hasNext()){
//				System.out.println(i.next().toString());
//			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

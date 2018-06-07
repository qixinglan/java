package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import connect.connections;

import entity.Emp;

public class EmpDao {
		@Test
		public  List findall() throws Exception{
			List<Emp> emplist=new ArrayList<Emp>();
			
			//try {
				Connection con=connections.openConnection1();
				System.out.println(con);
				PreparedStatement pre=con.prepareStatement("Select * from emp1");
				ResultSet re=pre.executeQuery();
				while(re.next()){
					Emp e=new Emp(re.getString("name"), re.getDouble("salary"), re.getInt("age"));
					emplist.add(e);
				}
				con.close();
	//		} catch (Exception e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
			
			return emplist;
		}
		public void update(Emp emp) throws Exception{
			
			Connection con=connections.openConnection1();
			String name=emp.getName();
			PreparedStatement pre=con.prepareStatement("update emp1 set salary=?,age=? where name=?");
			pre.setDouble(1, emp.getSalary());
			pre.setInt(2, emp.getAge());
			pre.setString(3, name);
			int a=pre.executeUpdate();
			con.close();
			
		}
		public void addEmp(Emp emp) throws Exception{
			Connection con=connections.openConnection1();
			con.prepareStatement("insert into emp1 values('"+emp.getName()+"',"+emp.getSalary()+","+emp.getAge()+")").executeUpdate();
			con.close();
		}
		public void deleteEmp(String name) throws Exception{
			Connection con=connections.openConnection1();
			PreparedStatement pre=con.prepareStatement("delete from emp1 where name=?");
			pre.setString(1,name);
			int a=pre.executeUpdate();
			System.out.println(name);
			System.out.println(a+"guliang");
			con.close();
		}
}

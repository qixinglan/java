package test;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Demo05 {
	public static void main(String[] args) throws Exception {
		Connection con =Demo03.openConnection();
		try{
			con.setAutoCommit(false);
			PreparedStatement ps=con.prepareStatement("update account set amount=amount-500 where id=200");
			ps.executeUpdate();
			
//			
//			if(2==2){
//				throw new Exception();
//			}
			System.out.println("12");
			PreparedStatement ps2=con.prepareStatement("update account set amount=amount+500 where id=100");
			ps2.executeUpdate();
			con.commit();
			con.close();
			System.out.println("sas");
		}catch(Exception e){
			System.out.println("asd");
			con.rollback();
			con.close();
			e.printStackTrace();
		}
	}
}

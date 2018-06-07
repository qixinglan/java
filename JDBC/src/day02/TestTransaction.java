package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import day01.Demo05;

public class TestTransaction {
	public static void main(String[] args) throws Exception {
		Connection con=null;
		try{
			con=Demo05.openConnection();
			con.setAutoCommit(false);//�����Զ��ύΪfalse��JDBC������DML���ִ�к��commit
			PreparedStatement stmt1=con.prepareStatement("update account set amount=amount+500 where id=200");
			stmt1.executeUpdate();
			
			//con.commit();//�ֶ��ύ
			System.out.println("sxwsdw");
			if(1==1){
				throw new Exception();
			}
				
			
			PreparedStatement stmt2=con.prepareStatement("update account set amount=amount-500 where id=100");
			stmt2.executeUpdate();
			
			con.commit();//�ֶ��ύ
			System.out.println("hahah");
		}catch(Exception e){
			//con.rollback();
			e.printStackTrace();
		}finally{
			if(con!=null){
				con.close();
			}
		}
		
	}
}

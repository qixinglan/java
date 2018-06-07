package day02;

import java.awt.datatransfer.Transferable;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Trans {
	private static final String minus=
			"update account_gl set bal=bal-? where id=?";
	private static final String add=
			"update account_gl set bal=bal+? where id=?";
	public static void main(String[] args) {
		try{
			transfer("A","B",500);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void transfer(String from,String to,double amount) throws Exception{
		Connection con=DBUtils.getConnection();
		
		PreparedStatement pstmtminus=con.prepareStatement(minus);
		PreparedStatement pstmtadd=con.prepareStatement(add);
		pstmtminus.setDouble(1, amount);
		pstmtminus.setString(2, from);
		pstmtadd.setDouble(1, amount);
		pstmtadd.setString(2, to);
		
		try{
			con.setAutoCommit(false);//�ر��Զ��ύ
			pstmtminus.executeUpdate();
			if(1==1) throw new Exception();
			pstmtadd.executeUpdate();
			con.commit();//�ֶ��ύ
			System.out.println("ת�˳ɹ�");
		}catch(Exception e){
			con.rollback();//�ع�����
			System.out.println("ת��ʧ�ܣ��ѻع��������");
		}finally{
			con.setAutoCommit(true);//�����Զ��ύ
			DBUtils.closeConnection(con);
		}
		
	}
}

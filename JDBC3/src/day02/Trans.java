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
			con.setAutoCommit(false);//关闭自动提交
			pstmtminus.executeUpdate();
			if(1==1) throw new Exception();
			pstmtadd.executeUpdate();
			con.commit();//手动提交
			System.out.println("转账成功");
		}catch(Exception e){
			con.rollback();//回滚操作
			System.out.println("转账失败，已回滚，请放心");
		}finally{
			con.setAutoCommit(true);//启动自动提交
			DBUtils.closeConnection(con);
		}
		
	}
}

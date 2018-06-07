package test03;

import java.sql.Connection;
import java.sql.SQLException;

public class Four {
	public static void main(String[] args) {
		Connection con=null;
		try{
			con=Three.getConnection();
			con.setAutoCommit(false);
			//Êý¾Ý¿â²Ù×÷
			con.commit();
		}catch(Exception e){
			e.printStackTrace();
			if(con!=null){
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}finally{
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
		
	}
}

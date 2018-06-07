package day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO {
	private static final String INSERT_ORDER=
			"insert into orders(id,customer) values(order_seq.nextval,?)";
	private static final String INSERT_DETAILS=
			"insert into orderdetails values(detail_seq.nextval,?,?,?,?)";
	public static void main(String[] args) {
		Connection con=null;
		try{
			con=DBUtils.getConnection();
			con.setAutoCommit(false);
			PreparedStatement pstmt=con.prepareStatement(INSERT_ORDER,new String[]{"id","customer"});
			pstmt.setString(1, "����");
			pstmt.executeUpdate();//ִ��֮��pstmt�Ѿ�������id
			ResultSet rs=pstmt.getGeneratedKeys();//��ȡ����ID������resultset
			rs.next();
			int id=rs.getInt(1);//��ʾ����ID
			String customer=rs.getString(2);
			System.out.println(id);
			System.out.println(customer);
			PreparedStatement pstmt2=con.prepareStatement(INSERT_DETAILS);
			pstmt2.setInt(1,id);
			pstmt2.setString(2,"������");
			pstmt2.setInt(3, 4);
			pstmt2.setDouble(4, 5000);
			pstmt2.addBatch();
			pstmt2.setInt(1,id);
			pstmt2.setString(2,"����ͶӰ��");
			pstmt2.setInt(3, 4);
			pstmt2.setDouble(4, 5000);
			pstmt2.addBatch();
			pstmt2.executeBatch();
			System.out.println("�������ɳɹ�");
			
			con.commit();
		}catch(Exception  e){
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				con.setAutoCommit(true);
				DBUtils.closeConnection(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}

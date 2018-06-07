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
			pstmt.setString(1, "谷亮");
			pstmt.executeUpdate();//执行之后，pstmt已经隐含了id
			ResultSet rs=pstmt.getGeneratedKeys();//获取该行ID，返回resultset
			rs.next();
			int id=rs.getInt(1);//显示该行ID
			String customer=rs.getString(2);
			System.out.println(id);
			System.out.println(customer);
			PreparedStatement pstmt2=con.prepareStatement(INSERT_DETAILS);
			pstmt2.setInt(1,id);
			pstmt2.setString(2,"超极本");
			pstmt2.setInt(3, 4);
			pstmt2.setDouble(4, 5000);
			pstmt2.addBatch();
			pstmt2.setInt(1,id);
			pstmt2.setString(2,"超级投影仪");
			pstmt2.setInt(3, 4);
			pstmt2.setDouble(4, 5000);
			pstmt2.addBatch();
			pstmt2.executeBatch();
			System.out.println("订单生成成功");
			
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

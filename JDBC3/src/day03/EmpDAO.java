package day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmpDAO {
	private static final String FindByPages=
			"select * from " +
			"(select row_number() over(order by salary desc) ÐÐºÅ," +
			"id,name,salary,deptno from emp) " +
			"where ÐÐºÅ>? and ÐÐºÅ<=?";
	public static void main(String[] args) {
		try {
			FindByPages(4,2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void FindByPages(int r,int p) throws Exception{
		int min=r*(p-1);
		int max=r*p;
		Connection con=DBUtils.getConnection();
		PreparedStatement pstmt=con.prepareStatement(FindByPages);
		pstmt.setInt(1, min);
		pstmt.setInt(2, max);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			System.out.println(rs.getInt(1)+"\t"+rs.getInt(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4)+"\t"+rs.getInt(5));
		}
		DBUtils.closeConnection(con);
	}
}

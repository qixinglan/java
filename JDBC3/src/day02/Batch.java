package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Batch {
	private static final String INSERT=
				"insert into emp (id,name) values(?,?)";
	public static void main(String[] args) {
		Connection con=null;
		try {
			con = DBUtils.getConnection();
			con.setAutoCommit(false);
			PreparedStatement pstmt=con.prepareStatement(INSERT);
			for(int i=2000;i<120000;i++){
				pstmt.setInt(1, i);
				pstmt.setString(2,"name"+i );
				//pstmt.executeUpdate();
				pstmt.addBatch();//在本地按顺序缓存参数，添加批处理
				if(i%10000==0){
					pstmt.executeBatch();//执行批处理
					pstmt.clearBatch();//清空内存
				}
			}
			pstmt.executeBatch();
			con.commit();
			System.out.println("批处理结束");
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

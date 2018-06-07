package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TestBatch {
	public static void main(String[] args) throws Exception {
		Connection con=day01.Demo05.openConnection();
		PreparedStatement stmt=con.prepareStatement("insert into foo100 values(?,?)");
		//批量插入
		for(int i=1;i<900000;i++){
			stmt.setInt(1, i);
			stmt.setString(2, "name"+i);
			stmt.addBatch();//添加批处理
			if(i%10000==0){//防止内存溢出
				stmt.executeBatch();//执行批处理
			}
			//stmt.executeUpdate();
		}
		stmt.executeBatch();//执行批处理
		con.close();
	}
}

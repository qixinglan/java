package test;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Demo02 {
	public static void main(String[] args) throws Exception {
		Connection con=Demo03.openConnection();
		PreparedStatement ps=con.prepareStatement("insert into foo100 values (?,?)");
		for(int i=1;i<9856;i++){
			ps.setInt(1,i);
			ps.setString(2, "name"+i);
			ps.addBatch();
			if(i%1000==0){
				ps.executeBatch();
			}
		}
		ps.executeBatch();
		con.close();
	}
}

package test03;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Five {
	public static void main(String[] args) throws Exception{
		Connection con=Three.getConnection();
		PreparedStatement ps=con.prepareStatement("insert into cost values (?)");
		for(int i=0;i<1000000;i++){
			ps.setString(1,""+i);
			ps.addBatch();
			if(i%1000==0){
				ps.executeBatch();
			}
		}
		ps.executeBatch();
		
		
	}
}

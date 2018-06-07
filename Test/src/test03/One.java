package test03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class One {
	public static void main(String[] args){
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con=Two.getConnection();
//			Connection con=DriverManager.getConnection
//					("jdbc:oracle:thin:@127.0.0.1:1521:xe","jsd1403","jsd1403");
			PreparedStatement ps=con.prepareStatement("select * from cost");
			ResultSet re=ps.executeQuery();
			while(re.next()){
				System.out.println(re.getString("name"));
			}
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
}
}

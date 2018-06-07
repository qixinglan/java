package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLJDBC {

	/**
	 * @param args
	 */
	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost:3306/xe";
	private static final String USER="jsd1403";
	private static final String PWD="jsd1403";
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Class.forName(DRIVER);
		Connection con=DriverManager.getConnection(URL,USER,PWD);
		Statement stmt=con.createStatement();
		ResultSet re=stmt.executeQuery("sql");
		while(re.next()){
			System.out.println(re.getString(""));
		}
		con.close();
	}
}

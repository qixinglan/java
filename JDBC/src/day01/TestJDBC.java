package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJDBC {
	public static void main(String[] args) throws Exception{
		//装载JDBC的实现
//		Driver driver =new OracleDriver();
//		DriverManager.registerDriver(driver);
		Class.forName("oracle.jdbc.OracleDriver");
		//把这个类装载到内存,静态块就是那两句，会进行注册
		//创建网络连接
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
		//url="jdbc:mysql://ip:3306/test";
		Connection con=DriverManager.getConnection(url,"jsd1403","jsd1403");
		
		/*url用表示数据库的连接信息IP,端口号，数据库名称,不同的厂商特定的url格式或标识，
		（如果同时注册了多个数据库厂商信息）DriverManager会根据不同标识选取不同的去顶信息*/
		//用户名
		//密码;
		//该方法返回的数据库厂商对Connection接口的实现类的对象（因为DriverManage已经注册了数据库厂商的Driver信息）
		//System.out.println(con);
		//执行SQL
		//connection的createStatement()创建Statement的实现类对象
		Statement stmt=con.createStatement();
		//只能执行DQL语句,返回ResultSet，传输给数据库执行
		//获取数据库传输回的数据，并且把这个数据封装成ResultSet对象
		ResultSet rs=stmt.executeQuery("select ename, rownum,empno,sal from emp");
		//获取结果
		while(rs.next()){
			System.out.println(rs.getString("rownum")+"\t"+rs.getString("ename")+"\t"+rs.getString("empno"));//返回字符串
		}
		//关闭连接
		con.close();
	}
}

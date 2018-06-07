package day03;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

public class DBUtils {
	private static  String DRIVER;
	private static  String URL;
	private static  String USER;
	private static  String PWD;
	private static  BasicDataSource ds=null;
	private static int initialSize=0;
	static{
		try {
			Properties p=new Properties();
			//String path="src/db.properties";
			File file=new File("src/db.properties");
			//p.load(new FileInputStream(file));
			p.load(DBUtils.class.getClassLoader().getResourceAsStream("db.properties"));
			DRIVER=p.getProperty("driver");
			URL=p.getProperty("url");
			USER=p.getProperty("user");
			PWD=p.getProperty("pwd");
			initialSize=Integer.parseInt(p.getProperty("initialSize"));
			//Class.forName(DRIVER);
			ds=new BasicDataSource();
			ds.setDriverClassName(DRIVER);
			ds.setUrl(URL);
			ds.setUsername(USER);
			ds.setPassword(PWD);
			ds.setInitialSize(initialSize);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() throws Exception{ 
		if(ds!=null){
			return ds.getConnection();
			
		}
		return ds.getConnection();
	}
	public static void closeConnection(Connection con) throws Exception{
		if(con!=null){
			con.close();
		}
	}
}

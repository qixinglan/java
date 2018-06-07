package day06;

import java.io.FileInputStream;
import java.util.Properties;

import org.omg.CORBA.INTF_REPOS;

/**
 * 用于读取配置文件的类
 * @author asus
 *
 */
public class Config {
	/**
	 * 用于读取properties文件的类
	 */
	private Properties properties;
	/**
	 * 根据给定的文件名读取配置文件
	 */
	public Config(String filename){
		try {
			properties=new Properties();//实例化Properties
			//创建用于读取文本文件的字节输入流
			FileInputStream fis=new FileInputStream(filename);
			properties.load(fis);//使用properties的方法，（输入流读取配置信息）
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 根据key获取字符串类型的value
	 */
	public String getStringValue(String key){
		return properties.getProperty(key);//注意该方法返回的是字符串？？？？？？
	}
	public int getIntValue(String key){
		String value=properties.getProperty(key, "-1");//该方法的可以有两个参数，???
		//第二个参数为String型，即文件没有key则返回这个???
		return Integer.parseInt(value);
	} 
	public static void main(String[] args) {
		Config config=new Config("Server_config.properties");
		int port=config.getIntValue("port");
		System.out.println(port);
	}
	
}

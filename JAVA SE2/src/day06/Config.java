package day06;

import java.io.FileInputStream;
import java.util.Properties;

import org.omg.CORBA.INTF_REPOS;

/**
 * ���ڶ�ȡ�����ļ�����
 * @author asus
 *
 */
public class Config {
	/**
	 * ���ڶ�ȡproperties�ļ�����
	 */
	private Properties properties;
	/**
	 * ���ݸ������ļ�����ȡ�����ļ�
	 */
	public Config(String filename){
		try {
			properties=new Properties();//ʵ����Properties
			//�������ڶ�ȡ�ı��ļ����ֽ�������
			FileInputStream fis=new FileInputStream(filename);
			properties.load(fis);//ʹ��properties�ķ���������������ȡ������Ϣ��
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * ����key��ȡ�ַ������͵�value
	 */
	public String getStringValue(String key){
		return properties.getProperty(key);//ע��÷������ص����ַ���������������
	}
	public int getIntValue(String key){
		String value=properties.getProperty(key, "-1");//�÷����Ŀ���������������???
		//�ڶ�������ΪString�ͣ����ļ�û��key�򷵻����???
		return Integer.parseInt(value);
	} 
	public static void main(String[] args) {
		Config config=new Config("Server_config.properties");
		int port=config.getIntValue("port");
		System.out.println(port);
	}
	
}

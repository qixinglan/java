package day03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 反序列化对象数据
 * @author asus
 *
 */
public class ObjectInputStreamDemo {
	public static void main(String[] args) throws IOException, Exception {
		//创建用于读取保存对象字节的文件的输入流
		FileInputStream fis=new FileInputStream("person.obj");
		//创建用于反序列化的流
		ObjectInputStream ois=new ObjectInputStream(fis);
		Person person=(Person)ois.readObject();//返回object类型.需强制转换
		System.out.println(person.age);
		System.out.println(person.name);
		System.out.println(person.sex);
		ois.close();
	}
}

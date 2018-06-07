package day03;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


/**
 * 对象序列化
 * @author asus
 *
 */
public class ObjectOutputStreamDemo {
	public static void main(String[] args) throws IOException {
		/**
		 * 创建一个对象
		 */
		Person person=new Person();
		person.setAge(22);
		person.setName("谷亮");
		person.setSex(1);
		//创建写文件的字节输出流
		FileOutputStream fos=new FileOutputStream("person.obj");
		//创建可以序列化对象的输出流
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(person);//序列化并写入文件
		oos.close();
	}
}

package day03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 将对象序列化后直接反序列化
 * @author asus
 *
 */
public class ObjectIODemo {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Person person=new Person();
		person.setAge(23);
		person.setName("谷亮");
		person.setSex(1);
		//序列化并存储到文件
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("person2.object"));
		oos.writeObject(person);
		//反序列化
		FileInputStream fis=new FileInputStream("person2.object");
		ObjectInputStream ois=new ObjectInputStream(fis);
		Person person2=(Person)ois.readObject();
		System.out.println(person2.age);
		System.out.println(person2.name);
		System.out.println(person2.sex);//sex属性不被设置，默认值
		System.out.println(person2==person);//判断是否是同一个对象，但内容相同
		System.out.println(person2.equals(person));//内容相同
		oos.close();
		ois.close();
		
	}
}

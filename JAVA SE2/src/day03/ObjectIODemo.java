package day03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * ���������л���ֱ�ӷ����л�
 * @author asus
 *
 */
public class ObjectIODemo {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Person person=new Person();
		person.setAge(23);
		person.setName("����");
		person.setSex(1);
		//���л����洢���ļ�
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("person2.object"));
		oos.writeObject(person);
		//�����л�
		FileInputStream fis=new FileInputStream("person2.object");
		ObjectInputStream ois=new ObjectInputStream(fis);
		Person person2=(Person)ois.readObject();
		System.out.println(person2.age);
		System.out.println(person2.name);
		System.out.println(person2.sex);//sex���Բ������ã�Ĭ��ֵ
		System.out.println(person2==person);//�ж��Ƿ���ͬһ�����󣬵�������ͬ
		System.out.println(person2.equals(person));//������ͬ
		oos.close();
		ois.close();
		
	}
}

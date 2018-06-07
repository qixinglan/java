package day03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * �����л���������
 * @author asus
 *
 */
public class ObjectInputStreamDemo {
	public static void main(String[] args) throws IOException, Exception {
		//�������ڶ�ȡ��������ֽڵ��ļ���������
		FileInputStream fis=new FileInputStream("person.obj");
		//�������ڷ����л�����
		ObjectInputStream ois=new ObjectInputStream(fis);
		Person person=(Person)ois.readObject();//����object����.��ǿ��ת��
		System.out.println(person.age);
		System.out.println(person.name);
		System.out.println(person.sex);
		ois.close();
	}
}

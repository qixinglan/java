package day03;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


/**
 * �������л�
 * @author asus
 *
 */
public class ObjectOutputStreamDemo {
	public static void main(String[] args) throws IOException {
		/**
		 * ����һ������
		 */
		Person person=new Person();
		person.setAge(22);
		person.setName("����");
		person.setSex(1);
		//����д�ļ����ֽ������
		FileOutputStream fos=new FileOutputStream("person.obj");
		//�����������л�����������
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(person);//���л���д���ļ�
		oos.close();
	}
}

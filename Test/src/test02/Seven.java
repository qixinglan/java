package test02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Seven {
	public static void main(String[] args)throws Exception {
		Persion p1=new Persion();
		FileOutputStream fos1=new FileOutputStream("persion1.txt");
		ObjectOutputStream oos1=new ObjectOutputStream(fos1);
		oos1.writeObject(p1);
		oos1.close();
		FileInputStream fis1=new FileInputStream("persion1.txt");
		ObjectInputStream ois1=new ObjectInputStream(fis1);
		Persion p2=(Persion)ois1.readObject();
		ois1.close();
		System.out.println(p2.getAge());
		System.out.println(p2.getName());
	}
}

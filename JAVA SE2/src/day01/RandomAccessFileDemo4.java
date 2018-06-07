package day01;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * ��д�ļ�
 * @author asus
 *
 */
public class RandomAccessFileDemo4 {
	public static void main(String[] args) throws IOException {
		File file=new File("rw.dat");
		if(!file.exists()){
			file.createNewFile();
		}
	//д����
	RandomAccessFile raf=new RandomAccessFile(file, "rw");
	raf.writeInt(123123);
	raf.writeDouble(12.34);
	raf.write('A');
	raf.writeUTF("�Ұ������찲��");//java �ṩ��һ�γ��͵ķ���
	//������
	
	raf.seek(0);//�����α굽ָ��λ��
	System.out.println(raf.getFilePointer());//��ȡ�α�λ��
	System.out.println(raf.readInt());
	System.out.println(raf.readDouble());
	System.out.println((char)raf.read());//?????
	String str=raf.readUTF();
	System.out.println(str);
	raf.close();
	}
	
}

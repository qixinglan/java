package day02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ���ڶ�д������������
 * @author asus
 *�߼�������߼���
 */
public class DisAndDos {
	public static void main(String[] args) throws IOException {
		/*
		 * д�����������������
		 */
		FileOutputStream fos=new FileOutputStream("data.dat");
		/**
		 * ����������������������д�ļ����ٶ�
		 */
		BufferedOutputStream bos=new BufferedOutputStream(fos);
		DataOutputStream dos=new DataOutputStream(bos);
		dos.writeInt(1);//�ĸ��ֽ�
		dos.writeUTF("�Ұ����й�");//д���ַ�������
		//dos.writeBytes("�Ұ����л�");
		dos.writeDouble(12.5);//д��double��������
		dos.flush();//
		dos.close();//�رո߼���
		/*
		 * ��ȡ������������
		 */
		FileInputStream fis=new FileInputStream("data.dat");
		/**
		 * ���������ֽ�����������߶�ȡ�ļ�������
		 */
		BufferedInputStream bis=new BufferedInputStream(fis);
		DataInputStream dis=new DataInputStream(bis);
		System.out.println(dis.readInt());
		System.out.println(dis.readUTF());
		System.out.println(dis.readDouble());
		dis.close();
		
	}
}

package day02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 用于读写基本数据类型
 * @author asus
 *高级流处理高级流
 */
public class DisAndDos {
	public static void main(String[] args) throws IOException {
		/*
		 * 写入基本数据类型数据
		 */
		FileOutputStream fos=new FileOutputStream("data.dat");
		/**
		 * 创建缓冲输出流，用于提高写文件的速度
		 */
		BufferedOutputStream bos=new BufferedOutputStream(fos);
		DataOutputStream dos=new DataOutputStream(bos);
		dos.writeInt(1);//四个字节
		dos.writeUTF("我爱你中国");//写入字符串数据
		//dos.writeBytes("我爱你中华");
		dos.writeDouble(12.5);//写入double类型数据
		dos.flush();//
		dos.close();//关闭高级流
		/*
		 * 读取基本数据类型
		 */
		FileInputStream fis=new FileInputStream("data.dat");
		/**
		 * 创建缓冲字节输入流，提高读取文件的速率
		 */
		BufferedInputStream bis=new BufferedInputStream(fis);
		DataInputStream dis=new DataInputStream(bis);
		System.out.println(dis.readInt());
		System.out.println(dis.readUTF());
		System.out.println(dis.readDouble());
		dis.close();
		
	}
}

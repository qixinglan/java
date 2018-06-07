package day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * ��ȡ�ļ�����
 * @author asus
 *
 */
public class RandomAccessFileDemo2 {
	public static void main(String[] args) throws Exception {
		File file=new File("raf.txt");
		RandomAccessFile raf=new RandomAccessFile(file,"r");//���벶���쳣
		/**
		 * ��ȡһ���ֽڣ���int��ʽ����
		 * ����int   read������
		 * ������ֵΪ-1ʱ�����Ƕ������ļ���ĩβ
		 * EOF   end of file  
		 * ע�⣺��intֵֻ�еͰ�λ��Ч
		 */
		//��ȡ��һ���ֽ�
		char a=(char)raf.read();
		char b=(char)raf.read();
		System.out.println(a+","+b);
		/**
		 * ������ȡ�ĸ��ֽڣ���intֵ
		 */
		int num=0;
		int i=raf.read();
		num=(i<<24)|num;
		i=raf.read();
		num=(i<<16)|num;
		i=raf.read();
		num=(i<<8)|num;
		i=raf.read();
		num=i|num;
		System.out.println(num==Integer.MAX_VALUE);
		/**
		 * ������ȡ�ĸ��ֽڣ�ֱ��Ϊintֵ����
		 */
		System.out.println(raf.readInt());
		/**
		 * ��ȡ�ַ���
		 */
		int len=raf.readInt();//��ȡ�ַ����ֽ���
		/**
		 * int read(byte[] data)
		 * һ���Գ��Զ�ȡdata ���鳤�ȵ��ֽ����������������
		 * ����ֵΪʵ�ʶ�ȡ���ֽ���
		 * ������ֵΪ-1����ȡ���� �ļ�ĩβ
		 */
		byte[] data=new byte[len];
		raf.read(data);
		String str=new String(data,"GBK");
		System.out.println(str);
		raf.close();//�����쳣
	}
}

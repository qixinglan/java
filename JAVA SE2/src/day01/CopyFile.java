package day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * �����ļ���RandomAccessFile
 * @author asus
 *
 */
public class CopyFile {
	public static void main(String[] args) throws IOException {
		/**
		 * ˼·
		 * ����file������������Ҫ���Ƶ��ļ�
		 * ����һ��RandomAccessFile��ȡ
		 * �ڴ���һ���µ�File�����������ƺ���ļ�
		 * ������һ��RandomAccessFileд����
		 * 
		 * ��һ���ļ�������д����һ���ļ����ﵽ���Ƶ�Ŀ��
		 */
		//����Դ�ļ���File����
		File file=new File("a.doc");
		//����RandomAccessFile���󣬶�ȡԴ�ļ�
		RandomAccessFile raf=new RandomAccessFile(file,"r");
		//�������ƺ���ļ���File����
		File file2=new File("a2.doc");
		//����һ���µ�RandomAccessFile��������д����
		RandomAccessFile raf2=new RandomAccessFile(file2, "rw");
		//ѭ����Դ�ļ���ȡ�ֽڲ�д��Ŀ���ļ�
		int data=-1;
		while((data=raf.read())!=-1){//??????????????????????/
			raf2.write(raf.read());
		}
		System.out.println("�������");
		raf.close();
		raf2.close();
		
	}
}

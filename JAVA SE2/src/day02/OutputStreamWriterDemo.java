package day02;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/*
 * ʹ���ַ������д���ļ�
 */
public class OutputStreamWriterDemo {
	public static void main(String[] args) throws IOException {
		FileOutputStream fos=new FileOutputStream("writer.txt");
		OutputStreamWriter writer=new OutputStreamWriter(fos);
		/**
		 * write(int c)
		 * һ��д�����ֽڣ�һ���ַ���intֵ�ĵ�16λ
		 */
		writer.write("ABCDE");//д���ַ���
		char[] chs={'A','B','C','D','E'};
		/**
		 * write(Char[] ch)
		 * һ�ν�һ���ַ������е������ַ�д��
		 */
		writer.write(chs);
		/**
		 * write(char[],int start,int end)
		 */
		writer.write(chs, 1, 3);
		writer.close();
	}
}

package day03;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * ʹ���ļ��ַ���������������ı��ļ�
 */
public class FileCopy7 {
	public static void main(String[] args) throws IOException {
		/**
		 * �������ڶ�ȡ�ļ����ַ�������
		 */
		FileReader reader=new FileReader("CopeFile5.java");//����������
		/**
		 * ��������д���ļ����ַ������
		 */
		FileWriter writer=new FileWriter("Copy_Copy_FileCopy.java");
		int c=0;
		while((c=reader.read())!=-1){
			writer.write(c);
		}
		reader.close();
		writer.close();
	}
}

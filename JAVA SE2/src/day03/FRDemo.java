package day03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * ʹ���ַ���������ȡGBK���ı��ļ�
 */
public class FRDemo {
	public static void main(String[] args) throws IOException {
		/*
		 * ʹ���ļ��ַ���������ȡGBK�ı��ļ�
		 */
		FileReader reader=new FileReader("chars.txt");//������ʹ���Լ�����ϵͳĬ�ϵı��뼯�������޸ģ����Բ���
		int c=0;
		while((c=reader.read())!=-1){
			System.out.print((char)c);
		}
		reader.close();
		System.out.println();
		/**
		 * FileReader�������ñ��뼯�������ڶ�ȡ��ϵͳĬ�ϱ��뼯
		 * ��ͬ���ı��ļ�ʱ���������
		 * InputStreamReader��һ�����صĹ��췽��֧��ָ��������ı��뼯����ȡ�ļ�
		 */
		FileInputStream fis=new FileInputStream("chars.txt");
		InputStreamReader reader1=new InputStreamReader(fis,"GBK");//���ع��췽����ָ�����뼯
		int c1=0;
		while((c1=reader1.read())!=-1){
			System.out.print((char)c1);
		}
		reader1.close();
	}
}

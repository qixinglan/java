package day02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ʹ���ַ���������ȡ�ı��ļ�
 * @author asus
 */
public class InputStreamReaderDemo {
	public static void main(String[] args) throws IOException {
		/**
		 * ���ַ���������ȡ��ǰ�����Դ����
		 */
		FileInputStream fis=
			new FileInputStream("src"+File.separator+"day02"+
					File.separator+"InputStreamReaderDemo.java");
		/**
		 * InputStreamReader(InputStream in)
		 * �ù��췽��������
		 * ���������ֽ�����װΪһ�����ַ�Ϊ��λ��ȡ���ַ���
		 */
		InputStreamReader reader=new InputStreamReader(fis);
		/**
		 * int read()
		 * ��������ȡ�����ֽڣ�һ���ַ���������int��ʽ����
		 * ���Ը�intֵ��16λ������
		 * ������Ϊ-1�����ʾĩβ��EOF��
		 */
		int c=-1;
		while((c=reader.read())!=-1){
			System.out.print((char)c);//ע��ת��Ϊchar
		}
		reader.close();
	}
}

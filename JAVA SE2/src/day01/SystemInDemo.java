package day01;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * ��ȡ�����������ݲ�д���ļ���
 * @author asus
 *
 */
public class SystemInDemo {
	public static void main(String[] args) throws IOException {
		InputStream in=System.in;
		/**
		 * ���ȡ�ַ��������Ƚ���ת��Ϊ�ַ�������
		 */
		InputStreamReader reader=new InputStreamReader(in);
		/**
		 * ת��Ϊ���Ի��ж�ȡ�Ļ����ַ�������
		 */
		BufferedReader br=new BufferedReader(reader);
		/**
		 * 
		 */
		FileOutputStream fos=new FileOutputStream("input.txt");
		/**
		 * ת��Ϊ���ļ���д���ݵĻ����ַ������
		 * �������Զ���ˢ��
		 */
		PrintWriter writer=new PrintWriter(fos,true);
		String str=null;
		/**
		 * readLine(�������Ῠס��ֱ����������س����ŻὫ֮ǰ�����������Ϊһ���ַ�������
		 * ��Ϊ��������ʱԴԴ���ϵģ������ļ��о�ͷ
		 * ���������whileѭ��ʽ��Զ����ͣ��
		 * 
		 */
		while((str=br.readLine())!=null){
			if("exit".equals(str)){
				System.out.println("��лʹ�ã��ټ�����");
				break;
			}
			System.out.println("�������"+str);//����̨�����������
			writer.println(str);//���浽�ļ�
			
		}	
		br.close();
		writer.close();
	}
	
}

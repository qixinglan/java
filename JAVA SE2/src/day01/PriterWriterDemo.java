package day01;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
	/**
	 * �����Զ���ˢ�µĻ����Է������
	 * @param args
	 */
public class PriterWriterDemo {
	public static void main(String[] args) throws IOException {
//		File file=new File("pw.txt");
//		PrintWriter pw=new PrintWriter(file);
//		//PrintWriter pw=new PrintWriter("pw.txt");
		FileOutputStream fos=new FileOutputStream("pw.txt");//�Զ���ջ���
		//PrintWriter pw=new PrintWriter(fos);
		PrintWriter pw=new PrintWriter(fos,true);
		pw.println("������ֱ�������ַ���!!!!!!!!");//�����������ַ���
		pw.println("�ڶ���");//������
		//pw.flush();//��ջ���
		pw.close();
	}

}

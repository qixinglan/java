package day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

/**
 * System.out.println������ʲô
 * �������޸�
 */
public class SystemOutDemo {
	public static void main(String[] args) throws IOException {
		System.out.println();
		/**
		 * ����һ�����ļ�д���ݵ�PrintStream
		 */
		File file=new File("log.txt");
		PrintStream ps=new PrintStream(file);
		ps.println("д���ļ���ȥ");
		//������Կ���̨�������
		//PrintStream out=System.out;
		System.setOut(ps);//�޸�System���
		System.out.println("�������������Ҹ��˰ɣ��������������̨��");
		//System.setOut(out);//�ٸĻ�ȥ
		ps.close();
	}
}

package day01;

import java.io.File;

/**
 *��ȡ��ǰĿ¼�µ���������
 */
public class FileDemo6 {
	public static void main(String[] args) {
		//��ȡsrcĿ¼�µ���������
		File src=new File("src");
		//ֻ��ȡ��ǰĿ¼�µ��������������
		if(src.isDirectory()){
			String[] subName=src.list();
			for(String sub:subName){
				System.out.println(sub);
			}
		}
		//��ȡ��ǰĿ¼�µ����������file��
		File[] subs=src.listFiles();
		for(File sub:subs){
			System.out.println(sub.getName()+":"+sub.length());
		}
	}
}

package day01;

import java.io.File;

/*
 *ɾ���ļ���Ŀ¼
 */
public class FileDemo5 {
	public static void main(String[] args) {
		/**
		 * ɾ���ļ�
		 * 1.����File������������Ҫɾ�����ļ���Ŀ¼
		 * 2.����File�ķ�����ɾ��
		 */
		//ɾ��demo��txt
		File file=new File("demo.txt");
		if(file.exists()){
			file.delete();
		}
		//ɾ��demoĿ¼,Ҫ��demoĿ¼�����ǿյģ���Ŀ¼
		File dir=new File("demo");
		if(dir.exists()){
			dir.delete();
		}
	}
}

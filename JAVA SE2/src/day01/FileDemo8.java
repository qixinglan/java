package day01;

import java.io.File;
import java.io.FileFilter;

/**
 * ��ȡĿ¼�µĲ�������
 * @author asus
 *����������FileFilter�ඨ��
 */
public class FileDemo8 {
	public static void main(String[] args) {
		/**
		 * ���󣺻�ȡ��Ŀ¼�µ��ı��ĵ�
		 * 1����һ��FilFilter�������������
		 * 2ʹ��listfile�����������������벢���ط�������������
		 */
		FileFilter filter=new FileFilter() {//������?????����������ã���������
			public boolean accept(File file) {
				System.out.println("׼�����˵��ļ���:"+file.getName());
				/**
				 * �ı��ĵ�Ӧ������.txt��β
				 */
				return file.getName().endsWith(".txt");
			}
		};
		/**
		 * ����file�����ط���listfile��FileFilter filter��
		 * ��������
		 */
		File file=new File(".");
		File[] subs=file.listFiles(filter);
		for(File sub:subs){
			System.out.println(sub.getName());
		}
	}
}

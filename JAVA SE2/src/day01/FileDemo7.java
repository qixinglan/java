package day01;

import java.io.File;

/**
 * ɾ��Ŀ¼
 * @author asus
 *
 */
public class FileDemo7 {
	public static void main(String[] args) {
		File file=new File("a");
		delateFile(file);
	}
	/**
	 * ɾ���ļ���Ŀ¼,�ݹ����
	 */
	public static void delateFile(File file){
		if(file.isDirectory()){
			//ѭ��ɾ����������
			File[] subs=file.listFiles();
			for(File sub:subs){
				delateFile(sub);//�ݹ�
			}
		}
		file.delete();//ɾ��file����,�������ļ�����Ŀ¼
	}
}

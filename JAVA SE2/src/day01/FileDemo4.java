package day01;

import java.io.File;

/**
 * �����ļ�
 * @author asus
 *
 */
public class FileDemo4 {
	public static void main(String[] args) {
		/**
		 * �ڸ�Ŀ¼�´���demo.txt
		 * �����ļ��޷�һ���Դ����༶Ŀ¼�µĵ��ļ�
		 */
		//File file=new File("."+File.separator+"demo.txt");
		File file=new File("demo.txt");//Ĭ�Ͼ�����ĿĿ¼�����Բ�д
		if(!file.exists()){
			try{
				file.createNewFile();//���벶���쳣��ʵ�ʣ�������
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}

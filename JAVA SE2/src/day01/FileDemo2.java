package day01;

import java.io.File;

/**
 * ͨ��File���󴴽�Ŀ¼
 * @author asus
 * 
 */
public class FileDemo2 {
	public static void main(String[] args) {
		/**
		 * ����Ŀ¼��Ҫ����
		 * 1.ʹ��File��������Ҫ������Ŀ¼
		 * 2.ʹ��File����ķ�������Ŀ¼
		 * 
		 * �ڵ�ǰ��Ŀ¼д����demoĿ¼
		 */
		File file=new File("."+File.separator+"demo");
		if(!file.exists()){//��������
			file.mkdir();//����Ŀ¼
		}
		/**
		 * demoĿ¼���ٴ�����Ŀ¼sub
		 */
		//File sub=new File("."+File.separator+"demo"+File.separator+"sub");
		File sub=new File("src","sub");
		if(!sub.exists()){
			sub.mkdir();
		}
	}
}

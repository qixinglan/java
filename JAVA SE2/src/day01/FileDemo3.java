package day01;

import java.io.File;

/**
 * �������ɼ���Ŀ¼
 * @author asus
 *
 */
public class FileDemo3 {
	public static void main(String[] args) {
		File file=new File("."+File.separator+"a"
				+File.separator+"b"+File.separator+"c"+
				File.separator+"d");
		if(!file.exists()){
			file.mkdirs();//����Ŀ¼����ͬ�����ڸ�Ŀ¼
		}
	}
}

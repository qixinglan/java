package day01;

import java.io.File;

/**
 * 创建若干级子目录
 * @author asus
 *
 */
public class FileDemo3 {
	public static void main(String[] args) {
		File file=new File("."+File.separator+"a"
				+File.separator+"b"+File.separator+"c"+
				File.separator+"d");
		if(!file.exists()){
			file.mkdirs();//创建目录，连同不存在父目录
		}
	}
}

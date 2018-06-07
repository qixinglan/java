package day01;

import java.io.File;

/**
 * 通过File对象创建目录
 * @author asus
 * 
 */
public class FileDemo2 {
	public static void main(String[] args) {
		/**
		 * 创建目录需要两步
		 * 1.使用File对象描述要创建的目录
		 * 2.使用File对象的方法创建目录
		 * 
		 * 在当前根目录写创建demo目录
		 */
		File file=new File("."+File.separator+"demo");
		if(!file.exists()){//若不存在
			file.mkdir();//创建目录
		}
		/**
		 * demo目录下再创建子目录sub
		 */
		//File sub=new File("."+File.separator+"demo"+File.separator+"sub");
		File sub=new File("src","sub");
		if(!sub.exists()){
			sub.mkdir();
		}
	}
}

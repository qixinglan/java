package day01;

import java.io.File;

/*
 *删除文件或目录
 */
public class FileDemo5 {
	public static void main(String[] args) {
		/**
		 * 删除文件
		 * 1.创建File对象，用来描述要删除的文件或目录
		 * 2.调用File的方法来删除
		 */
		//删除demo。txt
		File file=new File("demo.txt");
		if(file.exists()){
			file.delete();
		}
		//删除demo目录,要求demo目录必须是空的，空目录
		File dir=new File("demo");
		if(dir.exists()){
			dir.delete();
		}
	}
}

package day01;

import java.io.File;

/**
 *获取当前目录下的所有子项
 */
public class FileDemo6 {
	public static void main(String[] args) {
		//获取src目录下的所有子项
		File src=new File("src");
		//只获取当前目录下的所有子项的名字
		if(src.isDirectory()){
			String[] subName=src.list();
			for(String sub:subName){
				System.out.println(sub);
			}
		}
		//获取当前目录下的所有子项的file类
		File[] subs=src.listFiles();
		for(File sub:subs){
			System.out.println(sub.getName()+":"+sub.length());
		}
	}
}

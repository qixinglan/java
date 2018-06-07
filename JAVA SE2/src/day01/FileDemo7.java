package day01;

import java.io.File;

/**
 * 删除目录
 * @author asus
 *
 */
public class FileDemo7 {
	public static void main(String[] args) {
		File file=new File("a");
		delateFile(file);
	}
	/**
	 * 删除文件或目录,递归调用
	 */
	public static void delateFile(File file){
		if(file.isDirectory()){
			//循环删除所有子项
			File[] subs=file.listFiles();
			for(File sub:subs){
				delateFile(sub);//递归
			}
		}
		file.delete();//删除file对象,无论是文件还是目录
	}
}

package day01;

import java.io.File;

/**
 * 创建文件
 * @author asus
 *
 */
public class FileDemo4 {
	public static void main(String[] args) {
		/**
		 * 在根目录下创建demo.txt
		 * 创建文件无法一次性创建多级目录下的的文件
		 */
		//File file=new File("."+File.separator+"demo.txt");
		File file=new File("demo.txt");//默认就是项目目录，可以不写
		if(!file.exists()){
			try{
				file.createNewFile();//必须捕获异常。实质？？？？
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}

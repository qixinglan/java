package day01;

import java.io.File;
import java.io.FileFilter;

/**
 * 获取目录下的部分子项
 * @author asus
 *过滤条件用FileFilter类定义
 */
public class FileDemo8 {
	public static void main(String[] args) {
		/**
		 * 需求：获取根目录下的文本文档
		 * 1定义一个FilFilter，定义过滤条件
		 * 2使用listfile方法并将过滤器传入并返回符合条件的子项
		 */
		FileFilter filter=new FileFilter() {//匿名类?????匿名类的作用？？？？？
			public boolean accept(File file) {
				System.out.println("准备过滤的文件是:"+file.getName());
				/**
				 * 文本文档应该是以.txt结尾
				 */
				return file.getName().endsWith(".txt");
			}
		};
		/**
		 * 调用file的重载方法listfile（FileFilter filter）
		 * 返回需求
		 */
		File file=new File(".");
		File[] subs=file.listFiles(filter);
		for(File sub:subs){
			System.out.println(sub.getName());
		}
	}
}

package day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

/**
 * System.out.println到底是什么
 * 并进行修改
 */
public class SystemOutDemo {
	public static void main(String[] args) throws IOException {
		System.out.println();
		/**
		 * 创建一个向文件写数据的PrintStream
		 */
		File file=new File("log.txt");
		PrintStream ps=new PrintStream(file);
		ps.println("写到文件中去");
		//保存针对控制台的输出流
		//PrintStream out=System.out;
		System.setOut(ps);//修改System输出
		System.out.println("哈哈哈哈，被我改了吧，你输出不到控制台啦");
		//System.setOut(out);//再改回去
		ps.close();
	}
}

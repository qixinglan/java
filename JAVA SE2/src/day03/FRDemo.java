package day03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 使用字符输入流读取GBK的文本文件
 */
public class FRDemo {
	public static void main(String[] args) throws IOException {
		/*
		 * 使用文件字符输入流读取GBK文本文件
		 */
		FileReader reader=new FileReader("chars.txt");//该流会使用自己操作系统默认的编码集，不能修改，所以不用
		int c=0;
		while((c=reader.read())!=-1){
			System.out.print((char)c);
		}
		reader.close();
		System.out.println();
		/**
		 * FileReader不能设置编码集，所以在读取与系统默认编码集
		 * 不同的文本文件时会出现乱码
		 * InputStreamReader有一个重载的构造方法支持指定的特殊的编码集来读取文件
		 */
		FileInputStream fis=new FileInputStream("chars.txt");
		InputStreamReader reader1=new InputStreamReader(fis,"GBK");//重载构造方法，指定编码集
		int c1=0;
		while((c1=reader1.read())!=-1){
			System.out.print((char)c1);
		}
		reader1.close();
	}
}

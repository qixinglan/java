package day02;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/*
 * 使用字符输出流写入文件
 */
public class OutputStreamWriterDemo {
	public static void main(String[] args) throws IOException {
		FileOutputStream fos=new FileOutputStream("writer.txt");
		OutputStreamWriter writer=new OutputStreamWriter(fos);
		/**
		 * write(int c)
		 * 一次写两个字节（一个字符）int值的低16位
		 */
		writer.write("ABCDE");//写入字符串
		char[] chs={'A','B','C','D','E'};
		/**
		 * write(Char[] ch)
		 * 一次将一个字符数组中的所有字符写出
		 */
		writer.write(chs);
		/**
		 * write(char[],int start,int end)
		 */
		writer.write(chs, 1, 3);
		writer.close();
	}
}

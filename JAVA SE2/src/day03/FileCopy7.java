package day03;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * 使用文件字符输入输出流复制文本文件
 */
public class FileCopy7 {
	public static void main(String[] args) throws IOException {
		/**
		 * 创建用于读取文件的字符输入流
		 */
		FileReader reader=new FileReader("CopeFile5.java");//参数不是流
		/**
		 * 创建用于写入文件的字符输出流
		 */
		FileWriter writer=new FileWriter("Copy_Copy_FileCopy.java");
		int c=0;
		while((c=reader.read())!=-1){
			writer.write(c);
		}
		reader.close();
		writer.close();
	}
}

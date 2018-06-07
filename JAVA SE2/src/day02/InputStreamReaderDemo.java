package day02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 使用字符输入流读取文本文件
 * @author asus
 */
public class InputStreamReaderDemo {
	public static void main(String[] args) throws IOException {
		/**
		 * 用字符输入流读取当前程序的源代码
		 */
		FileInputStream fis=
			new FileInputStream("src"+File.separator+"day02"+
					File.separator+"InputStreamReaderDemo.java");
		/**
		 * InputStreamReader(InputStream in)
		 * 该构造方法的作用
		 * 将给定的字节流包装为一个按字符为单位读取的字符流
		 */
		InputStreamReader reader=new InputStreamReader(fis);
		/**
		 * int read()
		 * 将连续读取两个字节（一个字符），并以int形式返回
		 * 所以该int值低16位有数据
		 * 若返回为-1，则表示末尾（EOF）
		 */
		int c=-1;
		while((c=reader.read())!=-1){
			System.out.print((char)c);//注意转换为char
		}
		reader.close();
	}
}

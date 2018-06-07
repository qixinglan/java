package day01;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
	/**
	 * 带有自动行刷新的缓冲自符输出流
	 * @param args
	 */
public class PriterWriterDemo {
	public static void main(String[] args) throws IOException {
//		File file=new File("pw.txt");
//		PrintWriter pw=new PrintWriter(file);
//		//PrintWriter pw=new PrintWriter("pw.txt");
		FileOutputStream fos=new FileOutputStream("pw.txt");//自动清空缓存
		//PrintWriter pw=new PrintWriter(fos);
		PrintWriter pw=new PrintWriter(fos,true);
		pw.println("带换行直接输入字符串!!!!!!!!");//带换行输入字符串
		pw.println("第二行");//带换行
		//pw.flush();//清空缓存
		pw.close();
	}

}

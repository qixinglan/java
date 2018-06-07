package day01;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * 读取键盘输入内容并写到文件中
 * @author asus
 *
 */
public class SystemInDemo {
	public static void main(String[] args) throws IOException {
		InputStream in=System.in;
		/**
		 * 想读取字符串，就先将其转换为字符输入流
		 */
		InputStreamReader reader=new InputStreamReader(in);
		/**
		 * 转换为可以换行读取的缓冲字符输入流
		 */
		BufferedReader br=new BufferedReader(reader);
		/**
		 * 
		 */
		FileOutputStream fos=new FileOutputStream("input.txt");
		/**
		 * 转换为向文件中写数据的缓冲字符输出流
		 * 并带有自动行刷新
		 */
		PrintWriter writer=new PrintWriter(fos,true);
		String str=null;
		/**
		 * readLine(）方法会卡住，直到我们输入回车，才会将之前输入的内容作为一个字符串返回
		 * 因为键盘数据时源源不断的，不像文件有尽头
		 * 所以这里的while循环式永远不会停的
		 * 
		 */
		while((str=br.readLine())!=null){
			if("exit".equals(str)){
				System.out.println("感谢使用，再见！！");
				break;
			}
			System.out.println("输入的是"+str);//控制台输出键盘内容
			writer.println(str);//保存到文件
			
		}	
		br.close();
		writer.close();
	}
	
}

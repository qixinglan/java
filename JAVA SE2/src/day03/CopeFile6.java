package day03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 缓冲字节输入输出流
 * @author asus
 *
 */
public class CopeFile6 {
	public static void main(String[] args) throws IOException {
		/**
		 * 
		 * 复制根文件目录下的CopeFile5。Java
		 */
		FileInputStream fis=new FileInputStream("CopeFile5.java");
		InputStreamReader irs=new InputStreamReader(fis);
		BufferedReader br=new BufferedReader(irs);//基于的流要求以字符为单位，缓冲字节流要求以字符为单位所以
		/**
		 * 
		 */
		
		FileOutputStream fos=new FileOutputStream("copy_CopyFile5.java");
		OutputStreamWriter osw=new OutputStreamWriter(fos);
		BufferedWriter bw=new BufferedWriter(osw);//基于的流要求以字符为单位
		
		String str=null;
		br.readLine();//一次读一行，直到换行符为止，然后将换行符之前的字符组组成字符串返回，注意返回值中不包含换行符，返回null时表示EOF
		while((str=br.readLine())!=null){
			System.out.println(str);
			bw.write(str);//直接将一个字符串写入
			bw.newLine();//输出换行符
		}
	
		
		br.close();
		bw.close();
	}
}

package day01;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Arrays;

//新思路发送数据？？？？？？？？？？？？？？？？？？？？？？？？

public class DemocClient {
	public static void main(String[] args) throws Exception {
		Socket s=new Socket("localhost",8088);
		InputStream  in=s.getInputStream();
		OutputStream out=s.getOutputStream();
		File file1=new File("1.jpg");
		File file2=new File("books.xml");
		//发送头文件  100 byte(type,filename,length)
		String t="jpg,"+file1.getName()+","+file1.length();
		send(file1,out,t);
		t="xml,"+file2.getName()+","+file2.length();
		send(file2,out,t);
		s.close();
		
//		//发送头文件  100 byte(type,filename,length)
//		String t1="jpg,"+file1.getName()+","+file1.length();
//		byte[] bytes=t1.getBytes("utf-8");
//		bytes=Arrays.copyOf(bytes, 100);
//		out.write(bytes);//发送头文件
//		out.flush();
//		//发送文件数据
//		BufferedInputStream fin=new BufferedInputStream(new FileInputStream(file1));
//		int b;
//		while((b=fin.read())!=-1){
//			out.write(b);
//		}
//		out.flush();
//		
//		
//		
//		//发送头文件  100 byte(type,filename,length)
//		t1="xml,"+file2.getName()+","+file2.length();
//		bytes=t1.getBytes("utf-8");
//		bytes=Arrays.copyOf(bytes, 100);
//		out.write(bytes);//发送头文件
//		out.flush();
//		//发送文件数据
//		 fin=new BufferedInputStream(new FileInputStream(file2));
//		while((b=fin.read())!=-1){
//			out.write(b);
//		}
//		out.flush();
//		s.close();
	}
	public static void send(File file,OutputStream out,String t) throws Exception{
		
		byte[] bytes=t.getBytes("utf-8");
		bytes=Arrays.copyOf(bytes, 100);
		out.write(bytes);//发送头文件
		out.flush();
		//发送文件数据
		BufferedInputStream fin=new BufferedInputStream(new FileInputStream(file));
		int b;
		while((b=fin.read())!=-1){
			out.write(b);
		}
		out.flush();
	}
}

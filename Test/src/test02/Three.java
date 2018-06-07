package test02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class Three {
	public static void main(String[] args) throws Exception{
		FileInputStream fis1=new FileInputStream("a.txt");
		BufferedInputStream  bis1=new BufferedInputStream(fis1);
		DataInputStream dis1=new DataInputStream(bis1);
		FileOutputStream fos1=new FileOutputStream("f.txt",true);
		BufferedOutputStream bos1=new BufferedOutputStream(fos1);
		DataOutputStream dos1=new DataOutputStream(fos1);
		int b;
		while((b=dis1.read())!=-1){
			dos1.write(b);
		}
		dis1.close();
		dos1.close();
		FileInputStream fis2=new FileInputStream("a.txt");
		BufferedInputStream  bis2=new BufferedInputStream(fis2);
		DataInputStream dis2=new DataInputStream(bis2);
		FileOutputStream fos2=new FileOutputStream("f.txt",true);
		BufferedOutputStream bos2=new BufferedOutputStream(fos2);
		DataOutputStream dos2=new DataOutputStream(fos2);
		byte [] date=new byte[1024];
		int len;
		while((len=dis2.read(date))!=-1){
			dos2.write(date,0,len);
		}
		dis2.close();
		dos2.close();
		FileInputStream fis3=new FileInputStream("a.txt");
		DataInputStream dis3=new DataInputStream(fis3);
		FileOutputStream fos3=new FileOutputStream("f.txt",true);
		DataOutputStream dos3=new DataOutputStream(fos3);
		byte [] date1=new byte[1024];
		int len1;
		while((len1=dis3.read(date1))!=-1){
			dos3.write(date1,0,len1);
		}
		dis3.close();
		dos3.close();
	}
}

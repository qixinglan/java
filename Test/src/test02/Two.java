package test02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Two {
	public static void main(String[] args) throws Exception{
		FileInputStream fis1=new FileInputStream("a.txt");
		BufferedInputStream  bis1=new BufferedInputStream(fis1);
		FileOutputStream fos1=new FileOutputStream("e.txt");
		BufferedOutputStream bos1=new BufferedOutputStream(fos1);
		int b;
		while((b=bis1.read())!=-1){
			bos1.write(b);
		}
		bis1.close();
		bos1.close();
		FileInputStream fis2=new FileInputStream("a.txt");
		FileOutputStream fos2=new FileOutputStream("e.txt",true);
		BufferedInputStream bis2=new BufferedInputStream(fis2);
		BufferedOutputStream bos2=new BufferedOutputStream(fos2);
		byte[] date=new byte[1024];
		int len;
		while((len=bis2.read(date))!=-1){
			bos2.write(date, 0, len);
		}
		bis2.close();
		bos2.close();
	}
}

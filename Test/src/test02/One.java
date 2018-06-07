package test02;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class One {
	public static void main(String[] args) throws Exception {
		FileInputStream fis1=new FileInputStream("a.txt");
		FileOutputStream fos1=new FileOutputStream("d.txt",true);
		int b;
		while((b=fis1.read())!=-1){
			fos1.write(b);
		}
		fis1.close();
		fos1.close();
		FileInputStream fis2=new FileInputStream("a.txt");
		FileOutputStream fos2=new FileOutputStream("d.txt",true);
		byte []date=new byte[1024];
		int b1;
		while((b1=fis2.read(date))!=-1){
			fos2.write(date, 0, b1);
		}
		fis2.close();
		fos2.close();
	}
}

package test02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Four {
	public static void main(String[] args)throws Exception {
		FileInputStream fis1=new FileInputStream("a.txt");
		InputStreamReader isr1=new InputStreamReader(fis1);
		FileOutputStream fos1=new FileOutputStream("g.txt",true);
		OutputStreamWriter osw1=new OutputStreamWriter(fos1);
		int b;
		while((b=isr1.read())!=-1){
			osw1.write(b);
		}
		isr1.close();
		osw1.close();
		FileInputStream fis2=new FileInputStream("a.txt");
		InputStreamReader isr2=new InputStreamReader(fis2);
		FileOutputStream fos2=new FileOutputStream("g.txt",true);
		OutputStreamWriter osw2=new OutputStreamWriter(fos2);
		int len;
		char date[]=new char[1024];
		while((len=isr2.read(date))!=-1){
			osw2.write(date,0,len);
		}
		isr2.close();
		osw2.close();
	}
}

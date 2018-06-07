package test02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Five {
	public static void main(String[] args) throws Exception{
		FileInputStream fis1=new FileInputStream("a.txt");
		InputStreamReader isr1=new InputStreamReader(fis1);
		BufferedReader br1=new BufferedReader(isr1);
		FileOutputStream fos1=new FileOutputStream("h.txt",true);
		OutputStreamWriter osw1=new OutputStreamWriter(fos1);
		BufferedWriter bw1=new BufferedWriter(osw1);
		int b;
		while((b=isr1.read())!=-1){
			bw1.write(b);
		}
		br1.close();
		bw1.close();
		FileInputStream fis2=new FileInputStream("a.txt");
		InputStreamReader isr2=new InputStreamReader(fis2);
		BufferedReader br2=new BufferedReader(isr2);
		FileOutputStream fos2=new FileOutputStream("h.txt",true);
		OutputStreamWriter osw2=new OutputStreamWriter(fos2);
		BufferedWriter bw2=new BufferedWriter(osw2);
		int len;
		char date[]=new char[1024];
		while((len=isr2.read(date))!=-1){
			bw2.write(date,0,len);
		}
		br2.close();
		bw2.close();
	}
}

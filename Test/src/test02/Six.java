package test02;

import java.io.FileReader;
import java.io.FileWriter;

public class Six {
	public static void main(String[] args) throws Exception{
		FileReader fr1=new FileReader("a.txt");
		FileWriter fw1=new FileWriter("i.txt");
		int b;
		while((b=fr1.read())!=-1){
			fw1.write(b);
		}
		fr1.close();
		fw1.close();
		FileReader fr2=new FileReader("a.txt");
		FileWriter fw2=new FileWriter("i.txt");
		int len;
		char[] date=new char[1024];
		while((len=fr2.read(date))!=-1){
			fw2.write(date,0,len);
		}
		fr2.close();
		fw2.close();
	}
}

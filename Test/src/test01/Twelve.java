package test01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class Twelve {
	public static void main(String[] args) throws Exception {
		File file1=new File("a.txt");
		RandomAccessFile raf1=new RandomAccessFile(file1,"r");
		File file2=new File("b.txt");
		RandomAccessFile raf2=new RandomAccessFile(file2,"rw");
		int date;
		while((date=raf1.read())!=-1){
			raf2.write(date);
		}
		raf2.close();
		raf1.seek(0);
		File file3=new File("c.txt");
		RandomAccessFile raf3=new RandomAccessFile(file3,"rw");
		byte[]date1=new byte[1024];
		int len=0;
		while((len=raf1.read(date1))!=-1){
			raf3.write(date1, 0, len);
		}
		raf3.close();		
	}
}

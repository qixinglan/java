package day01;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * ���ڻ���ĸ����ļ�(�ٶȿ죩
 * @author asus
 *
 */
public class CopyFile2 {
	public static void main(String[] args) throws IOException {
		File file=new File("a.doc");
		RandomAccessFile raf=new RandomAccessFile(file,"r");
		File file2=new File("a3.doc");
		RandomAccessFile raf2=new RandomAccessFile(file2,"rw");
		byte[] buf=new byte[1024*10];//һ�ζ�ȡ�Ĵ�С
		int len=0;
		
		while((len=raf.read(buf))!=-1){
			raf2.write(buf, 0, len);
		}
		System.out.println("�������");
		raf.close();
		raf2.close();
	}
}

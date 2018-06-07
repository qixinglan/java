package day01;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 基于缓存的复制文件(速度快）
 * @author asus
 *
 */
public class CopyFile2 {
	public static void main(String[] args) throws IOException {
		File file=new File("a.doc");
		RandomAccessFile raf=new RandomAccessFile(file,"r");
		File file2=new File("a3.doc");
		RandomAccessFile raf2=new RandomAccessFile(file2,"rw");
		byte[] buf=new byte[1024*10];//一次读取的大小
		int len=0;
		
		while((len=raf.read(buf))!=-1){
			raf2.write(buf, 0, len);
		}
		System.out.println("复制完成");
		raf.close();
		raf2.close();
	}
}

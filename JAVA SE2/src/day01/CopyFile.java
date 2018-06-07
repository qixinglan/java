package day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 复制文件用RandomAccessFile
 * @author asus
 *
 */
public class CopyFile {
	public static void main(String[] args) throws IOException {
		/**
		 * 思路
		 * 创建file对象用于描述要复制的文件
		 * 并用一个RandomAccessFile读取
		 * 在创建一个新的File对象描述复制后的文件
		 * 并再用一个RandomAccessFile写数据
		 * 
		 * 从一个文件读，再写到另一个文件，达到复制的目的
		 */
		//创建源文件的File对象
		File file=new File("a.doc");
		//创建RandomAccessFile对象，读取源文件
		RandomAccessFile raf=new RandomAccessFile(file,"r");
		//创建复制后的文件的File对象
		File file2=new File("a2.doc");
		//创建一个新的RandomAccessFile对象，用于写数据
		RandomAccessFile raf2=new RandomAccessFile(file2, "rw");
		//循坏从源文件读取字节并写到目标文件
		int data=-1;
		while((data=raf.read())!=-1){//??????????????????????/
			raf2.write(raf.read());
		}
		System.out.println("复制完成");
		raf.close();
		raf2.close();
		
	}
}

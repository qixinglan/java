package day02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CopyFile3 {
	public static void main(String[] args) throws Exception {
		/**
		 * 可以根据指定路径读取文件
		 */
		File file1=new File("fos.dat");
		File file2=new File("fos2.dat");
		FileInputStream fis=new FileInputStream(file1);//读数据FileInputStream fis=new FileInputStream("fos.dat");
		FileOutputStream fio=new FileOutputStream(file2);//写数据FileOutputStream fio=new FileOutputStream("fos2.dat");
		byte[] data=new byte[1024];
		int len=0;
		long time=System.currentTimeMillis();
		while((len=fis.read(data))!=-1){
			fio.write(data,0,len);
		}
		System.out.println(System.currentTimeMillis()-time);
		fis.close();
		fio.close();
	}
}

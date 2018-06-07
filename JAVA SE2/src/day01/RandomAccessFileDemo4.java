package day01;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 读写文件
 * @author asus
 *
 */
public class RandomAccessFileDemo4 {
	public static void main(String[] args) throws IOException {
		File file=new File("rw.dat");
		if(!file.exists()){
			file.createNewFile();
		}
	//写数据
	RandomAccessFile raf=new RandomAccessFile(file, "rw");
	raf.writeInt(123123);
	raf.writeDouble(12.34);
	raf.write('A');
	raf.writeUTF("我爱北京天安门");//java 提供了一次成型的方法
	//读数据
	
	raf.seek(0);//滑动游标到指定位置
	System.out.println(raf.getFilePointer());//获取游标位置
	System.out.println(raf.readInt());
	System.out.println(raf.readDouble());
	System.out.println((char)raf.read());//?????
	String str=raf.readUTF();
	System.out.println(str);
	raf.close();
	}
	
}

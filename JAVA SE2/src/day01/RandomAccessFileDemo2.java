package day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * 读取文件内容
 * @author asus
 *
 */
public class RandomAccessFileDemo2 {
	public static void main(String[] args) throws Exception {
		File file=new File("raf.txt");
		RandomAccessFile raf=new RandomAccessFile(file,"r");//必须捕获异常
		/**
		 * 读取一个字节，以int形式返回
		 * 返回int   read（）；
		 * 当返回值为-1时，就是读到了文件的末尾
		 * EOF   end of file  
		 * 注意：读int值只有低八位有效
		 */
		//读取第一个字节
		char a=(char)raf.read();
		char b=(char)raf.read();
		System.out.println(a+","+b);
		/**
		 * 连续读取四个字节，读int值
		 */
		int num=0;
		int i=raf.read();
		num=(i<<24)|num;
		i=raf.read();
		num=(i<<16)|num;
		i=raf.read();
		num=(i<<8)|num;
		i=raf.read();
		num=i|num;
		System.out.println(num==Integer.MAX_VALUE);
		/**
		 * 连续读取四个字节，直接为int值返回
		 */
		System.out.println(raf.readInt());
		/**
		 * 读取字符串
		 */
		int len=raf.readInt();//读取字符串字节数
		/**
		 * int read(byte[] data)
		 * 一次性尝试读取data 数组长度的字节量，并传入该数组
		 * 返回值为实际读取的字节量
		 * 若返回值为-1，读取到了 文件末尾
		 */
		byte[] data=new byte[len];
		raf.read(data);
		String str=new String(data,"GBK");
		System.out.println(str);
		raf.close();//捕获异常
	}
}

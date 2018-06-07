package day01;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * File类，用于描述文件系统中的一个文件或目录
 * 测试描述当前项目根目录下的file。txt
 * 并获取该文件的小一些信息
 * @author asus
 *
 */
public class FileDemo {
	public static void main(String[] args) throws Exception{
		//创建一个File对象
		/*
		 * 描述文件路径时，尽可能书写相对路径，便于跨平台支持
		 * ” .“表示当前目录，即当前项目的根目录
		 */
		//File file=new File("E:/JAVA SE2/file.txt");
		File file=new File("."+File.separator+"file.txt");
		System.out.println(file.exists()); //判断file是否存在，存在true，不存在false
		System.out.println(file.isFile());//判断file是否是文件
		System.out.println(file.isDirectory());//判断file是否是目录
		System.out.println(file.getName());//返回file名字
		System.out.println(file.length());//返回file的字节数，一个汉字两个字节
		System.out.println(file.lastModified());//返回long类型毫秒值，最后文件修改时间
		Date date=new Date(file.lastModified());
		SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd,HH:mm:ss");
		String str=format.format(date);
		System.out.println(str);
		System.out.println(file.getPath());//获取file路径
		System.out.println(file.getAbsolutePath());//获取绝对路径
		System.out.println(file.getCanonicalPath());//获取操作系统中file绝对路径，必须捕获异常
		System.out.println(file.canRead());//获取file是否可读
		System.out.println(file.canWrite());//获取file是否可写
	}
}

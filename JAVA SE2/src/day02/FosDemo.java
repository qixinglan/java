package day02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件字节输出流
 * 用于向 文件中写数据
 * @author asus
 *
 */
public class FosDemo {
	public static void main(String[] args) {
		/**
		 * 向文件写数据
		 */
		//创建要写的文件File对象
		File file=new File("fos.dat");
		FileOutputStream fos=null;
		try{
			fos=new FileOutputStream(file);//没有File文件，会自动创建
			fos.write('A');//写入一个字节
			/**
			 * 写入字符流
			 * 只有三个方法，只能写入一个字符或字符流
			 */
			String str="我叫谷亮";
			byte[] data=str.getBytes("UTF-8");
			int length=data.length;//存入一个int值，用于记录
			fos.write(length>>>24);
			fos.write(length>>>16);
			fos.write(length>>>8);
			fos.write(length);
			fos.write(data);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(fos!=null){
				try{
					fos.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	
}

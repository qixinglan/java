package day02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 使用具有缓冲功能的输入输出流进行读写操作
 * 并完成复制工作
 * @author asus
 *
 */
public class CopyFile4 {	
	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("a.doc");
		FileOutputStream fos=new FileOutputStream("a4.doc");
		/**
		 * 创建具有缓冲功能的输入输出流
		 */
		BufferedInputStream bis=new BufferedInputStream(fis);
		BufferedOutputStream bos=new BufferedOutputStream(fos);//基于流，参数为流的就是高级流
		
		int d=0;
		while((d=bis.read())!=-1){
			bos.write(d);
		}
		bos.flush();//强制将缓存区中的的数据写出（用于还在缓存的数据）
		System.out.println("复制完毕");
		bis.close();//其实close包含了flush方法
		bos.close();//只需关外围流（高级流），外围流会先关闭自身控制的内部流，再关闭自己的流
		
	}
}


package day02;

import java.io.File;
import java.io.FileInputStream;

/**
 * 文件字节输入流
 * 从指定文件读取数据
 * @author asus
 *
 */
public class FisDemo {
	public static void main(String[] args) {
		File file=new File("fos.dat");
		FileInputStream fis=null;
		try{
			fis=new FileInputStream(file);
			System.out.println((char)fis.read());//读一个字节
			
			int num=0;//读一个int值，用于
			int i=fis.read();
			num=(i<<24)|num;
			i=fis.read();
			num=(i<<16)|num;
			i=fis.read();
			num=(i<<8)|num;
			i=fis.read();
			num=i|num;
			byte[] data=new byte[num];
			fis.read(data);//返回值为-1说明读到了文件末尾,返回值为实际读到的字节数
			String str=new String(data,"utf-8");
			System.out.println(str);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(fis!=null){
					fis.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			 }
		}
	}
}

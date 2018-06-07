package day01;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * 读写文件
 * @author asus
 *
 */
public class RandomAccessFileDemo {
	public static void main(String[] args) {
		RandomAccessFile raf=null;
		try{
		/**
		 * 向文件raf.dat写入数据
		 */
		File file=new File("raf.txt");
		if(!file.exists()){
			file.createNewFile();
		}
		/**
		 * RandomAccessFile类
		 */
		
		/**
		 * write(int date)
		 * 只能写一个低八位二进制
		 */
		raf=new RandomAccessFile(file,"rw");
		raf.write('1');
		raf.write('2');//字符1,2未超过八位二进制范围
		//写一个int值进去
		int max=Integer.MAX_VALUE;
		raf.write(max>>>24);
		raf.write(max>>>16);
		raf.write(max>>>8);
		raf.write(max);
		raf.writeInt(Integer.MAX_VALUE);//
		String str="我爱北京天安门";
		byte[]data=str.getBytes("GBK");
		raf.writeInt(data.length);
		raf.writeChars("asd");
		raf.write(data);//一次性将一个字节数组的内容全写进去write(byte[] b)
		//我们写进一个字符串，通常要先写进一个字符串的字节数，以便我们读取的时候方便，能一次性读取完
//		/**
//		 * write(byte[] data,int start,int len)
//		 * 从start开始，写len个字节,start+Len不能超过数组的长度
//		 */
//		raf.write(data, 0, 8);
//		
		raf.close();//使用文件后要关闭
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(raf!=null){
					raf.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
}

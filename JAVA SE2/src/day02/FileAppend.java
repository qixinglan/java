package day02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 向文件中追加内容意思是。。。。。。。
 * @author asus
 *
 */
public class FileAppend {
	public static void main(String[] args) throws IOException {
		/**
		 * RandomAccessFile向文件中追加内容
		 */
		File file=new File("append.txt");
		RandomAccessFile raf=new RandomAccessFile(file, "rw");
		//将游标移到文件末尾
		raf.seek(raf.length());//raf.seek(file.length());
		raf.writeUTF("你好！我好！大家好！");
		raf.close();
		/**
		 * 使用文件输出流向文件末尾添加内容
		 */
		FileOutputStream fos=new FileOutputStream(file,true);//加上个true即可
		String str="我要成为火影似的男人";
		byte[] data=str.getBytes("UTF-8");
		fos.write(data);
		fos.close();
	}
}

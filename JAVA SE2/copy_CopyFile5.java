
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 使用字符输入输出流复制文本文件（只能）
 * @author asus
 *
 */
public class CopeFile5 {
	public static void main(String[] args) throws IOException {
		/**
		 * 1创建一个文件字节输入流，用于读取当前文件
		 * 2将字节输入流包装成字符输入流，用字符为单位读取数据
		 * 3创建一个文件字节输出流，用于写入复制的文件
		 * 4将字节输出流包装成字符输出流，用字符为单位写数据
		 * 5循环从当前文件读取字符并写入要复制的文件
		 * 6关闭输入输出流
		 */
		FileInputStream fis=new FileInputStream("src"+File.separator+
				"day02"+File.separator+"CopeFile5.java");
		InputStreamReader reader=new InputStreamReader(fis);
		FileOutputStream fos=new FileOutputStream("CopeFile5.java");
		OutputStreamWriter writer=new OutputStreamWriter(fos);
		int i=0;
		while((i=reader.read())!=-1){
			writer.write(i);
		}
		/**
		 * reader另一个读取字符的方法
		 * int read(char[] chs)
		 * 返回读取到的实际字符数
		 * 返回值为-1，则为 EOF
		 */
//		char[] chs=new char[5];
//		int len=0;
//		while((len=reader.read(chs))!=-1){
//			writer.write(chs, 0, len);
//		}
		reader.close();
		writer.close();
		
	}
}

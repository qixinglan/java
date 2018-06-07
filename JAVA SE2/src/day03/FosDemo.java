package day03;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 使用GBK编码项文件中写字符串
 * @author asus
 *
 */
public class FosDemo {
	public static void main(String[] args) throws IOException {
		String str="才能接女儿女噢内容vmeriogjeofeorfoerjmgovknerkovokrngvo  ";
		FileOutputStream fos=new FileOutputStream("chars.txt");
		byte[] data=str.getBytes("GBK");
		fos.write(data);
		fos.close();
		
	}
}

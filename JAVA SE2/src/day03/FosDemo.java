package day03;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * ʹ��GBK�������ļ���д�ַ���
 * @author asus
 *
 */
public class FosDemo {
	public static void main(String[] args) throws IOException {
		String str="���ܽ�Ů��Ů������vmeriogjeofeorfoerjmgovknerkovokrngvo  ";
		FileOutputStream fos=new FileOutputStream("chars.txt");
		byte[] data=str.getBytes("GBK");
		fos.write(data);
		fos.close();
		
	}
}

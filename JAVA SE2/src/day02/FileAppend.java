package day02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * ���ļ���׷��������˼�ǡ�������������
 * @author asus
 *
 */
public class FileAppend {
	public static void main(String[] args) throws IOException {
		/**
		 * RandomAccessFile���ļ���׷������
		 */
		File file=new File("append.txt");
		RandomAccessFile raf=new RandomAccessFile(file, "rw");
		//���α��Ƶ��ļ�ĩβ
		raf.seek(raf.length());//raf.seek(file.length());
		raf.writeUTF("��ã��Һã���Һã�");
		raf.close();
		/**
		 * ʹ���ļ���������ļ�ĩβ�������
		 */
		FileOutputStream fos=new FileOutputStream(file,true);//���ϸ�true����
		String str="��Ҫ��Ϊ��Ӱ�Ƶ�����";
		byte[] data=str.getBytes("UTF-8");
		fos.write(data);
		fos.close();
	}
}

package day02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ʹ�þ��л��幦�ܵ�������������ж�д����
 * ����ɸ��ƹ���
 * @author asus
 *
 */
public class CopyFile4 {	
	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("a.doc");
		FileOutputStream fos=new FileOutputStream("a4.doc");
		/**
		 * �������л��幦�ܵ����������
		 */
		BufferedInputStream bis=new BufferedInputStream(fis);
		BufferedOutputStream bos=new BufferedOutputStream(fos);//������������Ϊ���ľ��Ǹ߼���
		
		int d=0;
		while((d=bis.read())!=-1){
			bos.write(d);
		}
		bos.flush();//ǿ�ƽ��������еĵ�����д�������ڻ��ڻ�������ݣ�
		System.out.println("�������");
		bis.close();//��ʵclose������flush����
		bos.close();//ֻ�����Χ�����߼���������Χ�����ȹر�������Ƶ��ڲ������ٹر��Լ�����
		
	}
}

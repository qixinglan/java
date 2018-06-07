package day02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * �ļ��ֽ������
 * ������ �ļ���д����
 * @author asus
 *
 */
public class FosDemo {
	public static void main(String[] args) {
		/**
		 * ���ļ�д����
		 */
		//����Ҫд���ļ�File����
		File file=new File("fos.dat");
		FileOutputStream fos=null;
		try{
			fos=new FileOutputStream(file);//û��File�ļ������Զ�����
			fos.write('A');//д��һ���ֽ�
			/**
			 * д���ַ���
			 * ֻ������������ֻ��д��һ���ַ����ַ���
			 */
			String str="�ҽй���";
			byte[] data=str.getBytes("UTF-8");
			int length=data.length;//����һ��intֵ�����ڼ�¼
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


package day02;

import java.io.File;
import java.io.FileInputStream;

/**
 * �ļ��ֽ�������
 * ��ָ���ļ���ȡ����
 * @author asus
 *
 */
public class FisDemo {
	public static void main(String[] args) {
		File file=new File("fos.dat");
		FileInputStream fis=null;
		try{
			fis=new FileInputStream(file);
			System.out.println((char)fis.read());//��һ���ֽ�
			
			int num=0;//��һ��intֵ������
			int i=fis.read();
			num=(i<<24)|num;
			i=fis.read();
			num=(i<<16)|num;
			i=fis.read();
			num=(i<<8)|num;
			i=fis.read();
			num=i|num;
			byte[] data=new byte[num];
			fis.read(data);//����ֵΪ-1˵���������ļ�ĩβ,����ֵΪʵ�ʶ������ֽ���
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

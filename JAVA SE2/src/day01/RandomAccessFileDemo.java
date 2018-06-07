package day01;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * ��д�ļ�
 * @author asus
 *
 */
public class RandomAccessFileDemo {
	public static void main(String[] args) {
		RandomAccessFile raf=null;
		try{
		/**
		 * ���ļ�raf.datд������
		 */
		File file=new File("raf.txt");
		if(!file.exists()){
			file.createNewFile();
		}
		/**
		 * RandomAccessFile��
		 */
		
		/**
		 * write(int date)
		 * ֻ��дһ���Ͱ�λ������
		 */
		raf=new RandomAccessFile(file,"rw");
		raf.write('1');
		raf.write('2');//�ַ�1,2δ������λ�����Ʒ�Χ
		//дһ��intֵ��ȥ
		int max=Integer.MAX_VALUE;
		raf.write(max>>>24);
		raf.write(max>>>16);
		raf.write(max>>>8);
		raf.write(max);
		raf.writeInt(Integer.MAX_VALUE);//
		String str="�Ұ������찲��";
		byte[]data=str.getBytes("GBK");
		raf.writeInt(data.length);
		raf.writeChars("asd");
		raf.write(data);//һ���Խ�һ���ֽ����������ȫд��ȥwrite(byte[] b)
		//����д��һ���ַ�����ͨ��Ҫ��д��һ���ַ������ֽ������Ա����Ƕ�ȡ��ʱ�򷽱㣬��һ���Զ�ȡ��
//		/**
//		 * write(byte[] data,int start,int len)
//		 * ��start��ʼ��дlen���ֽ�,start+Len���ܳ�������ĳ���
//		 */
//		raf.write(data, 0, 8);
//		
		raf.close();//ʹ���ļ���Ҫ�ر�
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

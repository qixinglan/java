package day01;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * File�࣬���������ļ�ϵͳ�е�һ���ļ���Ŀ¼
 * ����������ǰ��Ŀ��Ŀ¼�µ�file��txt
 * ����ȡ���ļ���СһЩ��Ϣ
 * @author asus
 *
 */
public class FileDemo {
	public static void main(String[] args) throws Exception{
		//����һ��File����
		/*
		 * �����ļ�·��ʱ����������д���·�������ڿ�ƽ̨֧��
		 * �� .����ʾ��ǰĿ¼������ǰ��Ŀ�ĸ�Ŀ¼
		 */
		//File file=new File("E:/JAVA SE2/file.txt");
		File file=new File("."+File.separator+"file.txt");
		System.out.println(file.exists()); //�ж�file�Ƿ���ڣ�����true��������false
		System.out.println(file.isFile());//�ж�file�Ƿ����ļ�
		System.out.println(file.isDirectory());//�ж�file�Ƿ���Ŀ¼
		System.out.println(file.getName());//����file����
		System.out.println(file.length());//����file���ֽ�����һ�����������ֽ�
		System.out.println(file.lastModified());//����long���ͺ���ֵ������ļ��޸�ʱ��
		Date date=new Date(file.lastModified());
		SimpleDateFormat format=new SimpleDateFormat("yyyy��MM��dd,HH:mm:ss");
		String str=format.format(date);
		System.out.println(str);
		System.out.println(file.getPath());//��ȡfile·��
		System.out.println(file.getAbsolutePath());//��ȡ����·��
		System.out.println(file.getCanonicalPath());//��ȡ����ϵͳ��file����·�������벶���쳣
		System.out.println(file.canRead());//��ȡfile�Ƿ�ɶ�
		System.out.println(file.canWrite());//��ȡfile�Ƿ��д
	}
}

package day04;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ˯������
 * @author asus
 *
 */
public class SleepBlockDemo {
	public static void main(String[] args) {
		/**���ӱ�
		 * ���ʱ���ʽ12:33:30
		 */
		
		while(true){
			Date date=new Date();
			SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
			String now=format.format(date);
			System.out.println(now);
			//ÿ��һ����ѭ��һ��
			try {
				/* 1��Ϊʲô�����쳣       ֪ͨ��ǰ�߳����ڱ��ж�
				 * 2��sleep�ǵ�ǰ���̽������������������ĸ�����   
				 * �����ǵĳ�������ʱ������ϵͳ������һ���������������ǵ�JVM��
				 * ��JVM����������ᴴ��һ���߳�������main������
				 * �Ӷ�ִ�����ǵĳ��������sleep���������ľ�������߳�
				 * 3��ѭ����һ����ִ��һ����
				 * ���ǣ���������
				 * ֻ��˵������һ���ӣ�����״̬�����󣬵�ǰ�̻߳�ص�runnable״̬���ȴ��ٴη���ʱ��Ƭ�Ӷ����У������ʱ��������
				 */
				
				Thread.sleep(1000);//���벶���쳣
				Thread current=Thread.currentThread();//���ص�ǰ�߳�
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

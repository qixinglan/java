package day02;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * ������Calender
 */
public class Demo10 {
	public static void main(String[] args){
		//����Calenderʵ��
		//Calendar calendar=new Calendar.getInstance();
		Calendar calendar=Calendar.getInstance();
		//����������Calendarʵ���������ǵ�ǰϵͳʱ��
		System.out.println(calendar);
		calendar.set(Calendar.YEAR, 1985);//�޸�ʱ��Ϊ1985-05-01
		calendar.set(Calendar.MONTH, 4);//���´�0��ʼ
		calendar.set(Calendar.DAY_OF_MONTH, 01);
		//Date date=calendar.getTime();//��Calender������ʱ��ת��ΪDate��ʽ�Ķ���
		//System.out.println(date);
		System.out.println(calendar.getTime());
		int week=calendar.get(Calendar.DAY_OF_WEEK);//������ڼ�
		System.out.println(week);//������Ϊ�����ǵ�һ��
		int day=calendar.get(Calendar.DAY_OF_YEAR);
		System.out.println(day);
		int sum=calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
		System.out.println(sum);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str=format.format(calendar.getTime());
		System.out.println(str);
		
	}
}

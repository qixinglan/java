package day02;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * 日历类Calender
 */
public class Demo10 {
	public static void main(String[] args){
		//创建Calender实例
		//Calendar calendar=new Calendar.getInstance();
		Calendar calendar=Calendar.getInstance();
		//创建出来的Calendar实例描述的是当前系统时间
		System.out.println(calendar);
		calendar.set(Calendar.YEAR, 1985);//修改时间为1985-05-01
		calendar.set(Calendar.MONTH, 4);//计月从0开始
		calendar.set(Calendar.DAY_OF_MONTH, 01);
		//Date date=calendar.getTime();//将Calender描述的时间转换为Date方式的对象
		//System.out.println(date);
		System.out.println(calendar.getTime());
		int week=calendar.get(Calendar.DAY_OF_WEEK);//获得星期几
		System.out.println(week);//国外认为周日是第一天
		int day=calendar.get(Calendar.DAY_OF_YEAR);
		System.out.println(day);
		int sum=calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
		System.out.println(sum);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str=format.format(calendar.getTime());
		System.out.println(str);
		
	}
}

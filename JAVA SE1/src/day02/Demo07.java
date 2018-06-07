package day02;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Demo07 {
	public static void main(String[] args) {
		Date date =new Date();//默认是当前时间相当于System。currentTime（）
		int year=date.getYear()+1900;
		int month=date.getMonth()+1;
		int day=date.getDay();
		System.out.println(year+","+month+","+day);
		date.setTime(0);
		year=date.getYear()+1900;
		month=date.getMonth()+1;
		System.out.println(year+","+month);
		date.setTime(-1000*60*60*8);
		year=date.getYear()+1900;
		month=date.getMonth()+1;
		System.out.println(year+","+month);
		long now=System.currentTimeMillis();
		date.setTime(now+1000L*60*60*24);
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		String str=fmt.format(date);//便捷时间输出
		System.out.println(str);
		System.out.println(fmt.format(0));//
		System.out.println(System.currentTimeMillis());
		
		
	}
}









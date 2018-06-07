package day02;

import java.util.Calendar;
import java.util.Date;

/*
 * 
 */
public class Calendardemo2 {
	public static void main(String[] args) {
		Date date1=new Date();
		Date date2=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.set(2014, 9, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		date2=calendar.getTime();
		System.out.println(date2);
		System.out.println((date2.getTime()-date1.getTime())/(1000*60.0));
	}
}

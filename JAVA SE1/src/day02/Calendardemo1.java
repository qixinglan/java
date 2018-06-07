package day02;

import java.util.Calendar;
/*获取下个月今天周几
 * 1.用calendar获取现在时间
 * 2.用add方法让时间加一个月
 * 3.用get方法获取星期几
 */
public class Calendardemo1 {
	public static void main(String[] args) {
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);
		int week=calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println(week==1?1:week-1);
	}
}

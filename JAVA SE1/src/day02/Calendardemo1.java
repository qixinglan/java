package day02;

import java.util.Calendar;
/*��ȡ�¸��½����ܼ�
 * 1.��calendar��ȡ����ʱ��
 * 2.��add������ʱ���һ����
 * 3.��get������ȡ���ڼ�
 */
public class Calendardemo1 {
	public static void main(String[] args) {
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);
		int week=calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println(week==1?1:week-1);
	}
}

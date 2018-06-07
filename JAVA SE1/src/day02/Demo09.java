package day02;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/*
 * DateFormat ÊÇsimpleDateFormatµÄ¸¸Àà
 */
public class Demo09 {
	public static void main(String[]args){
		Date date=new Date();
		DateFormat format=DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.CHINA);
		System.out.println(format.format(date));
		
	}
}

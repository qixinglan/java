package test01;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Nine {
	public static void main(String[] args) {
		String regex1="a";
		String s1="abc";
		System.out.println(s1. matches(regex1));
		String s2="abcdefg";
		String regex2="bc";
		System.out.println(Arrays.toString(s2.split(regex2)));
		System.out.println(s2.replaceAll(regex2,"s"));
		StringBuilder sb=new StringBuilder();
		sb.append("ss");
		System.out.println(sb);
		String s3=sb.toString();
		Date date1=new Date();
		System.out.println(date1);
		Date date2=new Date(System.currentTimeMillis());
		System.out.println(date2);
		Date date3=new java.sql.Date(System.currentTimeMillis());
		Time time=new Time(System.currentTimeMillis());
		System.out.println(date3);
		System.out.println(time);
		Timestamp ts=new Timestamp(System.currentTimeMillis());
		System.out.println(ts);
		DateFormat df1=DateFormat.getDateInstance();
		System.out.println(df1.format(date3));
		DateFormat df2=DateFormat.getDateTimeInstance(DateFormat.YEAR_FIELD,DateFormat.ERA_FIELD,new Locale("zh", "CH"));
		System.out.println(df2.format(date1));
		Calendar c=Calendar.getInstance();
		System.out.println(c.getTimeInMillis());
		System.out.println(c.getTime().getTime());
	}
}

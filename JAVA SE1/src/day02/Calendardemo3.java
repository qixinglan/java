package day02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calendardemo3 {
	public static void main(String[] args) throws ParseException {
		long l=System.currentTimeMillis();
		String str="2014-10-1 0:0:0:000";
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		Date date=format.parse(str);
		double time=(date.getTime()-l)/1000/60.0;
		System.out.println(time);
	}
}

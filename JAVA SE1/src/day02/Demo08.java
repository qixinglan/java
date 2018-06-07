package day02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 日期与字符串之间的转换
 */
		
		
public class Demo08 {
	public static void main(String[] args) throws ParseException {
		String timerStr="2013-04-13";
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		//创建SimpleDateFormat
		Date date=format.parse(timerStr);//必须捕获异常
		System.out.println(date);
	}		
}
package day02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * �������ַ���֮���ת��
 */
		
		
public class Demo08 {
	public static void main(String[] args) throws ParseException {
		String timerStr="2013-04-13";
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		//����SimpleDateFormat
		Date date=format.parse(timerStr);//���벶���쳣
		System.out.println(date);
	}		
}
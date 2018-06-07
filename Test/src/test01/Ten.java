package test01;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Ten {
	public static void main(String[] args) {
		Integer i1=new Integer(1);
		Integer i2=new Integer(1);
		System.out.println(i1==i2);
		int i3=1;
		System.out.println(i1==i3);
		Integer i4=Integer.valueOf(1);
		Integer i5=Integer.valueOf(1);
		System.out.println(i4==i5);
		System.out.println(i4==i1);
		Integer i6=Integer.valueOf(300);
		Integer i7=Integer.valueOf(300);
		System.out.println(i6==i7);
		Double d1=Double.valueOf(1.0);
		Double d2=Double.valueOf(1.0);
		System.out.println(d1==d2);
		String s1="123";
		int i8=Integer.parseInt(s1);
		System.out.println(i8);
		String s2=Integer.toString(i8);
		String s3=""+i8;
		Integer i9=123;
		String s4=i9.toString();
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.toHexString(100));
		System.out.println(Integer.toBinaryString(100));
		System.out.println(Integer.toOctalString(9));
		BigDecimal bd1=new BigDecimal("1");
		BigDecimal bd2=new BigDecimal("3");
		System.out.println(bd1.divide(bd2,8,BigDecimal.ROUND_HALF_UP));
		BigInteger bi1=new BigInteger("19999");
		BigInteger bi2=new BigInteger("9999");
		System.out.println(bi1.multiply(bi2));
				
	}
}

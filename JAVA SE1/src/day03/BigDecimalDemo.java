package day03;

import java.math.BigDecimal;

public class BigDecimalDemo {
	public static void main(String[] args){
		BigDecimal d1=new BigDecimal("30000000.0");
		BigDecimal d2=new BigDecimal("2.9");
		System.out.println(d1.subtract(d2));
		System.out.println(d1.divide(d2,8,BigDecimal.ROUND_HALF_UP));
	}
}

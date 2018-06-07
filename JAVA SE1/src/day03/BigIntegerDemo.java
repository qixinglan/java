package day03;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigIntegerDemo {
	public static void main(String[] args){
		BigInteger sum=BigInteger.valueOf(1);
		for(int i=2;i<=200;i++){
			sum=sum.multiply(BigInteger.valueOf(i));
		}
		System.out.println(sum);
	}
}

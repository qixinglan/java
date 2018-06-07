package day03;

import java.math.BigInteger;

public class PracticeDemo1 {
	public static void main(String[] args){
		BigInteger sum=new BigInteger("1");
		for(int i=2;i<=200;i++){
			sum=sum.multiply(new BigInteger(i+""));
		}
		System.out.println(sum);
	}
}

package day01;

import java.util.Scanner;

public class Demo02 {

	/**
	 * @param args
	 */
	public static final String s="123ABC";
	public static final int A=123;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1=s;//123ABC
		String s2="123ABC";//123ABC
		String s3=A+"ABC";//123ABC
		String s4=123+"ABC";
		String s5="123"+"ABC";
		String s6=1+2+3+"ABC";
		String s7='1'+'2'+'3'+"ABC";
		String s8="1"+'2'+'3'+"ABC";
		String s9="1"+"2"+"3"+"ABC";
		String s10="ABC";
		String s11="123"+s10;
		System.out.println(s8==s5);
		
		
		
		
	}

}

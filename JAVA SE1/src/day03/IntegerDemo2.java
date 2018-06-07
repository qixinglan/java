package day03;

public class IntegerDemo2 {
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		/*
		 * 
		 */
		int a=1;
		String str="123.456";
		double b=Double.parseDouble(str);
		System.out.println(b+a);
		int num=100;
		System.out.println(Integer.toBinaryString(num));
		System.out.println(Integer.toHexString(num));
	}
	
}

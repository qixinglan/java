package day02;

import java.util.Scanner;

public class Demo02 {
	public static void main(String[] args) {
		System.out.println("�����û���");
		String name=new Scanner(System.in).next();
		boolean pass=name.matches("^\\w{4,5}$");
		if(!pass){
			System.out.println("�������");
		}
		
	}
}

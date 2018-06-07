package test02;

import java.io.FileInputStream;
import java.util.Scanner;

public class Nine {
	public static void main(String[] args)throws Exception {
		FileInputStream  fis1=new FileInputStream("fis1.txt");
		System.setIn(fis1);
		Scanner in=new Scanner(System.in);
		String ss=in.nextLine();
		System.out.println(ss);
	}
}

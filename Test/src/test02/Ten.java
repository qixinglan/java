package test02;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Ten {
	public static void main(String[] args)throws Exception {
		Scanner in=new Scanner(System.in);
		String ss=in.nextLine();
		FileOutputStream fos1=new FileOutputStream("dd.txt");
		OutputStreamWriter osw1=new OutputStreamWriter(fos1);
		osw1.write(ss);
		osw1.close();
	}
}

package day01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Seven {
	public static void main(String[] args) throws Exception {
		InputStream in=System.in;
		InputStreamReader reader=new InputStreamReader(in);
		BufferedReader br=new BufferedReader(reader);
		FileWriter writer=new FileWriter("c:"+File.separator+"Users"+
				File.separator+"asus"+File.separator+"Desktop"+File.separator+"test.txt");
		String str=null;
		while((str=br.readLine())!=null){
			if("exit".equals(str)){
				break;
			}
			writer.write(str);
			System.out.println(str);
		}
		System.out.println("ты╪Ш");
		br.close();
		writer.close();
	}
}

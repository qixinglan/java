package day01;

import java.util.Arrays;

//GBK:Ӣ��һ��byte����2��byte
//UTF-8:Ӣ��һ��byte����3��byte
public class Demo07 {
	public static void main(String[] args) throws Exception{
		String str="���heo";
		byte[] bytes=str.getBytes("gbk");
		System.out.println(bytes.length);
		System.out.println(Arrays.toString(bytes));
		System.out.println(new String(bytes,"gbk"));
	}
}

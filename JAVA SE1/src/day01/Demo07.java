package day01;

import java.util.Arrays;

//GBK:英文一个byte汉字2个byte
//UTF-8:英文一个byte汉字3个byte
public class Demo07 {
	public static void main(String[] args) throws Exception{
		String str="你好heo";
		byte[] bytes=str.getBytes("gbk");
		System.out.println(bytes.length);
		System.out.println(Arrays.toString(bytes));
		System.out.println(new String(bytes,"gbk"));
	}
}

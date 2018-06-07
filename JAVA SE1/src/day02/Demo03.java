package day02;

import java.util.Arrays;

public class Demo03 {
	public static void main(String[] args) {
		String str="5,Tom,5, Tom@Tom.cn,  119";
		String[] date=str.split(",\\s*");//字符串拆分
		String[] date2=str.split("^,\\s*$");
		System.out.println(Arrays.toString(date));
		String s=str.replaceAll(",\\s*", "|");//替换
		System.out.println(s);
		char[] chs=s.toCharArray();//字符串变字符数组
		System.out.println(Arrays.toString(chs));
		String ss=new String(chs,0,5);//将字符数组变字符串，并选定部分变化
		System.out.println(ss);
		String name="   Tom    \nand Jerry";
		String[] words=name.trim().toLowerCase().split("\\s+");
		System.out.println(Arrays.toString(words));
	}
}

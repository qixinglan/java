package day02;

import java.util.Arrays;

public class Demo03 {
	public static void main(String[] args) {
		String str="5,Tom,5, Tom@Tom.cn,  119";
		String[] date=str.split(",\\s*");//�ַ������
		String[] date2=str.split("^,\\s*$");
		System.out.println(Arrays.toString(date));
		String s=str.replaceAll(",\\s*", "|");//�滻
		System.out.println(s);
		char[] chs=s.toCharArray();//�ַ������ַ�����
		System.out.println(Arrays.toString(chs));
		String ss=new String(chs,0,5);//���ַ�������ַ�������ѡ�����ֱ仯
		System.out.println(ss);
		String name="   Tom    \nand Jerry";
		String[] words=name.trim().toLowerCase().split("\\s+");
		System.out.println(Arrays.toString(words));
	}
}

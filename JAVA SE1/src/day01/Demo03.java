package day01;

import java.util.Arrays;

//字符数组转大写
public class Demo03 {
	public static void main(String[] args) {
		char[] chs={'D','d','!','H'};
		char[] chs1=Arrays.copyOf(chs, chs.length);
		for(int i=0;i<chs1.length;i++){
			if(chs1[i]>='a'&&chs1[i]<='z'){
				chs1[i]=(char)(chs1[i]+('A'-'a'));
			}
		}
		System.out.println(chs);
		System.out.println(chs1);
		f();
		String ss="HelloWorld";
		ss=ss.toUpperCase();
		System.out.println(ss);//
	}
	public static void f(){
		char[] ch={'A'};
		char[] ch1=Arrays.copyOf(ch, ch.length);
		for(int i=0;i<ch1.length;i++){
			if(ch1[i]>='A'&&ch1[i]<='Z'){
				ch1[i]=(char)(ch1[i]+('a'-'A'));
			}
		}
		System.out.println(ch);
		System.out.println(ch1);
	}
	
}

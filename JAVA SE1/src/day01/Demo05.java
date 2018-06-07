package day01;

public class Demo05 {
	public static void main(String[] args) {
	String str="nihaoguliang";
	int a=str.indexOf("guliang");
	System.out.println(a);
	System.out.println(str.indexOf("P"));
	System.out.println(str.indexOf('i',1));
	System.out.println(str.indexOf('i'));
	System.out.println(str.substring(2, 4));
	//截取主机名客户名
	String mail="gulaing@qq.com";
	System.out.println(mail.substring(0,mail.indexOf('@')));
	System.out.println(mail.substring(mail.indexOf('@')+1));
	}
}

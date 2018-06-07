package day01;

public class Demo06 {
	public static void main(String[] args) {
		String barcode="6953220900756";
		char c=barcode.charAt(2);
		System.out.println(c);
		int n=c-'0';
		System.out.println(n);
		System.out.println((int)c);
		int c1=0;
		int c2=0;
		for(int i=0;i<=11;i+=2){
			c1+=(int)(barcode.charAt(i)-'0');
			c2+=(int)((barcode.codePointAt(i+1))-'0');
		}
		int cc=c1+c2*3;
		int d=(10-cc%10)%10;
		System.out.println(d);
	}
	
}

package day02;

public class Demo05 {
	public static void main(String[] args) {
		System.out.println(test2(50000));
		System.out.println(test1(50000));
	}
	public static long test1(int n){
		long start=System.currentTimeMillis();
		String s="";
		for(int i=0;i<n;i++){
			s+="A";
		}
		long end=System.currentTimeMillis();
		return end-start;
	}
	public static long test2(int n){
		long start=System.currentTimeMillis();
		StringBuilder buf=new StringBuilder();
		for(int i=0;i<n;i++){
			buf.append("A");
		}
		String s=buf.toString();
		long end=System.currentTimeMillis();
		return end-start;
	}
}

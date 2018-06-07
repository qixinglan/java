package test;



public class Three {
	public static void main(String[] args) {
		f1();
		f2();
		f3();
		
		
	}
	public static void f1(){
		long L=System.currentTimeMillis();
		String s="";
		for(int i=0;i<5000;i++){
			s+=i;
		}
		System.out.println(System.currentTimeMillis()-L);
		
	}
	public static void f2(){
		long L=System.currentTimeMillis();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<5000;i++){
			sb.append(i);
		}
		System.out.println(System.currentTimeMillis()-L);
		
	}
	public static void f3(){
		long L=System.currentTimeMillis();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<5000;i++){
			sb.append(i);
		}
		System.out.println(System.currentTimeMillis()-L);
		
	}
}

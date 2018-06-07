package test01;

public class Eight {
	public static void main(String[] args) {
		System.out.println(fun(101));
		int a=0;
		for(int i=0;i<101;i++){
			a+=i;
		}
		System.out.println(a);
	}
	static int fun(int a){
		if(a==1){
			return 0;
		}
		--a;
		return fun(a)+a;
	}
	static String fun1(String a){
		int length=a.length();
		
		return null;
	}
}

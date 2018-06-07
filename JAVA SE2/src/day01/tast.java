package day01;

public class tast {
	static int i=2;
	static int sum=1;
	public static void main(String[] args) {
		f();
	}
	public static void f(){
		if(i<=100){
			sum+=i;
			i++;
			System.out.println(sum);
			f();
		}
	}
}

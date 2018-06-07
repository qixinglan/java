package day02;

public class Demo11 {
	public static void main(String[] args) {
		A a=new A();
		a.f();
		B b=new B();
		b.f();
		A a1= b;
		System.out.println(a1.a);
	}
}
class A{
	int a=1;
	public void f(){
		
		System.out.println("A");
	}
}
class B extends A{
	int a=2;
	public void f(){
		System.out.println("B");
	}
}
class C extends A{
	public void f(){
		System.out.println("C");
	}
}

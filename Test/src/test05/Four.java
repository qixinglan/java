package test05;

public class Four {
	private int a;
	private static int b;
	class A{
		public void fun1(){
			b=3;
			a=1;
			Four.this.a=2;
			System.out.println(a);
			System.out.println(b);
		}
	}
	static class B{
		public void fun2(){
			b=2;
			System.out.println(b);
		}
	}
	static public void fun3(){
		final int d=0;
		class C{
			public void ss(){
				System.out.println(d);
			}
		}
		C c=new C();
		c.ss();
	}
	B b1=new B(){
		public void run(){
			System.out.println("sss");
		}
	};
	public static void main(String[] args) {
		A a=new Four().new A();
		a.fun1();
		Four.A a1=new Four().new A();
		a1.fun1();
		B b=new Four.B();
		b.fun2();
		
		fun3();
	}
}
class D{
	public void dddd(){
		Four.B b=new Four.B();
	}
	
	
}

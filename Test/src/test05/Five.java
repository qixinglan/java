
package test05;

public class Five extends A implements B{

	@Override
	public void fun1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fun2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void fun() {
		// TODO Auto-generated method stub
	}

	@Override
	public void fun3() {
		// TODO Auto-generated method stub
		
	}

	
}
abstract class A{
	int a;
	abstract void fun();
	Five five=new Five();
	public static void main(String[] args) {
		System.out.println("aa");
	}
}
interface B{
	int b=0;
	void fun1();
	void fun2();
	void fun3();
}
package test04;

public class One {
	public static void main(String[] args) {
		A a=new A();
		a.start();
		Thread t=new A();
		t.start();
	}
}
class A extends Thread {
	@Override
	public void run(){
		System.out.println("a");
	}
}

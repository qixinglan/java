package test04;

public class Five {
	public static void main(String[] args) {
		final C c=new C();
		Thread t1=new Thread(){
			public void run(){
				c.fun();
			}
		};
		Thread t2=new Thread(){
			public void run(){
				c.fun();
			}
		};
		t1.start();
		t2.start();
	}
}
class C{
	 int a=1;
	 public void fun(){
		synchronized (this) {
			a++;
			System.out.println(a);
		}
	 }
}
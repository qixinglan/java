package test04;

public class Four {
	static int a=1;
	public static void main(String[] args) {
		Thread t1=new Thread(){
			public void run(){
				fun();
			}
		};
		Thread t2=new Thread(){
			public void run(){
				fun();
			}
		};
		t1.start();
		t2.start();
	}
	static synchronized  public void fun(){
			a++;
			System.out.println(a);
	}
}

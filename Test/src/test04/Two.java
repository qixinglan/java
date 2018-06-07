package test04;

public class Two {
	public static void main(String[] args) {
		B b=new B();
		Thread t1=new Thread(b);
		t1.start();
		Runnable r1=new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("b2");
			}
		};
		Thread t2=new Thread(r1);
		t2.start();
		Thread t3=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("b3");
			}
		});
		t3.start();
		Thread t4=new Thread(){
			public void run(){
				System.out.println("b4");
			}
		};
	}
}
class B implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("b1");
	}
	
}
package day04;

public class Test01 {
	public static void main(String[] args) {
		Thread t1=new Thread(){
			public void run(){
				System.out.println("˯��");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("������");
				}
			}
		};
		t1.start();
		t1.interrupt();
	}
}

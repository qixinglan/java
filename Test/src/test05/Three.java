package test05;

public class Three {
	public static void main(String[] args) throws InterruptedException {
		
		Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("子线程");
			}
		});
		for(int i=0;i<10;i++){
			t1.join();
			t1.start();
		}
		for(int i=0;i<100;i++){
			System.out.println("主线程");
		}
		for(int i=0;i<10;i++){
			t1.join();
			t1.start();
		}
	}
}

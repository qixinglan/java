package test04;

public class Six {
	static Thread t1;
	static Thread t2;
	static Thread t3;
	public static void main(String[] args) throws Exception {
		t1=new Thread(){
			public void run(){
				System.out.println("1");
				synchronized (t1) {
					t1.notify();
				}
			}
		};
		t2=new Thread(){
			public void run(){
				synchronized (t1) {
					try {
						t1.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("2");
				synchronized (t2) {
					t2.notify();
				}
			}
		};
		t3=new Thread(){
			public void run(){
				try {
					synchronized (t2) {
						t2.wait();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("3");
				
			}
		};
		t3.start();
		t1.start();
		t2.start();
	}
}

package test04;

public class Three {
	public static void main(String[] args) {
		Thread t1=new Thread(){
			public void run(){
				try {
					System.out.println("aa...");
					Thread.sleep(1500);
					System.out.println("aaaaaa");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("aa阻塞状态被中断");
				}
			}
		};
		Thread t2=new Thread(){
			public void run(){
				try {
					System.out.println("bb...");
					Thread.sleep(1500);
					System.out.println("bbbbbb");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("bb阻塞状态被中断");
				}
			}
		};
		Thread t3=new Thread(){
			public void run(){
				try {
					System.out.println("守护线程开始运行");
					Thread.sleep(5000);
					System.out.println("守护线程运行结束");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t3.setDaemon(true);
		t3.start();
		//t1.setPriority(Thread.MAX_PRIORITY);
		//System.out.println(t1.getName());
		//t1.setName("t1");
		t1.start();
		//t1.join(1500);
		t1.interrupt();
		//System.out.println(t1.isAlive());
		t2.start();
	}
	
}

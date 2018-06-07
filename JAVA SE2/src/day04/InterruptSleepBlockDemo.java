package day04;
/**
 * 中断睡眠阻塞
 * @author asus
 *
 */
public class InterruptSleepBlockDemo {
	public static void main(String[] args) {
		//林永健的睡眠阻塞进程
		final Thread lin=new Thread(new Runnable(){
			public void run(){
				System.out.println("林：刚美完容，睡觉去啦");
				try {
					Thread.sleep(1000000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("林：干嘛呢？干嘛呢？干嘛呢？都破了相了！");
				}
			}
		});
		// 黄宏线程用于中断林永健睡眠阻塞
		Thread huang=new Thread(new Runnable(){
			public void run(){
				System.out.println("开始砸墙!!");
				for(int i=0;i<5;i++){
					System.out.println("黄：80");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("咣当");
				System.out.println("黄：搞定！");
				//中断林永健的阻塞进程
				lin.interrupt();
				/**
				 * 一个方法的局部内部类中若要引用其他局部变量，该变量必须是final
				 */
			}
		});
		
		huang.start();lin.start();
	}
}

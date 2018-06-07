package day04;


/**
 * wait（）方法   notify（）方法
 * @author asus
 *
 */
public class WaiAndNotifyDemo {
	//图片是否下载完毕
	public static boolean finish=false;
	public static void main(String[] args){
		/**
		 * 两个线程并发运行
		 * 一个线程用于下载图片
		 * 另一个线程用于显示图片
		 * 这里就出现一个问题，显示图片的线程应当等待
		 * 下载图片的进程将图片下载后再进行显示
		 */
	
		//下载图片进程
		final Thread downLoadThread=new Thread(new Runnable(){
			public void run(){
				System.out.println("正在下载图片...");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("图片下载完成");
				finish=true;
				/**
				 * 通知当前对象上等待的线程回到runnable状态
				 * 这里的this就是downLoadThread
				 * 而下面的displayThread就是在当前对象上等待的
				 * 所以调用this notify（）方法会将displayThread
				 * 解除等待阻塞，使其可以继续运行
				 */
				synchronized (this) {
					this.notify();
					//this.notifyAll();??
				}
				
			}
		});
		//展示图片进程
		Thread displayThread=new Thread(new Runnable(){
			public void run(){
				System.out.println("等待下载图片的线程通知我显示...");
				try {
					synchronized (downLoadThread) {//要求必须加锁
						/**
						 * 当前线程在哪个对象上进行等待，就需要获取哪个对象的锁
						 */
						downLoadThread.wait();
					}
					//进入等待阻塞状态，与sleep状态不同的是，必须唤醒（使用notify（）方法唤醒）才能进入runnable状态
					//wait sleep 必须捕获异常
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!finish){
					throw new RuntimeException("图片没有下载完成，无法显示");
				}
				System.out.println("显示图片");
			}
		});
		downLoadThread.start();
		displayThread.start();
	}
}

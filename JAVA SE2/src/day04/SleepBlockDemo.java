package day04;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 睡眠阻塞
 * @author asus
 *
 */
public class SleepBlockDemo {
	public static void main(String[] args) {
		/**电子表
		 * 输出时间格式12:33:30
		 */
		
		while(true){
			Date date=new Date();
			SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
			String now=format.format(date);
			System.out.println(now);
			//每隔一秒钟循环一次
			try {
				/* 1、为什么捕获异常       通知当前线程正在被中断
				 * 2、sleep是当前进程进入阻塞，阻塞的是哪个进程   
				 * 当我们的程序运行时，操作系统会启动一个进程来运行我们的JVM，
				 * 而JVM启动起来后会创建一个线程来运行main方法，
				 * 从而执行我们的程序，这里的sleep方法阻塞的就是这个线程
				 * 3、循环是一秒钟执行一次吗？
				 * 不是，是有误差的
				 * 只能说阻塞了一秒钟，阻塞状态结束后，当前线程会回到runnable状态，等待再次分配时间片从而运行，而这段时间就是误差
				 */
				
				Thread.sleep(1000);//必须捕获异常
				Thread current=Thread.currentThread();//返回当前线程
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
